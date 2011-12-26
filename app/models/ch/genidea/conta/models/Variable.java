package models.ch.genidea.conta.models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 17.12.11
 * Time: 16:19
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Variable extends Model
{
    public String name;
    public String value;
    public Integer year;
}
