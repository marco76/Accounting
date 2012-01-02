package controllers;

import models.ch.genidea.conta.models.accounting.AccountingModel;
import models.ch.genidea.conta.models.accounting.AccountingUserModel;
import models.services.AccountingService;
import play.mvc.Controller;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 01.01.12
 * Time: 11:09
 * To change this template use File | Settings | File Templates.
 */
public class OptionsC extends Controller {

    public static void addAccountingPlan(){

    }

    public static void choicheAccountingPlan(){

    }

    public static void index (){
       List<AccountingModel> accountingModels =  AccountingModel.all().fetch();
        List<AccountingUserModel> userPlans = AccountingUserModel.find("user", Authenticate.getSessionUser()).fetch();
       render(accountingModels, userPlans);
    }
    
    public static void doApplyAccountingModel(Long modelId){
        AccountingService.copyModelToUserModel(modelId, Authenticate.getSessionUser().id);
        index();
    }
}
