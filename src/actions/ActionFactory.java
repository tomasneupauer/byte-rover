package org.berandev.byterover;

import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.event.ActionEvent;

public class ActionFactory {
    public static MenuItemAction[] newStructureTreeActions(StructureTree tree){
        MenuItemAction[] actions = {
            new CreateNewPageAction(tree),
            new CreateNewGroupAction(tree),
            new SetDefaultTreeNodeAction(tree),
            new RenameTreeNodeAction(tree),
            new DeleteTreeNodeAction(tree)
        };
        return actions;
    }

    public static Action newMenuAction(String name){
        return new AbstractAction(name){
            public void actionPerformed(ActionEvent event) {};
        };
    }
}

