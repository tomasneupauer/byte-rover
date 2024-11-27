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
    private ActionModel actionModel;

    public MenuFactory(ActionModel model){
        actionModel = model;
    }

    public JPopupMenu newPopupMenu(Object rootKey){
        MenuItemAction rootAction = actionModel.getAction(rootKey);
        JPopupMenu actionPopupMenu = new ActionPopupMenu();
        for (Object childKey : rootAction.getSubActionKeys()){
            MenuItemAction childAction = actionModel.getAction(childKey);
            if (childAction.isMenu()){
                actionPopupMenu.add(newMenu(childKey));
            }
            else {
                actionPopupMenu.add(new JMenuItem(childAction));
            }
            if (childAction.isSeparated()){
                actionPopupMenu.addSeparator();
            }
        }
        return actionPopupMenu;
    }

    public JMenu newMenu(Object rootKey){
        MenuItemAction rootAction = actionModel.getAction(rootKey);
        JMenu actionMenu = new ActionMenu(rootAction);
        for (Object childKey : rootAction.getSubActionKeys()){
            MenuItemAction childAction = actionModel.getAction(childKey);
            if (childAction.isMenu()){
                actionMenu.add(newMenu(childKey));
            }
            else {
                actionMenu.add(new JMenuItem(childAction));
            }
            if (childAction.isSeparated()){
                actionMenu.addSeparator();
            }
        }
        return actionMenu;
    }

    private void updatePopupItems(JPopupMenu popup){
        for (MenuElement item : popup.getSubElements()){
            JMenuItem menuItem = (JMenuItem) item;
            MenuItemAction action = (MenuItemAction) menuItem.getAction();
            action.actionUpdate();
            menuItem.setVisible(action.isVisible());
        }
    }

    private class ActionPopupMenu extends JPopupMenu {
        @Override
        public void show(Component invoker, int x, int y){
            updatePopupItems(this);
            super.show(invoker, x, y);
        }
    }

    private class ActionMenu extends JMenu {
        public ActionMenu(Action menu){
            super(menu);
            addMenuListener(new MenuListener(){
                public void menuSelected(MenuEvent event){
                    updatePopupItems(getPopupMenu());
                }
                public void menuDeselected(MenuEvent event){}
                public void menuCanceled(MenuEvent event){}
            });
        }
    }
}

