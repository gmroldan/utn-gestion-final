package edu.utn.gestion.ui.dialog.book;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Category;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.StringUtils;

public class NewBookDialog extends AbstractBookDialog {

    public NewBookDialog(JDialog parent, boolean modal) {
        super(parent, modal, null);
    }

    @Override
    protected void btnAcceptActionPerformed(ActionEvent event) {
        try {
            this.setBookData();
            this.controller.saveBook(this.currentBook);
            this.dispose();
        } catch (GestionAppException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void btnCancelActionPerformed(ActionEvent event) {
        this.dispose();
    }    

    private void setBookData() throws GestionAppException {
        this.currentBook.setIsbn(this.txtIsbn.getText());
        this.currentBook.setTitle(this.txtTitle.getText());
        this.currentBook.setAuthor(this.txtIsbn.getText());
        this.currentBook.setCategory((Category) this.cmbCategory.getSelectedItem());
        this.currentBook.setDescription(this.txtDescription.getText());
        this.currentBook.setEditorial(this.txtIsbn.getText());
        
        
        String priceString = this.txtPrice.getText();
        String stockString = this.txtStock.getText();
        
        
        if (StringUtils.isNotEmpty(priceString)) {
            this.currentBook.setPrice(Double.parseDouble(priceString));
        } else {
            throw new GestionAppException("Please review the price value.");
        }
        
        if (StringUtils.isNotEmpty(stockString)) {
            this.currentBook.setStock(Integer.parseInt(stockString));
        } else {
            throw new GestionAppException("Please review the stock value.");
        }
    }
}
