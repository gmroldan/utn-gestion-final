package edu.utn.gestion.ui.controller;

import edu.utn.gestion.model.Customer;
import edu.utn.gestion.service.CustomerService;
import edu.utn.gestion.ui.controller.generic.GenericController;

public class CustomerController extends GenericController<Customer, Long> {

    public CustomerController() {
        super(CustomerService.getInstance());
    }
    
}
