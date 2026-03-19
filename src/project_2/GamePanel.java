package project_2;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import project_2.Card.Rank;
import project_2.Card.Suit;

public class GamePanel extends JPanel {

    private ArrayList<AnimatedCard> playerHand = new ArrayList<>();
    private ArrayList<AnimatedCard> dealerHand = new ArrayList<>();

    private int playerTotal;
    private int dealerTotal;

    /**
     * Game-wide Timer for all card simulation.
     * <p>
     * Fires at ~60 fps. Advances each animatable objects tick by one frame.
     * Automatically stops when there are no cards to animate.
     * </p>
     */
    private final Timer animation = new Timer(16, e -> {
        boolean anyActive = false;

        for (AnimatedCard card : getAllCards()) {
            if (card.tick())
                anyActive = true;
        }

        repaint();

        if (!anyActive)
            ((Timer) (e.getSource())).stop();
    });

    /**
     * Creates a GamePanel to render components
     */
    public GamePanel() {
        super();
        setLayout(null);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (getWidth() > 0 && getHeight() > 0) {
                    removeComponentListener(this);
                    onReady();
                }
            }
        });
    }

    private void onReady() {

        // Create buttons
        Card card = new Card(Rank.ACE, Suit.HEARTS);

        AnimatedCard ac = new AnimatedCard(card);
        AnimatedCard ac2 = new AnimatedCard(card);
        AnimatedCard ac3 = new AnimatedCard(card);

        AnimatedCard ac4 = new AnimatedCard(card);
        AnimatedCard ac5 = new AnimatedCard(card);
        AnimatedCard ac6 = new AnimatedCard(card);

        GameFrame.runSequence(300,
                () -> addPlayerCard(ac4, deckX(), deckY()),
                () -> addPlayerCard(ac5, deckX(), deckY()),
                () -> addPlayerCard(ac6, deckX(), deckY()));

        GameFrame.runSequence(300,
                () -> addDealerCard(ac, deckX(), deckY(), false),
                () -> addDealerCard(ac2, deckX(), deckY(), false),
                () -> addDealerCard(ac3, deckX(), deckY(), false));
    }

    /**
     * Returns a flat array list of all cards currently on the table
     *
     * @return ArrayList of all cards
     */
    public ArrayList<AnimatedCard> getAllCards() {
        ArrayList<AnimatedCard> all = new ArrayList<>();
        all.addAll(playerHand);
        all.addAll(dealerHand);
        return all;
    }

    /**
     * Sets the hands of player and dealer, then updates the canvas
     *
     * @param player List of player cards
     * @param dealer List of dealer cards
     */
    public void setHands(ArrayList<AnimatedCard> player, ArrayList<AnimatedCard> dealer) {
        this.playerHand = player;
        this.dealerHand = dealer;
        repaint();
    }

    /**
     * Sets the totals for player and delear for compute
     *
     * @param player Points of player
     * @param dealer Points of dealer
     */
    public void setTotals(int player, int dealer) {
        this.playerTotal = player;
        this.dealerTotal = dealer;
        repaint();
    }

    public void startLoop() {
        if (!animation.isRunning())
            animation.start();
    }

    // =================================================
    // Game Functions
    // =================================================

    public void addPlayerCard(AnimatedCard card, double fX, double fY) {
        playerHand.add(card);
        int index = playerHand.size() - 1;
        double toX = cardX(index, playerHand.size());
        double toY = playerZoneY();
        card.startDeal(fX, fY, toX, toY, true, null);
        startLoop();
    }

    public void addDealerCard(AnimatedCard card, double fX, double fY, boolean faceUp) {
        dealerHand.add(card);
        int index = dealerHand.size() - 1;
        double toX = cardX(index, dealerHand.size());
        double toY = dealerZoneY();
        card.startDeal(fX, fY, toX, toY, faceUp, null);
        startLoop();
    }

    /**
     * Flips the dealer's hole card face-up.
     * Called when the dealer reveals their hidden card.
     *
     * @param onComplete Called once the flip animation finishes
     */
    public void flipDealerHoleCard(Runnable onComplete) {
        if (!dealerHand.isEmpty()) {
            dealerHand.get(0).startFlip(true, onComplete);
            startLoop();
        }
    }

    /**
     * Plays the bust animation on every card in the player's hand,
     * then clears the hand once all cards have faded out.
     *
     * @param onComplete Called once all cards have finished fading
     */
    // public void playPlayerBust(Runnable onComplete) {
    // if (playerHand.isEmpty()) return;

    // // Stagger the bust animation across each card
    // int[] completed = {0};
    // for (int i = 0; i < playerHand.size(); i++) {
    // final boolean isLast = (i == playerHand.size() - 1);
    // playerHand.get(i).startBust(() -> {
    // completed[0]++;
    // if (isLast && onComplete != null) onComplete.run();
    // });
    // }
    // startLoop();
    // }

    /**
     * Plays the win pulse animation on every card in the player's hand.
     *
     * @param onComplete Called once all cards have finished pulsing
     */
    // public void playPlayerWin(Runnable onComplete) {
    // if (playerHand.isEmpty()) return;

    // int[] completed = {0};
    // for (int i = 0; i < playerHand.size(); i++) {
    // final boolean isLast = (i == playerHand.size() - 1);
    // playerHand.get(i).startWin(() -> {
    // completed[0]++;
    // if (isLast && onComplete != null) onComplete.run();
    // });
    // }
    // startLoop();
    // }

    /**
     * Clears both hands from the table instantly.
     * Call this at the start of a new round after animations finish.
     */
    public void clearHands() {
        playerHand.clear();
        dealerHand.clear();
        repaint();
    }

    // =================================================
    // Layout Functions
    // =================================================

    public int cardWidth() {
        return (int) (getWidth() * 0.08);
    }

    public int cardHeight() {
        return (int) (cardWidth() * 1.4);
    }

    public int deckX() {
        return (int) (getWidth() * 0.82);
    }

    public int deckY() {
        return (int) (getHeight() * 0.12);
    }

    public int dealerZoneY() {
        return (int) (getHeight() * 0.15);
    }

    public int playerZoneY() {
        return (int) (getHeight() * 0.52);
    }

    /**
     * Computes the X position for a card at the given index,
     * centering the full hand horizontally on the panel.
     */
    public int cardX(int index, int handSize) {
        int padding = (int) (cardWidth() * 0.25);
        int totalWidth = handSize * cardWidth() + (handSize - 1) * padding;
        int startX = (getWidth() - totalWidth) / 2;
        return startX + index * (cardWidth() + padding);
    }

    // =================================================
    // Painting
    // =================================================

    /**
     * Draws the playing board and all cards.
     * Updated once per tick to reload states
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Draw inner elements
        drawTable(g2);
        drawDeck(g2);
        drawHand(g2, dealerHand);
        drawHand(g2, playerHand);
    }

    // Draw
    private void drawTable(Graphics2D g) {
        g.setColor(new Color(0, 110, 60));
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(new Color(0x2c7856));
        g.setStroke(new BasicStroke(2f));
        g.drawRoundRect(
                (int) (getWidth() * 0.03),
                (int) (getHeight() * 0.04),
                (int) (getWidth() * 0.93),
                (int) (getHeight() * 0.88),
                (int) (getWidth() * 0.03),
                (int) (getHeight() * 0.05));
    }

    public void drawZones(Graphics2D g) {
        g.setColor(new Color(0x5a9e72));
        g.setFont(new Font("SansSerif", Font.BOLD, 11));

        FontMetrics fm = g.getFontMetrics();
        String dealer = "DEALER";
        String player = "PLAYER";

        g.drawString(dealer, (getWidth() - fm.stringWidth(dealer)) / 2, (int) (getHeight() * .15) - 14);
        g.drawString(player, (getWidth() - fm.stringWidth(player)) / 2, (int) (getHeight() * .52) - 14);
    }

    private void drawDeck(Graphics2D g) {
        int cardW = (int) (getWidth() * .08);
        int cardH = (int) (cardW * 1.4);

        int deckX = (int) (getWidth() * .82);
        int deckY = (int) (getHeight() * .12);

        for (int i = 2; i >= 0; i--) {
            g.setColor(new Color(0x1e3a8a));
            g.fillRoundRect(deckX + i, deckY + i, cardW, cardH, 10, 10);
            g.setColor(new Color(0x3b5fc0));
            g.fillRoundRect(deckX + i, deckY + i, cardW, cardH, 10, 10);
        }

        // Shoe
        g.setColor(new Color(0x3b5fc0));
        g.setFont(new Font("SansSerif", Font.BOLD, 10));
        FontMetrics fm = g.getFontMetrics();
        g.drawString("SHOE",
                deckX + (cardW - fm.stringWidth("SHOE")) / 2,
                deckY + cardH / 2 + 4);
    }

    private void drawHand(Graphics2D g, ArrayList<AnimatedCard> hand) {
        if (hand == null || hand.isEmpty())
            return;

        int cardW = (int) (getWidth() * .08);
        int cardH = (int) (cardW * 1.4);

        hand.forEach(card -> {
            card.draw(g, cardW, cardH);
        });
    }
}
