package edu.utn.gestion.ui.dialog.book;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Book;
import edu.utn.gestion.model.Category;
import edu.utn.gestion.ui.controller.BookController;
import edu.utn.gestion.ui.dialog.generic.GenericDialog;
import edu.utn.gestion.ui.util.IFormat;
import edu.utn.gestion.ui.util.PopUpFactory;
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
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
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
    protected JScrollPane jScrollPane1;
    protected JTextField txtAuthor;
    protected JTextArea txtDescription;
    protected JTextField txtEditorial;
    protected JFormattedTextField txtIsbn;
    protected JFormattedTextField txtPrice;
    protected JSpinner txtStock;
    protected JTextField txtTitle;

    protected final BookController controller;
    protected Book currentBook;
    protected DefaultComboBoxModel categoriesModel;

    /**
     * Creates new form AbstractBookDialog
     * 
     * @param parent
     * @param modal
     * @param book
     */
    public AbstractBookDialog(JDialog parent, boolean modal, BookController controller, Book book) {
        super(parent, modal);
        this.controller = controller;
        this.currentBook = book;
        this.initModel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @Override
    protected void initComponents() {
        this.lblISBN = new JLabel();
        this.lblTitle = new JLabel();
        this.txtTitle = new JTextField();
        this.lblCategory = new JLabel();
        this.cmbCategory = new JComboBox();
        this.lblAuthor = new JLabel();
        this.txtAuthor = new JTextField();
        this.lblEditorial = new JLabel();
        this.txtEditorial = new JTextField();
        this.lblPrice = new JLabel();
        this.lblStock = new JLabel();
        this.lblDescription = new JLabel();
        this.jScrollPane1 = new JScrollPane();
        this.txtDescription = new JTextArea();
        this.txtPrice = new JFormattedTextField();
        this.txtStock = new JSpinner();
        this.txtIsbn = new JFormattedTextField();
        this.formPanel = new JPanel();

        this.lblISBN.setText("ISBN");
        this.lblTitle.setText("Title");
        this.lblCategory.setText("Category");
        this.lblAuthor.setText("Author");
        this.lblEditorial.setText("Editorial");
        this.lblPrice.setText("Price");
        this.lblStock.setText("Stock");
        this.lblDescription.setText("Description");

        this.txtDescription.setColumns(20);
        this.txtDescription.setRows(5);

        this.jScrollPane1.setViewportView(this.txtDescription);

        this.txtPrice.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat(IFormat.PRICE_FORMAT))));
        this.txtIsbn.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat(IFormat.NUMERIC_FORMAT))));

        this.createFormPanel();

        getContentPane().add(this.formPanel);
        pack();
    }

    // TODO: Improve this method.
    @Override
    protected void createFormPanel() {
        GroupLayout layout = new GroupLayout(this.formPanel);
        this.formPanel.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(this.lblAuthor)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(this.txtAuthor, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(this.lblPrice)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(this.txtPrice)))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(this.lblEditorial)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(this.txtEditorial))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(this.lblStock)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(this.txtStock, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 101, Short.MAX_VALUE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(this.lblDescription)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(this.jScrollPane1)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(this.btnAccept)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(this.btnCancel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(this.lblTitle)
                                                        .addComponent(this.lblISBN))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(this.txtIsbn)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(this.lblCategory)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(this.cmbCategory, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(txtTitle))))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(this.lblISBN)
                                        .addComponent(this.lblCategory)
                                        .addComponent(this.cmbCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(this.txtIsbn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(this.lblTitle)
                                        .addComponent(this.txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(this.lblAuthor)
                                        .addComponent(this.txtAuthor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(this.lblEditorial)
                                        .addComponent(this.txtEditorial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(this.lblPrice)
                                                .addComponent(this.lblStock)
                                                .addComponent(this.txtStock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(this.txtPrice, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(this.lblDescription)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(this.jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(this.btnCancel)
                                        .addComponent(this.btnAccept))
                                .addContainerGap())
        );
    }

    @Override
    protected void formWindowOpened(WindowEvent event) {
        if (this.currentBook != null) {
            this.txtIsbn.setText(this.currentBook.getIsbn());
            this.txtTitle.setText(this.currentBook.getTitle());
            this.txtAuthor.setText(this.currentBook.getAuthor());
            this.txtEditorial.setText(this.currentBook.getEditorial());
            this.txtPrice.setValue(this.currentBook.getPrice());
            this.txtStock.setValue(this.currentBook.getStock());
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
        this.currentBook.setStock(((Number) this.txtStock.getValue()).intValue());
    }
}
