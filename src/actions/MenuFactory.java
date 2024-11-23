package org.berandev.byterover;

import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.Action;
import javax.swing.MenuElement;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;
import java.awt.Component;

public class MenuFactory {
    public static JPopupMenu newPopupMenu(MenuItemAction[] items){
        JPopupMenu actionPopupMenu = new ActionPopupMenu();
        for (MenuItemAction action : items){
            actionPopupMenu.add(new JMenuItem(action));
            if (action.isSeparated()){
                actionPopupMenu.addSeparator();
            }
        }
        return actionPopupMenu;
    }

    public static JMenu newMenu(MenuItemAction[] items, Action menu){
        JMenu actionMenu = new ActionMenu(menu);
        for (MenuItemAction action : items){
            actionMenu.add(new JMenuItem(action));
            if (action.isSeparated()){
                actionMenu.addSeparator();
            }
        }
        return actionMenu;
    }

    public static void updateMenuElements(MenuElement menu){
        MenuElement[] subElements = menu.getSubElements();
        for (int i=0; i<subElements.length; i++){
            if (subElements[i] instanceof JPopupMenu){
                updateMenuElements(subElements[i]);
            }
            else {
                updateMenuItem((JMenuItem) subElements[i]);
            }
        }
    }

    private static void updateMenuItem(JMenuItem item){
        MenuItemAction action = (MenuItemAction) item.getAction();
        action.actionUpdate();
        item.setVisible(action.isVisible());
    }
}

class ActionPopupMenu extends JPopupMenu {
    @Override
    public void show(Component invoker, int x, int y){
        MenuFactory.updateMenuElements(this);
        super.show(invoker, x, y);
    }
}

class ActionMenu extends JMenu {
    public ActionMenu(Action menu){
        super(menu);
        addMenuListener(new UpdateListener());
    }

    private class UpdateListener implements MenuListener {
        public void menuSelected(MenuEvent event){
            MenuFactory.updateMenuElements(ActionMenu.this);
        }
        public void menuDeselected(MenuEvent event){}
        public void menuCanceled(MenuEvent event){}
    }
}

