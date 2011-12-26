package controllers;

import models.ch.genidea.conta.models.Actor;
import play.mvc.Before;
import play.mvc.Controller;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 17.12.11
 * Time: 11:29
 * To change this template use File | Settings | File Templates.
 */
public class ActorC extends Controller {
    @Before
        static void checkAuthentification(){
            if (session.get("userId")== null) Authenticate.login();
        }

    public static void addActor() {
        render();
    }

    public static void saveActor(Actor actor) {

        actor.user = Authenticate.getSessionUser();
        actor.save();
        listActors();
    }

    public static void listActors() {
        List<Actor> actors = Actor.find("from Actor a where a.user = ?",  Authenticate.getSessionUser()).fetch();
        render(actors);
    }

    public static void actionActor(Long id) {
        Actor actor = Actor.findById(id);
        render(actor);
    }

}
