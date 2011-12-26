package controllers;

import models.ch.genidea.conta.models.Actor;
import models.ch.genidea.conta.models.Client;
import models.ch.genidea.conta.models.Invoice;
import models.ch.genidea.conta.models.Sale;
import models.services.InvoiceService;
import models.services.SaleService;
import play.data.binding.As;
import play.db.jpa.JPA;
import play.mvc.Before;
import play.mvc.Controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 17.12.11
 * Time: 10:52
 * To change this template use File | Settings | File Templates.
 */
public class SaleC extends Controller {

    @Before
        static void checkAuthentification(){
            if (session.get("userId")== null) Authenticate.login();
        }

    public static void prepareCreateSale(Long clientId) {
        Client client = Client.findById(clientId);
        render(client);
    }

    public static void doCreateSale(Long id, Sale sale, @As("dd.MM.yyyy") Date dueDate) {

        Client client = Client.findById(id);
        sale.client = client;
        sale.dueDate = dueDate;
        sale.user = Authenticate.getSessionUser();
        sale.save();
        listSales();
    }

    public static void listSales() {
        //Query query = JPA.em().createQuery("select * from Invoice");

        List<Sale> sales = Sale.find("from Sale s where s.user = ?  order by dueDate", Authenticate.getSessionUser()).fetch();
        BigDecimal totalAmount = SaleService.totalAmountOfSales(sales);
        BigDecimal totalAmountToBePaid = SaleService.totalAmountOfSalesToBePaid(sales);
        render(sales, totalAmount, totalAmountToBePaid);
    }

    public static void prepareUpdate(Long id) {
        Sale sale = Sale.findById(id);
        render(sale);
    }

    public static void maintenance() {
        List<Sale> sales = Sale.all().fetch();
        for (Sale sale : sales){
            sale.paid = Boolean.FALSE;
            sale.save();
        }
    }



            public static void doUpdate(Sale sale, @As("dd.MM.yyyy") Date dueDate) {
                if (sale.client == null) {
                    Sale originalSale = Sale.findById(sale.id);
                    sale.client = originalSale.client;
                    sale.user = originalSale.user;
                }
                sale.dueDate = dueDate;
                sale.save();
                listSales();
            }
}
