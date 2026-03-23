package project_2;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

/**
 * Manages game state and orchestrates dealing, player
 * actions, and dealer resolution using a multi-deck shoe.
 */
public class BlackjackGame {

    private final Shoe shoe;
    private final GamePanel panel;

    private ArrayList<AnimatedCard> playerHand = new ArrayList<>();
    private ArrayList<AnimatedCard> dealerHand = new ArrayList<>();

    /**
     * Creates a new BlackjackGame with a 6-deck shoe.
     *
     * @param panel The GamePanel to deal cards onto
     */
    public BlackjackGame(GamePanel panel) {
        this.panel = panel;
        this.shoe = new Shoe(6);
    }

    /**
     * Deals the opening hand — two cards to the player and two
     * to the dealer, staggered by 300ms. The dealer's first card
     * is dealt face-down as the hole card.
     */
    public void dealOpeningHand() {
        playerHand.clear();
        dealerHand.clear();

        // Collect all four cards upfront
        AnimatedCard playerCard1 = shoe.dealAnimated();
        AnimatedCard dealerCard1 = shoe.dealAnimated(); // hole card
        AnimatedCard playerCard2 = shoe.dealAnimated();
        AnimatedCard dealerCard2 = shoe.dealAnimated();

        // Stagger deal with 300ms between each card
        runSequence(300,
                () -> panel.addPlayerCard(playerCard1, panel.deckX(), panel.deckY()),
                () -> panel.addDealerCard(dealerCard1, panel.deckX(), panel.deckY(),
                        false), // face-down hole card
                () -> panel.addPlayerCard(playerCard2, panel.deckX(), panel.deckY()),
                () -> panel.addDealerCard(dealerCard2, panel.deckX(), panel.deckY(),
                        true) // face-up
        );

        playerHand.add(playerCard1);
        playerHand.add(playerCard2);
        dealerHand.add(dealerCard1);
        dealerHand.add(dealerCard2);
    }

    /**
     * Deals one card to the player and adds it to their hand.
     * Used when the player hits.
     */
    public void dealToPlayer() {
        AnimatedCard ac = shoe.dealAnimated();
        playerHand.add(ac);
        panel.addPlayerCard(ac, panel.deckX(), panel.deckY());
    }

    /**
     * Deals one card to the dealer and adds it to their hand.
     * Used during dealer resolution after the player stands.
     */
    public void dealToDealer() {
        AnimatedCard ac = shoe.dealAnimated();
        dealerHand.add(ac);
        panel.addDealerCard(ac, panel.deckX(), panel.deckY(), true);
    }

    /**
     * Runs the dealer's turn — flips the hole card then keeps
     * hitting until the dealer reaches 17 or above.
     * Follows soft 17 rules (dealer hits on soft 17).
     */
    public void runDealerTurn() {
        System.out.println("Run");
        panel.flipDealerHoleCard(() -> dealerHitLoop());
    }

    /**
     * Recursively deals cards to the dealer one at a time,
     * waiting 600ms between each card, until the dealer stands.
     */
    private void dealerHitLoop() {
        int total = calculateTotal(dealerHand);
        boolean soft17 = (total == 17) && isSoft(dealerHand);

        if (total < 17 || soft17) {
            runAfter(600, () -> {
                dealToDealer();
                // Wait for deal animation then check again
                runAfter(500, this::dealerHitLoop);
            });
        } else {
            System.out.println("Resolve round");
            // resolveRound();
        }
    }

    // ═══════════════════════════════════════════════════════════════
    // Hand evaluation
    // ═══════════════════════════════════════════════════════════════

    /**
     * Calculates the best Blackjack total for a hand.
     * Aces are reduced from 11 to 1 as needed to avoid bust.
     *
     * @param hand The list of AnimatedCards to total
     * @return The highest non-busting total possible
     */
    public int calculateTotal(List<AnimatedCard> hand) {
        int total = 0;
        int aces = 0;

        for (AnimatedCard ac : hand) {
            total += ac.getValue();
            if (ac.isAce())
                aces++;
        }

        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }

        return total;
    }

    /**
     * Returns true if the hand contains an Ace still counted as 11.
     *
     * @param hand The hand to check
     * @return True if the hand is soft
     */
    public boolean isSoft(List<AnimatedCard> hand) {
        int total = 0;
        int aces = 0;
        for (AnimatedCard ac : hand) {
            total += ac.getValue();
            if (ac.isAce())
                aces++;
        }
        return aces > 0 && total <= 21;
    }

    /**
     * Returns true if the hand total exceeds 21.
     *
     * @param hand The hand to check
     * @return True if bust
     */
    public boolean isBust(List<AnimatedCard> hand) {
        return calculateTotal(hand) > 21;
    }

    /**
     * Returns true if the hand is a natural Blackjack —
     * exactly two cards totalling 21.
     *
     * @param hand The hand to check
     * @return True if Blackjack
     */
    public boolean isBlackjack(List<AnimatedCard> hand) {
        return hand.size() == 2 && calculateTotal(hand) == 21;
    }

    // ═══════════════════════════════════════════════════════════════
    // Helpers
    // ═══════════════════════════════════════════════════════════════

    /**
     * Runs the given code once after a delay on the Swing event thread.
     *
     * @param ms         Milliseconds to wait
     * @param onComplete Code to run after the delay
     */
    private void runAfter(int ms, Runnable onComplete) {
        Timer t = new Timer(ms, e -> onComplete.run());
        t.setRepeats(false);
        t.start();
    }

    /**
     * Runs a sequence of actions with a fixed delay between each.
     *
     * @param ms      Milliseconds between each action
     * @param actions Actions to run in order
     */
    private void runSequence(int ms, Runnable... actions) {
        for (int i = 0; i < actions.length; i++) {
            final Runnable action = actions[i];
            runAfter(ms * i, action);
        }
    }
}
