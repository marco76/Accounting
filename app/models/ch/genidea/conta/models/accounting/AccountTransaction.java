package models.ch.genidea.conta.models.accounting;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 01.01.12
 * Time: 17:03
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class AccountTransaction extends Model {
    public Date date;
    public Date dateValue;
    public BigDecimal amount;
    @ManyToOne
    public AccountingPeriod accountingPeriod;
    
}
