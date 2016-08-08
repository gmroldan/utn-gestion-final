package edu.utn.gestion.ui.dialog.user;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.User;
import edu.utn.gestion.ui.controller.UserController;
import edu.utn.gestion.ui.controller.generic.GenericController;
import edu.utn.gestion.ui.dialog.generic.GenericManagementDialog;
import edu.utn.gestion.ui.util.PopUpFactory;

import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by martin on 26/07/16.
 */
public class UserManagementDialog extends GenericManagementDialog<User, Long> {
    private static final String WINDOW_TITLE = "Users Management";
    private UserController controller;

    /**
     * Creates new form BookManagerDialog
     *
     * @param parent
     * @param modal
     * @parm model
     */
    public UserManagementDialog(Frame parent, boolean modal) {
        super(parent, WINDOW_TITLE, modal, new UserTableModel());
        this.controller = new UserController();
        this.checkBoxShowDeletedObjects.setVisible(true);
    }

    protected void checkBoxChanged(ItemEvent event) {
        this.updateObjectList();
    }

    @Override
    protected void updateObjectList() {
        try {
            if (this.checkBoxShowDeletedObjects.isSelected())
                this.model.setObjectList(this.controller.findAll());
            else
                this.model.setObjectList(this.controller.findActiveUsers());
        } catch (GestionAppException ex) {
            PopUpFactory.showErrorMessage(this, ex.getMessage());
            this.dispose();
        }
    }

    @Override
    protected void showEditObjectDialog(User user) {
        new EditUserDialog(this, this.controller, user).setVisible(true);
    }

    @Override
    protected void showNewObjectDialog() {
        new NewUserDialog(this, this.controller, null).setVisible(true);
    }

    @Override
    protected GenericController<User, Long> getController() {
        return this.controller;
    }
}
