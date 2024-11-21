package org.berandev.byterover;

import javax.swing.SwingUtilities;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.Action;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;
import java.awt.Component;

public class MenuFactory {
    public static JPopupMenu newPopupMenu(Action[] items){
        JPopupMenu actionPopupMenu = new ActionPopupMenu(items);
        for (Action action : items){
            actionPopupMenu.add(new JMenuItem(action));
        }
        return actionPopupMenu;
    }

    public static JMenu newMenu(Action[] items, Action menu){
        JMenu actionMenu = new ActionMenu(items, menu);
        for (Action action : items){
            actionMenu.add(new JMenuItem(action));
        }
        return actionMenu;
    }
}

class ActionPopupMenu extends JPopupMenu {
    private Action[] itemActions;

    public ActionPopupMenu(Action[] items){
        super();
        itemActions = items;
    }

    @Override
    public void show(Component invoker, int x, int y){
        for (Action action : itemActions){
            action.putValue(ActionFactory.UPDATE_ACTION, null);
        }
        SwingUtilities.invokeLater(() -> {super.show(invoker, x, y);});
    }
}

class ActionMenu extends JMenu {
    private Action[] itemActions;

    public ActionMenu(Action[] items, Action menu){
        super(menu);
        itemActions = items;
        addMenuListener(new UpdateListener());
    }

    private class UpdateListener implements MenuListener {
        public void menuSelected(MenuEvent event){
            for (Action action : itemActions){
                action.putValue(ActionFactory.UPDATE_ACTION, null);
            }
        }
        public void menuDeselected(MenuEvent event){}
        public void menuCanceled(MenuEvent event){}
    }
}

