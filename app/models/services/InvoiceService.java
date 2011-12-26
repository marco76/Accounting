package models.services;

import models.ch.genidea.conta.models.Invoice;
import models.ch.genidea.conta.models.MonthlyData;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 17.12.11
 * Time: 01:13
 * To change this template use File | Settings | File Templates.
 */
public class InvoiceService {
    
    public static BigDecimal totalAmountOfInvoices(List<Invoice> invoices){
        BigDecimal amount = new BigDecimal("0");
        for (Invoice invoice : invoices){
            amount = amount.add(invoice.amount);
        }
        return amount;
    }


}
