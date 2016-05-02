package edu.utn.gestion.ui.controller;

import edu.utn.gestion.model.Customer;
import edu.utn.gestion.service.CustomerService;
import edu.utn.gestion.service.generic.GenericService;
import edu.utn.gestion.ui.controller.generic.GenericController;

public class CustomerController extends GenericController<Customer, Long> {
    private final CustomerService service = CustomerService.getInstance();

    @Override
    protected GenericService<Customer, Long> getService() {
        return this.service;
    }
}
