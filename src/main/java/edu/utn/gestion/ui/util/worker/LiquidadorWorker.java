package edu.utn.gestion.ui.util.worker;

import edu.utn.gestion.model.Employee;
import edu.utn.gestion.model.Settlement;
import edu.utn.gestion.service.util.liquidacion.LiquidadorDeSueldos;
import edu.utn.gestion.ui.controller.SettlementController;
import edu.utn.gestion.ui.dialog.settlement.SettlementDialog;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import java.awt.Cursor;
import java.util.List;

/**
 * Created by ASUS on 03/08/2016.
 */
public class LiquidadorWorker extends SwingWorker<String,Object> {
    private static final String MESSAGE = "Liquidación Finalizada";
    private final List<Employee> employees;
    private final String period;
    private final SettlementController controller;
    private SettlementDialog dialog;

    /**
     * Class constuctor.
     *
     * @param employees
     * @param period
     * @param settlementDialog
     */
    public LiquidadorWorker(final List<Employee> employees, final String period, SettlementDialog settlementDialog) {
        this.employees = employees;
        this.dialog = settlementDialog;
        this.period = period;
        this.controller = new SettlementController();
    }

    @Override
    protected String doInBackground() throws Exception {
        Settlement settlement;
        List<Settlement> settlementList;
        String cuit;
        String cuitInDB;
        String period;
        String periodInDB;

        for (Employee employee : this.employees) {
            settlement = LiquidadorDeSueldos.generarLiquidacion(employee, this.period);

            //this is to overwrite previous settlement in case it has one
            settlementList = this.controller.findAll();

            for (Settlement settlementAux : settlementList) {
                cuit = settlement.getEmployee().getCuit();
                cuitInDB = settlementAux.getEmployee().getCuit();

                period = settlement.getPeriod();
                periodInDB = settlementAux.getPeriod();

                if (cuit.equals(cuitInDB) && period.equals(periodInDB)) {
                    this.controller.delete(settlementAux);
                    break;
                }
            }

            this.controller.save(settlement);

        }

        return "done";
    }

    @Override
    protected void done() {
        this.dialog.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        this.dialog.setEnabled(true);
        JOptionPane.showMessageDialog(this.dialog, MESSAGE);

    }
}
