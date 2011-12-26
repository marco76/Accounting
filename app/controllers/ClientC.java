package controllers;

import models.ch.genidea.conta.models.Client;
import play.mvc.Before;
import play.mvc.Controller;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 17.12.11
 * Time: 10:43
 * To change this template use File | Settings | File Templates.
 */

public class ClientC extends Controller {
    @Before
        static void checkAuthentification(){
            if (session.get("userId")== null) Authenticate.login();
        }

    public static void listClient() {
        List<Client> clients = Client.find("from Client c where c.user = ?", Authenticate.getSessionUser()).fetch();
        render(clients);
    }

    public static void actionClient(Long id) {
        Client client = Client.findById(id);
        render(client);
    }

    public static void doCreateClient(Client client) {
        client.user = Authenticate.getSessionUser();
        client.save();
    }

    public static void newClient() {
        render();
    }
}
