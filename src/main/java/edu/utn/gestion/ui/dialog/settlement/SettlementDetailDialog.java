package edu.utn.gestion.ui.dialog.settlement;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Employee;
import edu.utn.gestion.model.Settlement;
import edu.utn.gestion.ui.controller.SettlementController;
import edu.utn.gestion.ui.dialog.generic.GenericDialog;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 * Created by ASUS on 24/02/2016.
 */
public class SettlementDetailDialog extends GenericDialog {

    private String period;
    private Employee employee;

    private JLabel presenteeism;
    private JLabel antiqueness;
    private JLabel remuneration;

    private JLabel retire;
    private JLabel law19032;
    private JLabel socialCare;
    private JLabel faecys;
    private JLabel syndicate;

    private JLabel grossSalary;
    private JLabel totalDiscount;
    private JLabel netPay;

    protected SettlementController controller;
    protected Settlement currentSettlement;

    @Override
    protected void formWindowOpened(WindowEvent event) {
         calculateLabels();
    }

    public SettlementDetailDialog(JDialog parent, boolean modal, Settlement settlement) {
        super(parent, "", modal);
        this.currentSettlement = settlement;
    }

    @Override
    protected void initComponents() {

        this.presenteeism = new JLabel();
        this.remuneration = new JLabel();

        this.retire = new JLabel();
        this.law19032 = new JLabel();
        this.socialCare = new JLabel();
        this.faecys = new JLabel();
        this.syndicate = new JLabel();

        this.grossSalary = new JLabel();
        this.totalDiscount = new JLabel();
        this.netPay = new JLabel();

        createFormPanel();
    }

    private void calculateLabels() {
        this.grossSalary.setText(String.valueOf(currentSettlement.getGrossSalary()));
        this.presenteeism.setText(String.valueOf(currentSettlement.getPresenteeismAmount()));
        this.remuneration.setText(String.valueOf(currentSettlement.getRemunerationAmount()));

        this.retire.setText("-" + String.valueOf(currentSettlement.getRetireAmount()));
        this.law19032.setText("-" + String.valueOf(currentSettlement.getLaw19032()));
        this.socialCare.setText("-" + String.valueOf(currentSettlement.getSocialCare()));
        this.faecys.setText("-" + String.valueOf(currentSettlement.getFaecys()));
        this.syndicate.setText("-" + String.valueOf(currentSettlement.getSyndicate()));
        this.totalDiscount.setText("-" + String.valueOf(currentSettlement.getDiscount()));

        this.netPay.setText("$" + String.valueOf(currentSettlement.getNetPay()));
    }

    @Override
    protected void createFormPanel() {

        this.formPanel = new JPanel(new GridLayout(0,4));

        formPanel.add(new JLabel("Remunerations:"));
        formPanel.add(new JLabel(" "));
        formPanel.add(new JLabel(" "));
        formPanel.add(new JLabel(" "));

        formPanel.add(new JLabel("Gross Salary"));
        formPanel.add(new JLabel(" "));
        formPanel.add(new JLabel(" "));
        formPanel.add(this.grossSalary);

        formPanel.add(new JLabel("Presenteeism"));
        formPanel.add(new JLabel(" "));
        formPanel.add(new JLabel(" "));
        formPanel.add(this.presenteeism);

        formPanel.add(new JLabel(" "));
        formPanel.add(new JLabel("Total Remuneration "));
        formPanel.add(new JLabel(" "));
        formPanel.add(this.remuneration);

        formPanel.add(new JLabel(" "));
        formPanel.add(new JLabel(" "));
        formPanel.add(new JLabel(" "));
        formPanel.add(new JLabel(" "));

        formPanel.add(new JLabel("Discounts:"));
        formPanel.add(new JLabel(" "));
        formPanel.add(new JLabel(" "));
        formPanel.add(new JLabel(" "));

        formPanel.add((new JLabel("Retire")));
        formPanel.add((new JLabel(" ")));
        formPanel.add((new JLabel(" ")));
        formPanel.add((this.retire));

        formPanel.add(new JLabel("Law 19032"));
        formPanel.add(new JLabel(" "));
        formPanel.add((new JLabel(" ")));
        formPanel.add(this.law19032);

        formPanel.add(new JLabel("Social Care"));
        formPanel.add(new JLabel(" "));
        formPanel.add((new JLabel(" ")));
        formPanel.add(this.socialCare);

        formPanel.add(new JLabel("Faecys"));
        formPanel.add(new JLabel(" "));
        formPanel.add((new JLabel(" ")));
        formPanel.add(this.faecys);

        formPanel.add(new JLabel("syndicate"));
        formPanel.add(new JLabel(" "));
        formPanel.add((new JLabel(" ")));
        formPanel.add(this.syndicate);

        formPanel.add(new JLabel(" "));
        formPanel.add(new JLabel("Total Discount"));
        formPanel.add((new JLabel(" ")));
        formPanel.add(this.totalDiscount);

        formPanel.add(new JLabel(" "));
        formPanel.add(new JLabel(" "));
        formPanel.add(new JLabel(" "));
        formPanel.add(new JLabel(" "));

        formPanel.add(new JLabel(" "));
        formPanel.add(new JLabel("Net Pay"));
        formPanel.add((new JLabel(" ")));
        formPanel.add(this.netPay);

    }

    @Override
    protected void initModel() {

    }

    @Override
    protected void setObjectData() throws GestionAppException {

    }

    @Override
    protected void btnAcceptActionPerformed(ActionEvent event) {


    }

    @Override
    protected void btnCancelActionPerformed(ActionEvent event) {


    }
}
