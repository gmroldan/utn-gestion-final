package edu.utn.gestion.ui.controller;

import edu.utn.gestion.model.Employee;
import edu.utn.gestion.model.Settlement;
import edu.utn.gestion.service.SettlementService;
import edu.utn.gestion.service.generic.GenericService;
import edu.utn.gestion.ui.controller.generic.GenericController;

/**
 * Created by ASUS on 25/02/2016.
 */
public class SettlementController extends GenericController<Settlement, Long> {

    public SettlementController() {
        super(SettlementService.getInstance());
    }

    public double getGrossSalary(Employee employee) {
        return 10000;
    }
}
