package edu.utn.gestion.ui.dialog.employee;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Employee;
import edu.utn.gestion.service.EmployeeService;
import edu.utn.gestion.ui.controller.EmployeeController;
import edu.utn.gestion.ui.util.PopUpFactory;
import org.apache.commons.collections4.CollectionUtils;

import javax.swing.*;
import java.util.List;

/**
 * Created by ASUS on 18/02/2016.
 */
public class ComboEmployees extends JComboBox{

    public ComboEmployees() {
        initModel();
    }

    private void initModel() {
        List<Employee> employees = null;

        try {
            EmployeeController employeeController = new EmployeeController();
            employees = employeeController.findAll();

        } catch (GestionAppException ex) {
            PopUpFactory.showErrorMessage(this, ex.getMessage());
        }

        if (CollectionUtils.isNotEmpty(employees)) {
            this.setModel(new DefaultComboBoxModel(employees.toArray()));
        }
    }
}
