package models.ch.genidea.conta.models.accounting;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 25.12.11
 * Time: 13:32
 * To change this template use Fil
 * e | Settings | File Templates.
 */
@Entity
public class Account extends Model {
    AccountingModel model;
    Integer code;
    String definitionIT;
}
