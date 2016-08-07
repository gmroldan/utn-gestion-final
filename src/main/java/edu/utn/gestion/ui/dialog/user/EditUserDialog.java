package edu.utn.gestion.ui.dialog.user;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.User;
import edu.utn.gestion.ui.controller.UserController;
import edu.utn.gestion.ui.util.PopUpFactory;

import javax.swing.JDialog;
import java.awt.event.ActionEvent;

/**
 * Created by martin on 07/08/16.
 */
public class EditUserDialog extends AbstractUserDialog {
    private static final String WINDOW_TITLE = "Edit User";

    /**
     * Class contructor.
     *
     * @param parent
     * @param controller
     * @param user
     */
    public EditUserDialog(JDialog parent, UserController controller, User user) {
        super(parent, WINDOW_TITLE, controller, user);
    }

    @Override
    protected void btnAcceptActionPerformed(ActionEvent event) {
        try {
            this.setObjectData();
            this.controller.update(this.currentUser);
            this.dispose();
        } catch (GestionAppException ex) {
            PopUpFactory.showErrorMessage(this, ex.getMessage());
        }
    }

    @Override
    protected void btnCancelActionPerformed(ActionEvent event) {
        this.dispose();
    }
}
