package controllers;

import models.ch.genidea.conta.models.accounting.AccountCategory;
import models.ch.genidea.conta.models.accounting.AccountingModel;
import play.data.binding.As;
import play.mvc.Controller;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 25.12.11
 * Time: 14:20
 * To change this template use File | Settings | File Templates.
 */
public class AccountingModelC extends Controller {
    public static void create(Long modelId){

        render();
    }

    public static void doCreate(AccountingModel accountingModel) {
        accountingModel.save();
        list();
          
    }
    public static void list(){
        List<AccountingModel> accountingModels = AccountingModel.all().fetch();
        render(accountingModels);
    }

    public static void prepareUpdate(Long accountingModelId) {
                AccountingModel accountingModel = AccountingModel.findById(accountingModelId);
                render(accountingModel);
            }

            public static void doUpdate(AccountingModel accountingModel) {

                accountingModel.save();
                    list();
                }
    public static void doDelete(Long id){
       AccountingModel am = AccountingModel.findById(id);
        Long accountsLinked = AccountCategory.count("model = ?", am);
        if (accountsLinked.compareTo(0l) == 0){
            am.delete();
        }
        list();
    }
}
