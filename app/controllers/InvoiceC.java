package controllers;

import models.beans.UserCacheBean;
import models.ch.genidea.conta.models.Actor;
import models.ch.genidea.conta.models.Invoice;
import models.ch.genidea.conta.models.admin.User;
import models.services.InvoiceService;
import play.cache.Cache;
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
 * Time: 11:36
 * To change this template use File | Settings | File Templates.
 */
public class InvoiceC extends Controller {

    @Before
    static void checkAuthentification(){
        if (session.get("userId")== null) Authenticate.login();
    }

    public static void prepareCreateInvoice(Long actorId) {
            Actor actor = Actor.findById(actorId);
            render(actor);
        }

        public static void doCreateInvoice(Long id, Invoice invoice, @As("dd.MM.yyyy") Date dateToPay) {
             Actor actor1 = Actor.findById(id);
            invoice.actor = actor1;
            invoice.dateToPay = dateToPay;
            invoice.user = Authenticate.getSessionUser();
            invoice.save();
            invoicesList();
        }

        public static void prepareUpdateInvoice(Long invoiceId) {
            Invoice invoice = Invoice.findById(invoiceId);
            render(invoice);
        }

        public static void doUpdateInvoice(Invoice invoice, @As("dd.MM.yyyy") Date dateToPay) {
            if (invoice.actor == null) {
                Invoice originalInvoice = Invoice.findById(invoice.id);
                invoice.actor = originalInvoice.actor;
                invoice.user = originalInvoice.user;
            }
            invoice.dateToPay = dateToPay;
            invoice.save();
            invoicesList();
        }

        public static void invoicesList() {
            //Query query = JPA.em().createQuery("select * from Invoice");
            List<Invoice> invoices = Invoice.find("from Invoice i where i.user = ? order by dateToPay", Authenticate.getSessionUser()).fetch();
            BigDecimal totalAmount = InvoiceService.totalAmountOfInvoices(invoices);
            render(invoices, totalAmount);
        }

}
