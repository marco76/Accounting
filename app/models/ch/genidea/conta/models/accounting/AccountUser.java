package models.ch.genidea.conta.models.accounting;

import models.ch.genidea.conta.models.admin.User;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 25.12.11
 * Time: 13:27
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class AccountUser extends Model{
    /*
       Inherited from AccountCategory, it's not possible extend the class because it already extends Model
     */
    public Integer number;
    public String nameIT;
    @ManyToOne
    public AccountingModel model;
    @ManyToOne
    public AccountUser parentCategory;
    // if is not an account is a category
    public Boolean account = Boolean.FALSE;
    /*
      end of inherited
     */

    @ManyToOne
    AccountingPeriod period;
}
