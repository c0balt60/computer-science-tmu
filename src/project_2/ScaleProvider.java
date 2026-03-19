package project_2;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * The <i>ScaleProvider</i> is an object that prvoides scaling to JavaSwing
 * components based on the size of the parent component.
 *
 * <p>
 * {@code ScaleProvider} should be used once per frame, and to only resize the
 * shallow children
 * of the frame. (not nested children of children)
 *
 * Sample of how it should be used
 *
 * <pre>
 *      JFrame frame = new JFrame();
 *
 *      // Create items
 *      JButton btn = new JButton();
 *
 *      ScaleProvider scaler = new ScaleProvider(frame);
 *      scaler.register(btn,  0.0, 0.0, .25, .25)  // Makes the button positioned at 0,0 with size of 25% width and 25% height
 * </pre>
 *
 * <b>Note:</b> This class autmatically handles resizing hooks so you do not
 * need to manually call apply(Component) when the parent resizes.
 * </p>
 *
 * @author Andrii Naumenko
 */
public class ScaleProvider {
    /**
     * Store proportional bounds for Component (x, y, width, height)
     */
    private final Map<Component, double[]> ratioMap;

    /**
     * Parent container that ratios are calculated to
     */
    private final Container parent;

    /**
     * Creates a Scaler class to the given parent.
     * Automatically hooks to parent sizing changes and resizes children
     *
     * @param parent Parent container of the children
     * @param ratios Map of the children with their ratios
     */
    public ScaleProvider(Container parent, Map<Component, double[]> ratios) {
        this.parent = parent;
        this.ratioMap = ratios;
        listenToResize();
    }

    /**
     * Creates a Scaler class to the given parent.
     * Automatically hooks to parent sizing changes and resizes children
     *
     * @param parent Parent container of the children
     */
    public ScaleProvider(Container parent) {
        this(parent, new HashMap<>());
    }

    /**
     * Registers a component into the ratio provider
     *
     * @param component Child to resize
     * @param x         position scale
     * @param y         position scale
     * @param w         width scale
     * @param h         height scale
     */
    public void register(Component component, double x, double y, double w, double h) {
        ratioMap.put(component, new double[] { x, y, w, h });
        apply(component);
    }

    /**
     * Removes the component from resizing
     *
     * @param component
     */
    public void unregister(Component component) {
        ratioMap.remove(component);
    }

    /**
     * Apply the currently stored ratios to the component, based
     * on the current size of the parent
     *
     * @param component
     */
    public void apply(Component component) {
        double[] ratios = ratioMap.get(component);
        if (ratios == null)
            return;

        int pw = parent.getWidth();
        int ph = parent.getHeight();

        component.setBounds(
                (int) (pw * ratios[0]),
                (int) (ph * ratios[1]),
                (int) (pw * ratios[2]),
                (int) (ph * ratios[3]));
    }

    /**
     * Apply ratios to all stored components
     */
    public void applyAll() {
        ratioMap.forEach((component, _) -> apply(component));
        parent.revalidate();
        parent.repaint();
    }

    /**
     * Listen to parent frame resizing
     */
    private void listenToResize() {
        parent.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                applyAll();
            }
        });
    }
}
