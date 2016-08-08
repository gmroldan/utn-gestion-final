package edu.utn.gestion.service;

import edu.utn.gestion.dao.SaleDAO;
import edu.utn.gestion.dao.generic.GenericDAO;
import edu.utn.gestion.exception.DataAccessException;
import edu.utn.gestion.exception.FileGenerationException;
import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.Employee;
import edu.utn.gestion.model.Sale;
import edu.utn.gestion.model.SaleDetail;
import edu.utn.gestion.model.User;
import edu.utn.gestion.service.generic.GenericService;
import edu.utn.gestion.service.util.InvoiceFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by martin on 08/12/15.
 */
public class SaleService extends GenericService<Sale, Long> {
    private static final Logger LOGGER = Logger.getLogger(SaleService.class);
    private static final SaleService INSTANCE = new SaleService();
    private final SaleDAO saleDAO = SaleDAO.getInstance();

    /**
     * Class constructor.
     */
    private SaleService() {}

    /**
     * Returns the unique instance of SaleService.
     *
     * @return
     */
    public static SaleService getInstance() {
        return INSTANCE;
    }

    @Override
    protected GenericDAO<Sale, Long> getDAO() {
        return this.saleDAO;
    }

    @Override
    public Long save(Sale sale) throws GestionAppException {
        Validate.notNull(sale, "Cannot save a null sale.");

        if (CollectionUtils.isEmpty(sale.getSaleDetails())) {
            LOGGER.error("Cannot save an empty sale.");
            throw new GestionAppException("Cannot save an empty sale.");
        }

        this.updateBookStock(sale);

        Long saleId = super.save(sale);

        LOGGER.info("Sale " + saleId + " saved successfully.");

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

    /**
     * Finds the sales for a given user during certain period.
     * If there are not sales, returns an empty list.
     *
     * @param user
     * @param month
     * @param year
     */
    public List<Sale> findByPeriod(final User user, int month, int year) throws GestionAppException {
        Validate.notNull(user, "User cannot be null.");

        Employee employee = user.getEmployee();

        LOGGER.info(new StringBuilder()
                .append("Searching sales for ")
                .append(employee.getName())
                .append(" in month=")
                .append(month)
                .append(" and year=")
                .append(year)
                .toString());

        List<Sale> saleList = null;

        try {
            saleList = this.saleDAO.findByEmployeeAndPeriod(employee, month, year);
        } catch (DataAccessException e) {
            LOGGER.info("There was an issue retrieving the sales.", e);
            throw new GestionAppException("There was an issue retrieving the sales.", e);
        }

        LOGGER.info(new StringBuilder()
                .append(saleList.size())
                .append(" sales were found for ")
                .append(employee.getName())
                .append(" in month=")
                .append(month)
                .append(" and year=")
                .append(year)
                .toString());

        return saleList;
    }
}
