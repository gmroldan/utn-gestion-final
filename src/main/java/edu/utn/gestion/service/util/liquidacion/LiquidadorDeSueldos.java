package edu.utn.gestion.service.util.liquidacion;

import edu.utn.gestion.exception.FileGenerationException;
import edu.utn.gestion.model.Employee;
import edu.utn.gestion.model.Settlement;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Created by ASUS on 27/07/2016.
 */
public class LiquidadorDeSueldos {
    private static final Logger LOGGER = Logger.getLogger(LiquidadorDeSueldos.class);

    /**
     * Executes de Liquidacion process for a certain employee during a given period.
     *
     * @param employee Cannot be null.
     * @param period Cannot be null.
     * @return
     */
    public static Settlement generarLiquidacion(final Employee employee, final String period) {
        Validate.notNull(employee, "Employee cannot be null");
        Validate.notNull(period, "Period cannot be null");

        LOGGER.info(String.format("Starting liquidación for employee with id=%d for the period=%s",
                employee.getId(), period));

        Settlement settlement = new Settlement(employee, period);

        double sueldoBasico = employee.getCategory().getDayPay();
        settlement.setSueldoBasico(sueldoBasico);

        int antiguedad = calcularAntiguedad(employee.getIngress(),period);
        double montoPorAntiguedad = determinarMontoPorAntiguedad(antiguedad,sueldoBasico);
        settlement.setMontoPorAntiguedad(montoPorAntiguedad);
        settlement.setAntiguedad(antiguedad);

        double presentismo = sueldoBasico * 0.083;
        settlement.setPresenteeismAmount(presentismo);

        double asignacionFamiliar = calcularAsignacion(employee, sueldoBasico);
        settlement.setAsignacionFamiliar(asignacionFamiliar);

        double remunerationAmount = sueldoBasico + montoPorAntiguedad + presentismo + asignacionFamiliar;
        settlement.setRemunerationAmount(remunerationAmount);

        double retireAmount = remunerationAmount * 0.11;
        settlement.setRetireAmount(retireAmount);
        double socialCare = remunerationAmount * 0.03;
        settlement.setSocialCare(socialCare);
        double law19032 = remunerationAmount * 0.03;
        settlement.setLaw19032(law19032);

        double discount = retireAmount + socialCare + law19032;
        settlement.setDiscount(discount);

        settlement.setNetPay(remunerationAmount - discount);

        try {
            InvoiceFactoryRecibo.getInstance().generate(settlement);
        } catch (FileGenerationException e) {
            e.printStackTrace();
        }

        return settlement;
    }

    private static double calcularAsignacion(final Employee employee, double sueldoBasico) {
        double asignacion = 0;
        int numberOfFamilyMembers = employee.getFamilies().size();

        for (int i = 0; i < numberOfFamilyMembers; i++) {
            if (sueldoBasico < 15000) {
                asignacion += 966;
            } else if (sueldoBasico < 22000) {
                asignacion += 649;
            } else if (sueldoBasico < 25400) {
                asignacion += 390;
            } else if (asignacion < 60000) {
                asignacion += 200;
            }
        }

        return asignacion;
    }

    protected static double determinarMontoPorAntiguedad(int antiguedad, double grossSalary) {
        int years = antiguedad / 12;
        double porcentaje = years * 0.02;

        return grossSalary * porcentaje;
    }

    protected static int calcularAntiguedad(Date ingress, String period) {

        String[] splitPeriod = period.split("-");
        int actualMonth = Integer.parseInt(splitPeriod[1]);
        int actualYear = Integer.parseInt(splitPeriod[0]);

        int ingressMonth = ingress.getMonth();
        int ingressYear = ingress.getYear() + 1900;

        int yearsInMonths = (actualYear - ingressYear) * 12;

        int totalMonths = actualMonth - ingressMonth + yearsInMonths;

        return totalMonths;
    }

    public static boolean isEmployeeAvailableForPeriod(final Employee employee, final String period) {
        return (calcularAntiguedad(employee.getIngress(),period) >= 0);
    }
}
