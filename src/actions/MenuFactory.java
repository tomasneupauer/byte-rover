package org.berandev.byterover;

import javax.swing.SwingUtilities;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.Action;
import java.awt.Component;

public class MenuFactory {
    public static JPopupMenu newPopupMenu(Action[] actions){
        JPopupMenu popupMenu = new ActionPopupMenu(actions);
        for (Action action : actions){
            popupMenu.add(new JMenuItem(action));
        }
        return popupMenu;
    }
}

class ActionPopupMenu extends JPopupMenu {
    private Action[] menuActions;

    public ActionPopupMenu(Action[] actions){
        super();
        menuActions = actions;
    }

    @Override
    public void show(Component invoker, int x, int y){
        for (Action action : menuActions){
            action.putValue(ActionFactory.UPDATE_ACTION, null);
        }
        SwingUtilities.invokeLater(() -> {super.show(invoker, x, y);});
    }
}

