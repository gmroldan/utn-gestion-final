package edu.utn.gestion.ui.dialog.book;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Book;
import edu.utn.gestion.model.Category;
import edu.utn.gestion.ui.controller.BookController;
import edu.utn.gestion.ui.dialog.generic.GenericDialog;
import edu.utn.gestion.ui.util.FormUtils;
import edu.utn.gestion.ui.util.IFormat;
import edu.utn.gestion.ui.util.PopUpFactory;

import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import org.apache.commons.collections4.CollectionUtils;

public abstract class AbstractBookDialog extends GenericDialog {
    protected JComboBox cmbCategory;
    protected JLabel lblISBN;
    protected JLabel lblTitle;
    protected JLabel lblCategory;
    protected JLabel lblAuthor;
    protected JLabel lblEditorial;
    protected JLabel lblPrice;
    protected JLabel lblStock;
    protected JLabel lblDescription;
    protected JLabel lblMinimumStock;
    protected JScrollPane jScrollPane1;
    protected JTextField txtAuthor;
    protected JTextArea txtDescription;
    protected JTextField txtEditorial;
    protected JFormattedTextField txtIsbn;
    protected JFormattedTextField txtPrice;
    protected JSpinner txtCurrentStock;
    protected JTextField txtTitle;
    protected JSpinner txtMinimumStock;

    protected final BookController controller;
    protected Book currentBook;
    protected DefaultComboBoxModel categoriesModel;

    /**
     * Creates new form AbstractBookDialog
     * 
     * @param parent
     * @param windowTitle
     * @param modal
     * @param book
     */
    public AbstractBookDialog(JDialog parent, String windowTitle
            , boolean modal, BookController controller, Book book) {
        super(parent, windowTitle, modal);
        this.controller = controller;
        this.currentBook = book;
        this.initModel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @Override
    protected void initComponents() {
        this.lblISBN = new JLabel("ISBN");
        this.lblTitle = new JLabel("Title");
        this.lblCategory = new JLabel("Category");
        this.lblAuthor = new JLabel("Author");
        this.lblEditorial = new JLabel("Editorial");
        this.lblPrice = new JLabel("Price");
        this.lblStock = new JLabel("Stock");
        this.lblDescription = new JLabel("Description");
        this.lblMinimumStock = new JLabel("Min Stock");

        this.txtTitle = new JTextField();
        this.cmbCategory = new JComboBox();
        this.txtAuthor = new JTextField();
        this.txtEditorial = new JTextField();
        this.jScrollPane1 = new JScrollPane();
        this.txtDescription = new JTextArea();
        this.txtPrice = new JFormattedTextField();
        this.txtCurrentStock = new JSpinner();
        this.txtIsbn = new JFormattedTextField();
        this.txtMinimumStock = new JSpinner();

        this.lblISBN.setLabelFor(this.txtIsbn);
        this.lblTitle.setLabelFor(this.txtTitle);
        this.lblCategory.setLabelFor(this.cmbCategory);
        this.lblAuthor.setLabelFor(this.txtAuthor);
        this.lblEditorial.setLabelFor(this.txtEditorial);
        this.lblPrice.setLabelFor(this.txtPrice);
        this.lblStock.setLabelFor(this.txtCurrentStock);
        this.lblDescription.setLabelFor(this.txtDescription);
        this.lblMinimumStock.setLabelFor(this.txtMinimumStock);

        this.txtDescription.setColumns(20);
        this.txtDescription.setRows(5);

        this.jScrollPane1.setViewportView(this.txtDescription);

        this.txtPrice.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat(IFormat.PRICE_FORMAT))));
        this.txtIsbn.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat(IFormat.NUMERIC_FORMAT))));

        this.createFormPanel();
    }

    @Override
    protected void createFormPanel() {
        this.formPanel = new JPanel(new GridBagLayout());

        FormUtils.addLabel(this.lblISBN, this.formPanel);
        FormUtils.addLastField(this.txtIsbn, this.formPanel);
        FormUtils.addLabel(this.lblCategory, this.formPanel);
        FormUtils.addLastField(this.cmbCategory, this.formPanel);
        FormUtils.addLabel(this.lblTitle, this.formPanel);
        FormUtils.addLastField(this.txtTitle, this.formPanel);
        FormUtils.addLabel(this.lblAuthor, this.formPanel);
        FormUtils.addLastField(this.txtAuthor, this.formPanel);
        FormUtils.addLabel(this.lblEditorial, this.formPanel);
        FormUtils.addLastField(this.txtEditorial, this.formPanel);
        FormUtils.addLabel(this.lblPrice, this.formPanel);
        FormUtils.addLastField(this.txtPrice, this.formPanel);
        FormUtils.addLabel(this.lblStock, this.formPanel);
        FormUtils.addLastField(this.txtCurrentStock, this.formPanel);
        FormUtils.addLabel(this.lblMinimumStock, this.formPanel);
        FormUtils.addLastField(this.txtMinimumStock, this.formPanel);
        FormUtils.addLabel(this.lblDescription, this.formPanel);
        FormUtils.addLastField(this.txtDescription, this.formPanel);
    }

    @Override
    protected void formWindowOpened(WindowEvent event) {
        if (this.currentBook != null) {
            this.txtIsbn.setText(this.currentBook.getIsbn());
            this.txtTitle.setText(this.currentBook.getTitle());
            this.txtAuthor.setText(this.currentBook.getAuthor());
            this.txtEditorial.setText(this.currentBook.getEditorial());
            this.txtPrice.setValue(this.currentBook.getPrice());
            this.txtCurrentStock.setValue(this.currentBook.getCurrentStock());
            this.txtMinimumStock.setValue(this.currentBook.getMinimumStock());
            this.txtDescription.setText(this.currentBook.getDescription());
            this.cmbCategory.setSelectedItem(this.currentBook.getCategory());
        } else {
            this.currentBook = new Book();
        }
    }//GEN-LAST:event_formWindowOpened

    @Override
    protected void initModel() {
        List<Category> categories = null;
        
        try {
            categories = this.controller.findAllCategories();
        } catch (GestionAppException ex) {
            PopUpFactory.showErrorMessage(this, ex.getMessage());
            this.dispose();
        }
        
        if (CollectionUtils.isNotEmpty(categories)) {
            this.categoriesModel = new DefaultComboBoxModel(categories.toArray());
            this.cmbCategory.setModel(this.categoriesModel);
        }
    }

    @Override
    protected void setObjectData() throws GestionAppException {
        this.currentBook.setIsbn(this.txtIsbn.getText());
        this.currentBook.setTitle(this.txtTitle.getText());
        this.currentBook.setAuthor(this.txtAuthor.getText());
        this.currentBook.setCategory((Category) this.cmbCategory.getSelectedItem());
        this.currentBook.setDescription(this.txtDescription.getText());
        this.currentBook.setEditorial(this.txtEditorial.getText());
        this.currentBook.setPrice(((Number) this.txtPrice.getValue()).doubleValue());
        this.currentBook.setCurrentStock(((Number) this.txtCurrentStock.getValue()).intValue());
        this.currentBook.setMinimumStock(((Number) this.txtMinimumStock.getValue()).intValue());
    }
}
