package controllers;

import models.ch.genidea.conta.models.Invoice;
import models.ch.genidea.conta.models.Todo;
import play.data.binding.As;
import play.mvc.Before;
import play.mvc.Controller;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 17.12.11
 * Time: 16:01
 * To change this template use File | Settings | File Templates.
 */
public class TodoC extends Controller {

    @Before
        static void checkAuthentification(){
            if (session.get("userId")== null) Authenticate.login();
        }

    public static void list() {
            List<Todo> todos = Todo.find("from Todo t where t.user = ?", Authenticate.getSessionUser()).fetch();
            render(todos);
        }

        public static void action(Long id) {
            Todo todo = Todo.findById(id);
            render(todo);
        }

        public static void doCreate(Todo todo, @As("dd.MM.yyyy") Date dueDate) {
            todo.dueDate = dueDate;
            todo.user = Authenticate.getSessionUser();
            todo.save();
            list();
        }

        public static void create() {
            render();
        }

        public static void prepareUpdate(Long todoId) {
            Todo todo = Todo.findById(todoId);
            render(todo);
        }
    
        public static void doUpdate(Todo todo, @As("dd.MM.yyyy") Date dueDate) {
                todo.dueDate = dueDate;
            todo.user = Authenticate.getSessionUser();
                todo.save();
                list();
            }
        public static void doDelete(Long todoId){
            Todo.findById(todoId)._delete();
            list();
        }
}
