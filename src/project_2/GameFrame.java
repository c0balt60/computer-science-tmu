package project_2;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

public class GameFrame extends JFrame {

    public static void runAfter(int ms, Runnable onComplete) {
        Timer timer = new Timer(ms, e -> onComplete.run());
        timer.setRepeats(false);
        timer.start();
    }

    public static void runSequence(int ms, Runnable... actions) {
        for (int i = 0; i < actions.length; i++) {
            final Runnable action = actions[i];
            runAfter(ms * i, action);
        }
    }

    // Class renderer
    public static void main() {
        JFrame frame = new GameFrame();
        GamePanel panel = new GamePanel();

        // Add panel to frame
        frame.add(panel);

        // Scaler
        ScaleProvider scale = new ScaleProvider(frame);
        scale.register(panel, 0, 0, 1, 1);

        panel.onPlayerHit = () -> {
            System.out.println("Player hit");
        };

        // Build game handler
        BlackjackGame game = new BlackjackGame(panel);
        game.dealOpeningHand();
        // runAfter(3000, () -> game.runDealerTurn());
    }

    /**
     * Creates a Blackjack game window, initializes necessary components
     */
    public GameFrame() {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Blackjack");
        setIconImage(new ImageIcon("./Assets/icon.png").getImage());
        setSize(1200, 800);
        setVisible(true);
        setLayout(null);
    }

    // =================================================
    // Event Listeners
    // =================================================

}
