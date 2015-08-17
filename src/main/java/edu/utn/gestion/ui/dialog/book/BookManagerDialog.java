package edu.utn.gestion.ui.dialog.book;

import edu.utn.gestion.ui.controller.BookController;
import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Book;
import edu.utn.gestion.ui.util.PopUpFactory;
import java.awt.Frame;
import java.util.Set;
import javax.swing.JDialog;

/**
 *
 * @author gerardo
 */
public class BookManagerDialog extends JDialog {
    private final BookController controller = BookController.getInstance();
    private final BookTableModel model = new BookTableModel();

    /**
     * Creates new form BookManagerDialog
     * 
     * @param parent
     * @param modal
     */
    public BookManagerDialog(Frame parent, boolean modal) {
        super(parent, modal);
        this.initComponents();
        this.setLocationRelativeTo(parent);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableBooks = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Books");
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tableBooks.setModel(this.model);
        tableBooks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBooksMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableBooks);

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnDelete, btnNew});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.updateBookList();
    }//GEN-LAST:event_formWindowOpened

    private void tableBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBooksMouseClicked
        int rowIndex = this.tableBooks.getSelectedRow();
        int columnIndex = this.tableBooks.getSelectedColumn();
        
        if (rowIndex >= 0) {
            if (columnIndex == 0) {
                this.editSelectedObject(rowIndex, columnIndex);
            } else {
                this.refreshSelectedObjects();
            }
        }
    }//GEN-LAST:event_tableBooksMouseClicked

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        new NewBookDialog(this, true).setVisible(true);
    }//GEN-LAST:event_btnNewActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        this.updateBookList();
    }//GEN-LAST:event_formWindowGainedFocus

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        this.deleteSelectedObjects();        
        this.updateBookList();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void updateBookList() {
        try {
             this.model.setObjectList(this.controller.findAll());
        } catch (GestionAppException ex) {
            PopUpFactory.showErrorMessage(this, ex.getMessage());
        }
    }
    
    private void editSelectedObject(int rowIndex, int columnIndex) {
        Long id = (Long) this.model.getValueAt(rowIndex, columnIndex);

        try {
            Book book = this.controller.findOne(id);
            new EditBookDialog(this, true, book).setVisible(true);
        } catch (GestionAppException ex) {
            PopUpFactory.showErrorMessage(this, ex.getMessage());
        }
    }
    
    private void refreshSelectedObjects() {
        int[] selectedRows = this.tableBooks.getSelectedRows();
        this.model.refreshSelectedObjects(selectedRows);
    }
    
    // TODO: this method should be optimized.
    private void deleteSelectedObjects() {
        Set<Book> selectedBooks = this.model.getSelectedObjects();
        
        for (Book book : selectedBooks) {
            try {
                this.controller.deleteBook(book);
            } catch (GestionAppException ex) {
                PopUpFactory.showErrorMessage(this, ex.getMessage());
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton btnDelete;
    protected javax.swing.JButton btnNew;
    protected javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JTable tableBooks;
    // End of variables declaration//GEN-END:variables
}
