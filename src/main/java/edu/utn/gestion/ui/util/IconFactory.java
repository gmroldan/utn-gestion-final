package edu.utn.gestion.ui.util;

import javax.swing.ImageIcon;

/**
 * Created by martin on 04/03/16.
 */
public class IconFactory {

    /**
     * Returns an icon from the given location.
     *
     * @param iconLocation
     * @return
     */
    public static ImageIcon getIcon(String iconLocation) {
        return new ImageIcon(Thread.currentThread().getContextClassLoader().getResource(iconLocation));
    }
}
