package models.ch.genidea.conta.models;

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
 * Date: 17.12.11
 * Time: 10:29
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Sale extends Model {
    @ManyToOne
    public Client client;
    public Date dateValue;
    public Date dueDate;
    public Boolean paid = Boolean.FALSE;
    public BigDecimal amount;
    public String reference;
    public BigDecimal vatAmount;
    public BigDecimal vatPercent;
    public BigDecimal amountTTC;

    @ManyToOne
        public User user;


    public static List<Sale> getSalesForTheMonth(int month, int year, Long userId){
            DateTime date = new DateTime(year, month, 1, 0,0);
            Date start = date.toDate();
            Date stop = date.plusMonths(1).toDate();
            List<Sale> result =Invoice.em().createQuery("Select e from Sale e where e.user.id = :userId and dueDate >= :start and dueDate < :stop").
                    setParameter("userId", userId).
                    setParameter("start", start, TemporalType.DATE).
                    setParameter("stop", stop, TemporalType.DATE).getResultList();
            return result;
        }
}
