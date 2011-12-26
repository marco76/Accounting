package models.services;

import models.ch.genidea.conta.models.Invoice;
import models.ch.genidea.conta.models.Sale;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 17.12.11
 * Time: 01:13
 * To change this template use File | Settings | File Templates.
 */
public class SaleService {
    
    public static BigDecimal totalAmountOfSales(List<Sale> sales){
        BigDecimal amount = new BigDecimal("0");
        for (Sale sale : sales){
            amount = amount.add(sale.amountTTC);
        }
        return amount;
    }
    
    public static BigDecimal totalAmountOfSalesToBePaid(List<Sale> sales){
        BigDecimal amount = new BigDecimal("0");
                for (Sale sale : sales){
                    if(!sale.paid)
                        amount = amount.add(sale.amountTTC);
                }
                return amount;
        
    }


}
