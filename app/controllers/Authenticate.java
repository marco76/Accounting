package controllers;

import models.beans.UserCacheBean;
import models.ch.genidea.conta.models.admin.User;
import play.cache.Cache;
import play.data.validation.Valid;
import play.libs.Codec;
import play.mvc.Controller;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 26.12.11
 * Time: 11:08
 * To change this template use File | Settings | File Templates.
 */
public class Authenticate extends Controller {
    
    public static void doLoginLogic(String username){
        String uuid = Codec.UUID();
        session.put("userId", uuid);
        User logged =  User.find("byUsername", username).first();
        UserCacheBean user = new UserCacheBean();
        user.setUsername(logged.username);
        user.setUserId(logged.id);
        Cache.add(uuid, user);
        Application.index();
    }
    
    public static void register(){
        render();
    }
    
    public static void doRegister(@Valid User user){
        // if there are errors, redisplay the registration form
        if (!user.validateAndSave()){
            params.flash();
            validation.keep();
            register();
        }
        // if no errors, log the user in and redirect to the index page
        doLoginLogic(user.username);
        Application.index();

    }

    public static void login() {
       render();
    }
    public static void doLogin(String username, String password) {
       if (User.isValidLogin(username, password)) {
          doLoginLogic(username);
          // it never pass here!!!
          Application.index();
       } else {
            validation.addError("username",
                 "Username/Password combination was incorrect");
             validation.keep();
             login();
       }

    }
       public static void logout() {
          session.remove("userId");
          Application.index();
       }
    
    public static User getSessionUser(){
        UserCacheBean userCacheBean = null;

        userCacheBean = (UserCacheBean) Cache.get(session.get("userId"));
        if (userCacheBean == null){
            logout();
        }
       User user = User.findById(userCacheBean.getUserId());
        return user;
    }
}
