package edu.utn.gestion.ui.util;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by martin on 20/12/15.
 */
public class InternalFrameManager {
    private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();
    private static final Map<String, JInternalFrame> FRAMES_MAP = new HashMap<>();

    public static JDesktopPane getDesktopPane() {
        return DESKTOP_PANE;
    }

    public static void addInternalFrame(JInternalFrame internalFrame) {
        String key = internalFrame.getTitle();

        if (!FRAMES_MAP.containsKey(key)) {
            DESKTOP_PANE.add(internalFrame);
            FRAMES_MAP.put(key, internalFrame);
        }
    }

    public static void removeInternalFrame(JInternalFrame internalFrame) {
        String key = internalFrame.getTitle();

        if (FRAMES_MAP.containsKey(key)) {
            DESKTOP_PANE.remove(internalFrame);
            FRAMES_MAP.remove(key);
        }
    }
}
