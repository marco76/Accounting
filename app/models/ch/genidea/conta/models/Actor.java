package models.ch.genidea.conta.models;

import models.ch.genidea.conta.models.admin.User;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 15.12.11
 * Time: 19:41
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Actor extends Model{
    public String name;
    public String shortName;
    @ManyToOne
    public User user;
}
