package edu.utn.gestion.ui.controller;

import edu.utn.gestion.model.Supplier;
import edu.utn.gestion.service.SupplierService;
import edu.utn.gestion.ui.controller.generic.GenericController;

/**
 * Created by martin on 18/12/15.
 */
public class SupplierController extends GenericController<Supplier, Long> {

    /**
     * Creates a new instance of <code>SupplierController</code>.
     */
    public SupplierController() {
        super(SupplierService.getInstance());
    }
}
