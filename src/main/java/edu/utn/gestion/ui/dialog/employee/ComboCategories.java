package edu.utn.gestion.ui.dialog.employee;

import edu.utn.gestion.exception.GestionAppException;
import edu.utn.gestion.model.SalaryCategory;
import edu.utn.gestion.service.SalaryCategoryService;
import edu.utn.gestion.ui.util.PopUpFactory;
import org.apache.commons.collections4.CollectionUtils;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.util.List;

/**
 * Created by ASUS on 28/02/2016.
 */
public class ComboCategories extends JComboBox {

    public ComboCategories() {
        initModel();
    }

    private void initModel() {
        List<SalaryCategory> salaryCategories = null;

        try {
            SalaryCategoryService service = SalaryCategoryService.getInstance();
            salaryCategories = service.findAll();

        } catch (GestionAppException ex) {
            PopUpFactory.showErrorMessage(this, ex.getMessage());
        } catch (ClassCastException ex) {

        }

        if (CollectionUtils.isNotEmpty(salaryCategories)) {
            this.setModel(new DefaultComboBoxModel(salaryCategories.toArray()));
        }
    }

    public SalaryCategory getEmployee(){

        return (SalaryCategory) this.getSelectedItem();
    }
}
