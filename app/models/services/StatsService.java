package models.services;

import models.ch.genidea.conta.models.Invoice;
import models.ch.genidea.conta.models.MonthlyData;
import models.ch.genidea.conta.models.Sale;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 18.12.11
 * Time: 20:15
 * To change this template use File | Settings | File Templates.
 */
public class StatsService {
    
    public static List<MonthlyData> getMonthlyStats(Date startingDate, int months, Long userId){
        List<MonthlyData> result = new ArrayList<MonthlyData>();
        DateTime date = new DateTime(startingDate);
        
        for (int i = 0; i < months; i++){
            List<Invoice> invoices = Invoice.getInvoicesToPayForTheMonth(date.getMonthOfYear(), date.getYear(), userId);
            List<Sale> sales = Sale.getSalesForTheMonth(date.getMonthOfYear(), date.getYear(), userId);
            BigDecimal totalForTheMonth = InvoiceService.totalAmountOfInvoices(invoices);
            BigDecimal totalAmountSalesForTheMonth = SaleService.totalAmountOfSales(sales);
            MonthlyData md = new MonthlyData();
                md.setMonth(date.getMonthOfYear());
                md.setYear(date.getYear());
                md.setAmountOfInvoicesToPay(totalForTheMonth);
                md.setAmountOfSales(totalAmountSalesForTheMonth);

                date = date.plusMonths(1);
                result.add(md);
            }
            return result;
            }
    }
