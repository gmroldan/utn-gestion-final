package edu.utn.gestion.ui.controller;

import edu.utn.gestion.model.Supplier;
import edu.utn.gestion.service.SupplierService;
import edu.utn.gestion.service.generic.GenericService;
import edu.utn.gestion.ui.controller.generic.GenericController;

/**
 * Created by martin on 18/12/15.
 */
public class SupplierController extends GenericController<Supplier, Long> {
    private final SupplierService service = SupplierService.getInstance();

    @Override
    protected GenericService<Supplier, Long> getService() {
        return this.service;
    }
}
