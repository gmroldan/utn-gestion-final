package edu.utn.gestion.service.util.liquidacion;

import edu.utn.gestion.exception.FileGenerationException;
import edu.utn.gestion.model.Employee;
import edu.utn.gestion.model.Settlement;

import java.util.Date;

/**
 * Created by ASUS on 27/07/2016.
 */
public class LiquidadorDeSueldos {

    public static Settlement generarLiquidacion(Employee employee, String period) {

        Settlement settlement = new Settlement(employee, period);

        double sueldoBasico = employee.getCategory().getDayPay();
        settlement.setSueldoBasico(sueldoBasico);

        int antiguedad = calcularAntiguedad(employee.getIngress(),period);
        double montoPorAntiguedad = determinarMontoPorAntiguedad(antiguedad,sueldoBasico);
        settlement.setMontoPorAntiguedad(montoPorAntiguedad);
        settlement.setAntiguedad(antiguedad);

        double presentismo = sueldoBasico * 0.083;
        settlement.setPresenteeismAmount(presentismo);

        double remunerationAmount = sueldoBasico + montoPorAntiguedad + presentismo;
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
            InvoiceFactoryRecibo.generarRecibo(settlement);
        } catch (FileGenerationException e) {
            e.printStackTrace();
        }

        return settlement;
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

    public static boolean isEmployeeAvailableForPeriod(Employee e, String period) {

        //TODO filtrar solo los que entraron despues del periodo seleccionado

        return true;
    }
}
