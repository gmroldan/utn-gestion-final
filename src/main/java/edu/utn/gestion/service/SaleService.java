package edu.utn.gestion.service;

import edu.utn.gestion.dao.SaleDAO;
import edu.utn.gestion.exception.FileGenerationException;
import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Sale;
import edu.utn.gestion.model.SaleDetail;
import edu.utn.gestion.service.generic.GenericService;
import edu.utn.gestion.service.util.InvoiceFactory;
import org.apache.commons.lang3.Validate;

import java.util.List;

/**
 * Created by martin on 08/12/15.
 */
public class SaleService extends GenericService<Sale, Long> {
    private static final SaleService INSTANCE = new SaleService();

    private SaleService() {
        super(SaleDAO.getInstance());
    }

    public static SaleService getInstance() {
        return INSTANCE;
    }

    @Override
    public Long save(Sale sale) throws GestionAppException {
        Validate.notNull(sale, "Cannot save a null sale.");

        this.updateBookStock(sale);

        Long saleId = super.save(sale);

        if (saleId != null && saleId > 0) {
            Sale currentSale = this.findOne(saleId);

            if (currentSale != null) {
                try {
                    InvoiceFactory.generateInvoice(currentSale);
                } catch (FileGenerationException ex) {
                    throw new GestionAppException(ex);
                }
            }
        }

        return saleId;
    }

    private void updateBookStock(Sale sale) throws GestionAppException {
        List<SaleDetail> details = sale.getSaleDetails();

        for (SaleDetail detail : details) {
            BookService.getInstance().decreaseStock(detail.getBook().getIsbn(), detail.getQuantity());
        }
    }

    @Override
    public List<Sale> findBySearch(String searchString) throws GestionAppException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
