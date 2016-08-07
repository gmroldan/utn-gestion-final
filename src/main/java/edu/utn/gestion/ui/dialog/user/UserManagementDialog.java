package edu.utn.gestion.ui.dialog.user;

import edu.utn.gestion.model.User;
import edu.utn.gestion.ui.controller.UserController;
import edu.utn.gestion.ui.controller.generic.GenericController;
import edu.utn.gestion.ui.dialog.generic.GenericManagementDialog;

import java.awt.Frame;

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
    }

    @Override
    protected void showEditObjectDialog(User object) {

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
