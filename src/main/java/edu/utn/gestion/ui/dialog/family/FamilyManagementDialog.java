package edu.utn.gestion.ui.dialog.family;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Employee;
import edu.utn.gestion.model.Family;
import edu.utn.gestion.ui.controller.EmployeeController;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

/**
 * Created by ASUS on 07/08/2016.
 */
public class FamilyManagementDialog extends JDialog {

    private final EmployeeController controller;
    private JPanel panel;
    private JTable table;
    private FamilyTableModel tableModel;
    private Employee currentEmployee;
    private JButton addBtn;
    private JButton updateBtn;
    private JButton deleteBtn;
    private JButton saveBtn;

    public FamilyManagementDialog(JDialog parent, boolean modal, Employee currentEmployee) {
        super(parent, modal);

        this.controller = new EmployeeController();
        this.setSize(450,300);
        this.tableModel = new FamilyTableModel();
        this.currentEmployee = currentEmployee;

        init();

        createView();
    }

    private void init() {
        tableModel.setObjectList(this.currentEmployee.getFamilies());
        table = new JTable(this.tableModel);

        this.addBtn = new JButton(" New  ");
        this.addBtn.addActionListener(event -> this.newActionPerformed());

        this.updateBtn = new JButton("Update");
        this.updateBtn.addActionListener(event -> this.updateActionPerformed());

        this.deleteBtn = new JButton("Delete");
        this.deleteBtn.addActionListener(event -> this.deleteActionPerformed());

        this.saveBtn = new JButton("Save Changes");
        this.saveBtn.addActionListener(event -> this.saveActionPerformed());

    }

    private void createView() {

        BorderLayout layout = new BorderLayout();
        this.panel = new JPanel(layout);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setSize(400,200);
        this.panel.add(scrollPane, BorderLayout.CENTER);

        //ABM
        JPanel editPanel = new JPanel(new FlowLayout());

        editPanel.add(addBtn);
        editPanel.add(updateBtn);
        editPanel.add(deleteBtn);
        editPanel.add(saveBtn);

        this.panel.add(editPanel, BorderLayout.PAGE_END);

        this.setContentPane(this.panel);
    }

    private void saveActionPerformed() {

        List<Family> familyList = this.tableModel.getObjectList();
        this.currentEmployee.getFamilies().clear();

        for (Family family : familyList) {
            currentEmployee.getFamilies().add(family);
        }

        try {
            this.controller.update(currentEmployee);
        } catch (GestionAppException e) {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el grupo familiar del empleado " + currentEmployee.getName());
        }
    }

    private void newActionPerformed() {
        System.out.println("New");
        new NewFamilyDialog(this,true,this.tableModel).setVisible(true);

    }

    private void updateActionPerformed() {
        JOptionPane.showMessageDialog(this,"Not Implemented yet.");
    }

    private void deleteActionPerformed() {
        JOptionPane.showMessageDialog(this,"Not Implemented yet.");
    }
}
