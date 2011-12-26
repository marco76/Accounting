package models.ch.genidea.conta.models;

import models.ch.genidea.conta.models.admin.User;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 17.12.11
 * Time: 10:25
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Client extends Model {
    public String name;

    @ManyToOne
    public User user;

}
