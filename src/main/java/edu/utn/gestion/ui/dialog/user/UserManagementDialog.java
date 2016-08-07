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
        if (ItemEvent.SELECTED == event.getStateChange()) {
            this.model.setObjectList(this.getAllUsers());
        } else {
            this.updateObjectList();
        }
    }

    @Override
    protected void updateObjectList() {
        this.model.setObjectList(this.getActiveUsers());
    }

    private List<User> getActiveUsers() {
        List<User> userList = new ArrayList<>();

        try {
            userList.addAll(this.controller.findActiveUsers());
        } catch (GestionAppException ex) {
            PopUpFactory.showErrorMessage(this, ex.getMessage());
            this.dispose();
        }

        return userList;
    }

    private List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try {
            userList.addAll(this.controller.findAll());
        } catch (GestionAppException ex) {
            PopUpFactory.showErrorMessage(this, ex.getMessage());
            this.dispose();
        }

        return userList;
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
