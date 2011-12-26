package models.ch.genidea.conta.models;

import play.db.jpa.Model;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 15.12.11
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
public class Parameters extends Model {
    public String category; //salary
    public String name; // constant name
    public BigDecimal amount; //
    public String classz; //complex elements
}
