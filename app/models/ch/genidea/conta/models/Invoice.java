package models.ch.genidea.conta.models;

import com.sun.xml.internal.ws.client.SenderException;
import models.ch.genidea.conta.models.admin.User;
import org.joda.time.DateTime;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 15.12.11
 * Time: 19:36
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Invoice extends Model {
    public Date date;
    public Boolean closed = Boolean.FALSE;
    public BigDecimal amount;
    @ManyToOne
    public Actor actor;
    public BigDecimal vatAmount;
    public BigDecimal vatPercent;
    public Boolean vatDeductible;
    public Date dateReceived;
    public Date dateToPay;
    public Date dateClosed;
    public String referenceExt;

    @ManyToOne
        public User user;


    public static List<Invoice> getInvoicesToPayForTheMonth(int month, int year, Long userId){
        DateTime date = new DateTime(year, month, 1, 0,0);
        Date start = date.toDate();
        Date stop = date.plusMonths(1).toDate();
        List<Invoice> result = Invoice.em().createQuery("Select e from Invoice e where e.user.id = :userId and dateToPay >= :start and dateToPay < :stop").
        setParameter("userId", userId).
                setParameter("start", start, TemporalType.DATE).
                setParameter("stop", stop, TemporalType.DATE).getResultList();
        return result;
    }

}
