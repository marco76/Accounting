package models.ch.genidea.conta.models;

import models.ch.genidea.conta.models.admin.User;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 17.12.11
 * Time: 15:55
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Todo extends Model {
  public String description;
  public Date dueDate;
  public Integer priority;
  public Boolean finished;

    @ManyToOne
        public User user;

}
