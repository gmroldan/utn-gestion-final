package edu.utn.gestion.ui.controller;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Sale;
import edu.utn.gestion.model.User;
import edu.utn.gestion.service.SaleService;
import edu.utn.gestion.service.generic.GenericService;
import edu.utn.gestion.ui.controller.generic.GenericController;

import java.util.List;

/**
 * Created by martin on 08/12/15.
 */
public class SaleController extends GenericController<Sale, Long> {
    private final SaleService service = SaleService.getInstance();

    @Override
    protected GenericService<Sale, Long> getService() {
        return this.service;
    }

    public List<Sale> searchByPeriod(User user, int month, int year) throws GestionAppException {
        return this.service.findByPeriod(user, month, year);
    }
}
