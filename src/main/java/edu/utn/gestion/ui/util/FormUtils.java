package edu.utn.gestion.ui.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * Created by martin on 08/12/15.
 */
public class FormUtils {
    private static final GridBagConstraints LAST_CONSTRAINTS;
    private static final GridBagConstraints MIDDLE_CONSTRAINTS;
    private static final GridBagConstraints LABEL_CONSTRAINTS;

    static {
        LAST_CONSTRAINTS = new GridBagConstraints();
        LAST_CONSTRAINTS.fill = GridBagConstraints.HORIZONTAL;
        LAST_CONSTRAINTS.anchor = GridBagConstraints.NORTHWEST;
        LAST_CONSTRAINTS.weightx = 1.0;
        LAST_CONSTRAINTS.gridwidth = GridBagConstraints.REMAINDER;
        LAST_CONSTRAINTS.insets = new Insets(1, 1, 1, 1);

        MIDDLE_CONSTRAINTS = (GridBagConstraints) LAST_CONSTRAINTS.clone();
        MIDDLE_CONSTRAINTS.gridwidth = GridBagConstraints.RELATIVE;

        LABEL_CONSTRAINTS = (GridBagConstraints) LAST_CONSTRAINTS.clone();
        LABEL_CONSTRAINTS.weightx = 0.0;
        LABEL_CONSTRAINTS.gridwidth = 1;
    }

    /**
     * Adds a field component. Any component may be used. The
     * component will be stretched to take the remainder of
     * the current row.
     */
    public static void addLastField(Component component, Container parent) {
        addComponent(component, parent, LAST_CONSTRAINTS);
    }

    /**
     * Adds an arbitrary label component, starting a new row
     * if appropriate. The width of the component will be set
     * to the minimum width of the widest component on the
     * form.
     */
    public static void addLabel(Component component, Container parent) {
        addComponent(component, parent, LABEL_CONSTRAINTS);
    }

    /**
     * Adds a "middle" field component. Any component may be
     * used. The component will be stretched to take all of
     * the space between the label and the "last" field. All
     * "middle" fields in the layout will be the same width.
     */
    public static void addMiddleField(Component component, Container parent) {
        addComponent(component, parent, MIDDLE_CONSTRAINTS);
    }

    private static void addComponent(Component component,  Container parent, GridBagConstraints constraints) {
        GridBagLayout layout = (GridBagLayout) parent.getLayout();
        layout.setConstraints(component, constraints);
        parent.add(component);
    }
}
