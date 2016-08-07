package edu.utn.gestion.ui.dialog.family;

import edu.utn.gestion.model.Family;
import edu.utn.gestion.ui.util.DateLabelFormatter;
import edu.utn.gestion.ui.util.FormUtils;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by ASUS on 07/08/2016.
 */
public class NewFamilyDialog extends JDialog {

    private JLabel lblName;
    private JLabel lblDNI;
    private JLabel lblBound;
    private JLabel lblBirthDate;

    private JTextField txtName;
    private JTextField txtDNI;
    private JTextField txtBound;
    private JDatePickerImpl birthDatePicker;

    private JButton saveBtn;

    private FamilyTableModel tableModel;

    private JPanel panel;

    public NewFamilyDialog(JDialog parent, boolean modal, FamilyTableModel tableModel) {
        super(parent, modal);

        this.setSize(300,300);

        this.tableModel = tableModel;

        init();

        createView();
    }

    private void init() {

        this.lblName = new JLabel("Name: ");
        this.lblDNI = new JLabel("DNI: ");
        this.lblBound = new JLabel("Bound: ");
        this.lblBirthDate = new JLabel("Birth Date");

        this.txtName = new JTextField(30);
        this.txtDNI = new JTextField(30);
        this.txtBound = new JTextField(30);

        this.saveBtn = new JButton("Save");
        this.saveBtn.addActionListener(event -> this.saveBtnActionPerformed());
        initModel();

    }

    private void initModel() {
        UtilDateModel model = new UtilDateModel();
        Date date = new Date();
        model.setDate(date.getYear()+1900,date.getMonth(),date.getDate());
        model.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        this.birthDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
    }

    private void createView() {
        this.panel = new JPanel(new GridBagLayout());

        FormUtils.addLabel(this.lblName, this.panel);
        FormUtils.addLastField(this.txtName, this.panel);

        FormUtils.addLabel(this.lblDNI, this.panel);
        FormUtils.addLastField(this.txtDNI, this.panel);

        FormUtils.addLabel(this.lblBound, this.panel);
        FormUtils.addLastField(this.txtBound, this.panel);

        FormUtils.addLabel(this.lblBirthDate, this.panel);
        FormUtils.addLastField(this.birthDatePicker, this.panel);

        FormUtils.addMiddleField(this.saveBtn,this.panel);

        this.setContentPane(this.panel);
    }

    private void saveBtnActionPerformed() {

        Family family = new Family();

        family.setName(this.txtName.getText());
        family.setDni(this.txtDNI.getText());
        family.setBound(this.txtBound.getText());
        family.setBirthDate((Date) this.birthDatePicker.getModel().getValue());

        //TODO agregar validaciones, mensajitos de error y toda la musica
        this.tableModel.addObject(family);
    }

}
