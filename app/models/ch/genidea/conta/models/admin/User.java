package models.ch.genidea.conta.models.admin;

import play.data.validation.Email;
import play.data.validation.MinSize;
import play.data.validation.Password;
import play.data.validation.Required;
import play.db.jpa.Model;
import play.libs.Codec;

import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 26.12.11
 * Time: 10:59
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class User extends Model {
    
    @Required
    @MinSize(6)
    public String username;
    @Required
    @Email
    public String email;
    @Required
    @Password
    @Transient
    public String password;
    public String passwordHash;
    @Required
    public String firstName;
    @Required
    public String lastName;
    
    public void setPassword(String password){
        this.password = password;
        this.passwordHash = Codec.hexMD5(password);
    }
    
    public static boolean isValidLogin(String username, String password){
        return (count("username=? AND PasswordHash=?", username, Codec.hexMD5(password)) == 1);
    }
}
