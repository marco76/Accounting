package models.ch.genidea.conta.models;

import models.ch.genidea.conta.models.admin.User;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 16.12.11
 * Time: 21:47
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Bill extends Model
{
    public Actor actor;
    public Float amount;
    public Date dueDate;
    
    public void Bill(){}
    
    public void Bill(Actor actor, Float amount, Date dueDate){
        this.actor = actor;
        this.amount = amount;
        this.dueDate = dueDate;
    }
    @ManyToOne
    public User user;


}
