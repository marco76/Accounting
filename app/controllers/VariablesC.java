package controllers;

import models.ch.genidea.conta.models.Todo;
import models.ch.genidea.conta.models.Variable;
import play.mvc.Controller;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 17.12.11
 * Time: 16:01
 * To change this template use File | Settings | File Templates.
 */
public class VariablesC extends Controller {
    public static void list() {
      List<Variable> variables = Variable.all().fetch();
            render(variables);
        }

        public static void action(Long id) {
            Variable variable = Variable.findById(id);
            render(variable);
        }

        public static void doCreate(Variable variable) {
            variable.save();
        }

        public static void create() {
            render();
        }
}
