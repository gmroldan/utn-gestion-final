package edu.utn.gestion.ui.controller;

import edu.utn.gestion.model.Sale;
import edu.utn.gestion.service.SaleService;
import edu.utn.gestion.ui.controller.generic.GenericController;

/**
 * Created by martin on 08/12/15.
 */
public class SaleController extends GenericController<Sale, Long> {
    public SaleController() {
        super(SaleService.getInstance());
    }
}
