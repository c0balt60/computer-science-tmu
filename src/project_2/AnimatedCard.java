package project_2;

import java.awt.*;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class AnimatedCard {
    private final Card card;

    private double cx, cy = 0;
    private double tx, ty = 0;

    private double scale = 1.0;
    private float alpha = 1.0f;
    private int glow = 0;
    private boolean faceUp = false;

    private JPanel panel;

    private int flipTick = 0;
    private int bustTick = 0;
    // private boolean dealCard = true;
    private boolean revealOnFlip = false;

    private Runnable onDealComplete;
    private Runnable onFlipComplete;
    private Runnable onBustComplete;
    private Runnable onFadeComplete;
    private Runnable onWinComplete;

    public AnimatedCard(Card card) {
        this.card = card;
    }

    // region Getters

    public String getRank() {
        return card.getRank().getLabel();
    }

    public String getSuitSymbol() {
        return card.getSuitSymbol();
    }

    public boolean isRed() {
        return card.isRed();
    }

    // endregion

    // region Setters

    public void setPosition(double x, double y) {
        cx = x;
        cy = y;
        tx = x;
        ty = y;
    }

    public void setTarget(double x, double y) {
        tx = x;
        ty = y;
    }

    // endregion

    public boolean isMoving() {
        return Math.abs(cx - tx) > 1 || Math.abs(cy - ty) > 1;
    }

    // =================================================
    // Animation Functions
    // =================================================

    public void startDeal(double fX, double fY, double toX, double toY, boolean faceUp, Runnable onComplete) {
        this.cx = fX;
        this.cy = fY;
        this.tx = toX;
        this.ty = toY;
        this.faceUp = faceUp;
        alpha = 1.0f;
        onDealComplete = onComplete;
    }

    // =================================================
    // Tick Functions
    // =================================================

    /**
     * Advances all active animations by one frame.
     * This method is called by {@Code GamePanel} to run the card's simulation
     *
     * @return
     */
    public boolean tick() {
        boolean active = false;

        if (isMoving()) {
            tickDeal();
            active = true;
        }

        return active;
    }

    private void tickDeal() {
        cx += (tx - cx) * .18;
        cy += (ty - cy) * .18;

        if (!isMoving()) {
            cx = tx;
            cy = ty;
            if (onDealComplete != null) {
                onDealComplete.run();
                onDealComplete = null;
            }
        }
    }

    public void reset() {
        scale = 1.0;
        alpha = 1.0f;
        glow = 0;
        faceUp = false;
        flipTick = 0;
        bustTick = 0;

        onDealComplete = null;
    }

    /**
     * Draws this {@code AnimatedCard} onto the given {@code Graphics2D} context
     * using its current animation state
     *
     * @param g The graphics context to draw
     * @param w Card width
     * @param h Card height
     */
    public void draw(Graphics2D g, int w, int h) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int x = (int) cx;
        int y = (int) cy;

        // Apply alpha
        Composite original = g.getComposite();
        g.setComposite(
                AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        // Draw glow behind card
        if (glow > 0) {
            g.setColor(new Color(240, 192, 64, 100));
            g.fillRoundRect(x - glow, y - glow, w + glow * 2, h + glow * 2, 16, 16);
        }

        // Apply scale transform centered on the card
        AffineTransform old = g.getTransform();
        g.translate(x + w / 2.0, y + h / 2.0);
        g.scale(scale, 1.0);
        g.translate(-(w / 2.0), -(h / 2.0));

        // Draw shadow
        g.setColor(new Color(0, 0, 0, 40));
        g.fillRoundRect(3, 3, w, h, 10, 10);

        if (!faceUp)
            drawBack(g, w, h);
        else
            drawFront(g, w, h);

        // Resore transform and composite
        g.setTransform(old);
        g.setComposite(original);
    }

    /**
     * Draws the face-down version of the card (blue)
     *
     * @param g The graphics context to draw
     * @param w Card width
     * @param h Card height
     */
    private void drawBack(Graphics2D g, int w, int h) {
        g.setColor(new Color(0x1e3a8a));
        g.fillRoundRect(0, 0, w, h, 10, 10);
        g.setColor(new Color(0x3b5fc0));
        g.drawRoundRect(3, 3, w - 6, h - 6, 8, 8);
        g.setFont(new Font("SansSerif", Font.BOLD, h / 4));
        FontMetrics fm = g.getFontMetrics();
        g.drawString("?", (w - fm.stringWidth("?")) / 2, (h + fm.getAscent()) / 2 - 4);
    }

    /**
     * Draws the face-up version of the card (normal)
     *
     * @param g The graphics context to draw
     * @param w Card width
     * @param h Card height
     */
    private void drawFront(Graphics2D g, int w, int h) {
        g.setColor(Color.WHITE);
        g.fillRoundRect(0, 0, w, h, 10, 10);
        g.setColor(new Color(0xdddddd));
        g.setStroke(new BasicStroke(0.5f));
        g.drawRoundRect(0, 0, w, h, 10, 10);

        Color suitColor = isRed() ? new Color(0xc0392b) : new Color(0x1a1a1a);
        g.setColor(suitColor);

        // Corner rank
        g.setFont(new Font("SansSerif", Font.BOLD, h / 7));
        g.drawString(getRank(), 5, h / 6);

        // Corner suit
        g.setFont(new Font("SansSerif", Font.PLAIN, h / 8));
        g.drawString(getSuitSymbol(), 6, h / 4);

        // Center suit symbol
        g.setFont(new Font("SansSerif", Font.PLAIN, h / 3));
        FontMetrics fm = g.getFontMetrics();
        String sym = getSuitSymbol();
        g.drawString(sym, (w - fm.stringWidth(sym)) / 2,
                (h + fm.getAscent()) / 2 - 4);
    }
}
