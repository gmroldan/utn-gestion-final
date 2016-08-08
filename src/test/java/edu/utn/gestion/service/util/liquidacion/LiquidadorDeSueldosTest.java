package edu.utn.gestion.service.util.liquidacion;

import edu.utn.gestion.model.Employee;
import edu.utn.gestion.model.Family;
import edu.utn.gestion.model.SalaryCategory;
import edu.utn.gestion.model.Settlement;
import org.testng.annotations.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by ASUS on 27/07/2016.
 */
public class LiquidadorDeSueldosTest {

    @Test
    public void generarLiquidacionTest() {

        SalaryCategory category = new SalaryCategory("Vendedor", 10000);
        Employee employee = new Employee();
        employee.setCategory(category);
        employee.setIngress(new Date(115,3,9));
        employee.setName("Benjamin Salas");
        employee.setCuit("20-34953806-8");
//        Family hijo = new Family();
//        hijo.setName("Benja jr");
//        hijo.setDni("50123456");
//        hijo.setBound("Hijo");
//        hijo.setBirthDate(new Date());
//        employee.getFamilies().add(hijo);

        Settlement liquidacion = LiquidadorDeSueldos.generarLiquidacion(employee, "2016-8");

//        assertEquals((int)liquidacion.getSueldoBasico(),10000);
//        assertEquals((int)liquidacion.getPresenteeismAmount(),830);
//        assertEquals((int)liquidacion.getMontoPorAntiguedad(),200);
//        assertEquals((int)liquidacion.getRemunerationAmount(),10000+830+200);
//        assertEquals((int)liquidacion.getRetireAmount(),1213);
//        assertEquals((int)liquidacion.getSocialCare(),330);
//        assertEquals((int)liquidacion.getLaw19032(),330);
    }

    @Test
    public void calcularAntiguedadSoloMesesTest() {

        int meses = LiquidadorDeSueldos.calcularAntiguedad(new Date(116,3,9),"2016-6");

        assertEquals(3, meses);

    }

    @Test
    public void calcularAntiguedadMesesYAniosTest() {

        int meses = LiquidadorDeSueldos.calcularAntiguedad(new Date(115,3,9),"2016-4");

        assertEquals(13, meses);
    }

    /**
     * Solo para estar seguro
     *
     */
    @Test
    public void aLotOfPosibilitiesTest() {

        int result = LiquidadorDeSueldos.calcularAntiguedad(new Date(115,3,9),"2016-4");
        assertEquals(13, result);

        result = LiquidadorDeSueldos.calcularAntiguedad(new Date(115,5,9),"2016-4");
        assertEquals(11, result);

        result = LiquidadorDeSueldos.calcularAntiguedad(new Date(115,3,9),"2016-3");
        assertEquals(12, result);

        result = LiquidadorDeSueldos.calcularAntiguedad(new Date(115,3,9),"2016-10");
        assertEquals(12+7, result);

    }

    @Test
    public void calcularMontoPorAntiguedadTest() {
        int result = (int) LiquidadorDeSueldos.determinarMontoPorAntiguedad(12, 10000);
        assertEquals(result,200);

        result = (int) LiquidadorDeSueldos.determinarMontoPorAntiguedad(25, 10000);
        assertEquals(result,400);

        result = (int) LiquidadorDeSueldos.determinarMontoPorAntiguedad(35, 10000);
        assertEquals(result,400);

    }

    @Test
    public void isAvailableTest() {

        Employee e = new Employee();
        e.setIngress(new Date(116,5,3));
        String period = "2016-5";

        boolean result = LiquidadorDeSueldos.isEmployeeAvailableForPeriod(e,period);

        assertEquals(true,result);
    }

    @Test
    public void isUnAvailableTest() {

        Employee e = new Employee();
        e.setIngress(new Date(116,5,3));
        String period = "2016-3";

        boolean result = LiquidadorDeSueldos.isEmployeeAvailableForPeriod(e,period);

        assertEquals(false,result);
    }

}