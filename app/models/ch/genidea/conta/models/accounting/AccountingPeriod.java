package models.ch.genidea.conta.models.accounting;

import models.ch.genidea.conta.models.admin.User;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 26.12.11
 * Time: 17:06
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class AccountingPeriod extends Model{
    public Integer year;
    @ManyToOne
    public User user;
    public String name;
    public String description;
    public Boolean active = Boolean.TRUE;
}
