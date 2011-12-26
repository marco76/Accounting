package models.ch.genidea.conta.models;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 18.12.11
 * Time: 19:10
 * To change this template use File | Settings | File Templates.
 */
public class MonthlyData {
    private Integer year;
    private Integer month;
    private BigDecimal amountOfInvoicesToPay;
    private BigDecimal amountOfSales;

    public Integer getPeriod() {
        String period = "" + year + (month<10?"0"+month:month);
        return Integer.valueOf(period);
    }

    
    public BigDecimal getAmountOfInvoicesToPay() {
        return amountOfInvoicesToPay;
    }

    public void setAmountOfInvoicesToPay(BigDecimal amountOfInvoicesToPay) {
        this.amountOfInvoicesToPay = amountOfInvoicesToPay;
    }

    public BigDecimal getAmountOfSales() {
        return amountOfSales;
    }

    public void setAmountOfSales(BigDecimal amountOfSales) {
        this.amountOfSales = amountOfSales;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public BigDecimal getDifferenceToPayAndSales(){
        return amountOfSales.subtract(amountOfInvoicesToPay);
    }
}
