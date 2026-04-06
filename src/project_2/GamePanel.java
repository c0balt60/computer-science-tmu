package project_2;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class GamePanel extends JPanel {

    private ArrayList<AnimatedCard> playerHand = new ArrayList<>();
    private ArrayList<AnimatedCard> dealerHand = new ArrayList<>();

    private int playerTotal;
    private int dealerTotal;

    private final ScaleProvider scaler;

    private boolean buttonState = true;

    public Map<String, JLabel> labels = new HashMap<>();

    /**
     * Player pressed New Round button
     */
    public Runnable onPlayerReset;

    /**
     * Player pressed Hit
     */
    public Runnable onPlayerHit;

    /**
     * Player pressed Double
     */
    public Runnable onPlayerDouble;

    /**
     * Player pressed Stand
     */
    public Runnable onPlayerStand;

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
        setVisible(true);

        // Create scale
        scaler = new ScaleProvider(this);

        // Render ui
        SwingUtilities.invokeLater(this::renderInterace);
    }

    /**
     * Render the misc buttons such as Hit, Double, ...
     */
    private void renderInterace() {
        // Create buttons
        JButton hit = createPrimaryButton("Hit", new Color(0xf0c040), Color.BLACK);
        add(hit);
        JButton stand = createPrimaryButton("Stand", new Color(0x2d6e46), Color.WHITE);
        add(stand);
        JButton dbl = createPrimaryButton("Double", new Color(0x2d6e46), Color.WHITE);
        add(dbl);
        // JButton split = createPrimaryButton("Split", new Color(0x2d6e46),
        // Color.WHITE);
        // add(split);
        JButton newRound = createPrimaryButton("New Round", new Color(0x5a9e72), Color.WHITE);
        add(newRound);

        // Create chip buttons
        JButton chip5 = createChipButton("$5", new Color(0xc0392b));
        add(chip5);
        JButton chip25 = createChipButton("$25", new Color(0x27ae60));
        add(chip25);
        JButton chip100 = createChipButton("$100", new Color(0x2980b9));
        add(chip100);

        // Create labels
        JLabel player = createLabel("PLAYER");
        add(player);
        JLabel playerPoints = createLabel("Player Points");
        add(playerPoints);
        JLabel dealerPoints = createLabel("Dealer Points");
        add(dealerPoints);

        // Scale Buttons
        scaler.register(hit, 0.65, .8, .09, 0.04);
        scaler.register(stand, 0.75, .8, .09, 0.04);
        scaler.register(dbl, 0.85, .8, .09, 0.04);
        // scaler.register(split, 0.65, .85, .09, 0.04);
        scaler.register(newRound, 0.65, .85, .29, 0.04);

        // Scale chip buttons
        scaler.register(chip5, .075, .6, .05, .05, 1f);
        scaler.register(chip25, .14, .6, .05, .05, 1f);
        scaler.register(chip100, .2, .6, .05, .05, 1f);

        // Scale labels
        scaler.register(player, .05, .5, .12, .04);

        // Write labels
        labels.put("PlayerPoints", player);
        labels.put("DealerPoints", dealerPoints);

        // Write button listeners
        hit.addActionListener(e -> {
            if (!buttonState)
                return;
            System.out.println("Hit");
            onPlayerHit.run();
        });
        stand.addActionListener(e -> {
            if (!buttonState)
                return;
            System.out.println("Stand");
            onPlayerStand.run();
        });
        dbl.addActionListener(e -> {
            if (!buttonState)
                return;
            System.out.println("Double");
            onPlayerDouble.run();
        });
        newRound.addActionListener(e -> {
            System.out.println("New Round");
            onPlayerDouble.run();
        });
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

    /**
     * Sets the given Label's text
     *
     * @param label the label on screen to modify text
     * @param text  the text to be set
     */
    public void setLabelText(String label, String text) {
        if (!labels.containsKey(label))
            return;
        labels.get(label).setText(text);
    }

    /**
     * Begins the simulation loop in its not active
     */
    public void startLoop() {
        if (!animation.isRunning())
            animation.start();
    }

    // =================================================
    // Game Functions
    // =================================================

    public void addCard(AnimatedCard card) {
        // scaler.register(card, ABORT, playerTotal, WIDTH, HEIGHT);
    }

    /**
     * Creates an animated player card
     *
     * @param card AnimatedCard to animate
     * @param fX   Start position x (Scale)
     * @param fY   Start position y (Scale)
     */
    public void addPlayerCard(AnimatedCard card, double fX, double fY) {
        playerHand.add(card);
        int index = playerHand.size() - 1;
        double toX = cardX(index, playerHand.size());
        double toY = playerZoneY();
        card.startDeal(fX, fY, toX, toY, true, null);
        startLoop();
    }

    /**
     * Creates an animated dealer card, with optional faceUp
     *
     * @param card   AnimatedCard to animate
     * @param fX     Start position x
     * @param fY     Start position y
     * @param faceUp Start with faceUp
     */
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
            System.out.println("Flip card");
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
        return (int) (getWidth() * 0.06);
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
    // Button Factory
    // =================================================

    /**
     * Creates a styled jbutton, re-drawing it into a styled
     * rectangular button.
     *
     * @param text The button label
     * @param bg   Background fill color
     * @param fg   Text color
     * @return
     */
    private JButton createPrimaryButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics graphics) {
                Graphics2D g = (Graphics2D) graphics.create();
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                ButtonModel model = getModel();
                g.setColor(
                        (model.isPressed()) ? bg.darker() : (model.isRollover() ? bg.brighter() : bg));

                g.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);

                // Add outline
                if (!bg.equals(new Color(0xf0c040))) {
                    g.setColor(new Color(0x5a9e72));
                    g.setStroke(new BasicStroke(1.5f));
                    g.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 12, 12);
                }

                g.setColor(fg);
                g.setFont(new Font("SansSerif", Font.BOLD, 13));
                FontMetrics fm = g.getFontMetrics();
                g.drawString(text, (getWidth() - fm.stringWidth(text)) / 2,
                        (getHeight() + fm.getAscent() - fm.getDescent()) / 2);
                g.dispose();
            }
        };
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return btn;
    }

    private JButton createChipButton(String text, Color color) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics graphics) {
                Graphics2D g = (Graphics2D) graphics.create();
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Body
                ButtonModel model = getModel();
                g.setColor(
                        model.isPressed() ? color.darker() : (model.isRollover() ? color.brighter() : color));
                // g.setStroke(new BasicStroke(2f));
                g.fillOval(0, 0, getWidth(), getHeight());

                // Chip border
                g.setColor(color.brighter());
                g.setStroke(new BasicStroke(2f));
                g.drawOval(1, 1, getWidth() - 2, getHeight() - 2);

                // Label
                g.setColor(Color.WHITE);
                g.setFont(new Font("SansSerif", Font.BOLD, getHeight() / 4));
                FontMetrics fm = g.getFontMetrics();
                g.drawString(text, (getWidth() - fm.stringWidth(text)) / 2,
                        (getHeight() + fm.getAscent() - fm.getDescent()) / 2);
                g.dispose();
            }
        };
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return btn;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        return label;
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
