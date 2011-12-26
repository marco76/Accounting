import models.ch.genidea.conta.models.Actor;
import models.ch.genidea.conta.models.Invoice;
import models.ch.genidea.conta.models.MonthlyData;
import models.services.InvoiceService;
import models.services.StatsService;
import org.joda.time.DateTime;
import org.junit.*;

import java.math.BigDecimal;
import java.util.*;
import play.test.*;

public class BasicTest extends UnitTest {

    @Before
    public void setup(){
        Fixtures.deleteDatabase();
    }

    @Test
    public void createAndRetrieveActor(){
        Actor avs = new Actor();
        avs.name = "AVS";
        avs.save();
        
        long count = Actor.count();

        assertEquals(count, 1);
    }
    
    @Test
    public void createInvoice(){
        Actor avs = new Actor();
        Invoice invoice = new Invoice();
        invoice.actor = avs;
        invoice.amount = new BigDecimal(15.0);
        avs.save();
        assertEquals(1, Actor.count());
        invoice.save();
        List<Invoice> avsInvoices = Invoice.find("byActor", avs).fetch();
        
        assertEquals(1, avsInvoices.size());
        assertEquals(15, avsInvoices.get(0).amount.intValue());
    }

    @Test
    public void totalMonthlyInvoicesTest(){
        assertTrue(InvoiceService.totalAmountOfInvoices(Invoice.getInvoicesToPayForTheMonth(1, 2012)).doubleValue() >= 0f);

        Actor test = new Actor();
        Invoice invoice = new Invoice();
        invoice.actor = test;
        invoice.dateToPay = new DateTime(2012,1,12,0,0).toDate();
        invoice.amount = new BigDecimal("1230");
        test.save();
        invoice.save();
        assertTrue(InvoiceService.totalAmountOfInvoices(Invoice.getInvoicesToPayForTheMonth(1, 2012)).doubleValue() > 1000f);
        List<MonthlyData> monthlyData = StatsService.getMonthlyStats(new DateTime(2012, 1, 12, 0, 0).toDate(), 2);
        assertTrue(monthlyData.get(0).getPeriod().equals(201201));
        assertTrue(monthlyData.get(1).getPeriod().equals(201202));
        assertTrue(monthlyData.get(0).getAmountOfInvoicesToPay().compareTo(new BigDecimal("1230")) == 0);
        assertTrue(monthlyData.get(1).getAmountOfInvoicesToPay().doubleValue() < 1);

    } 
}
