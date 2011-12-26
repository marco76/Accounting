package models.ch.genidea.conta.models.accounting;

import play.db.jpa.Model;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 25.12.11
 * Time: 13:27
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class AccountCategory extends Model{
    public Integer number;
    public String nameIT;
    @ManyToOne
    public AccountingModel model;
    @ManyToOne
    public AccountCategory parentCategory;
    // if is not an account is a category
    public Boolean account = Boolean.FALSE;
}
