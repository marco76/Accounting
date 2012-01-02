package controllers;

import models.ch.genidea.conta.models.accounting.AccountUserPeriod;
import models.ch.genidea.conta.models.accounting.AccountingPeriod;
import models.ch.genidea.conta.models.accounting.AccountingUserModel;
import models.services.AccountingUserService;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 01.01.12
 * Time: 13:04
 * To change this template use File | Settings | File Templates.
 */
public class AccountingPeriodC extends Controller{
    public static void index(){
      List<AccountingPeriod> accountingPeriods = AccountingPeriod.find("user = ? and active = ?", Authenticate.getSessionUser(), Boolean.TRUE).fetch();
      render(accountingPeriods);
    }

    
    public static void prepareNewPeriod(){
        List<AccountingUserModel>
                userModels = AccountingUserModel.find("user", Authenticate.getSessionUser()).fetch();
        render(userModels);
    };

    public static void doNewPeriod(String year, String plan){
        AccountingUserModel model = AccountingUserModel.findById(Long.valueOf(plan));
        AccountingPeriod period = new AccountingPeriod();
        period.active = Boolean.TRUE;
        period.user = Authenticate.getSessionUser();
        period.year = Integer.valueOf(year);
        period.name = model.name;
        period.save();
        AccountingUserService.copyUserModelToPeriod(model, period);
        index();
    }
    
    public static void listAccounts(Long period){
        List<AccountUserPeriod> accounts = AccountUserPeriod.find("period = ? order by number", AccountingPeriod.findById(period)).fetch();
        render(accounts);
        
    }
    
    public static void prepareNewOperation(Long accountId){

        
    }

    public static void categoryDetail(Long periodId, Long accountId){
            List<AccountUserPeriod> parents = new ArrayList<AccountUserPeriod>();
            List<AccountUserPeriod> accountCategories = null;

            AccountingPeriod accountingPeriod = AccountingPeriod.findById(periodId);

            if (accountId == null){
                  accountCategories = AccountingPeriod.find("select a from AccountingPeriod a, AccountingPeriod m where a.period = m and a.parentCategory is EMPTY and m.id = ? order by a.number", periodId).fetch();
            } else {
                // find parent
                AccountUserPeriod parent = AccountUserPeriod.findById(accountId);
                while (parent != null){
                    parents.add(0,parent);
                    parent = AccountUserPeriod.find("select a from AccountUserPeriod a where a = ?", parent.parentCategory).first();
                }
                accountCategories = AccountUserPeriod.find("select a from AccountUserPeriod a where a.parentCategory.id = ? order by a.number", accountId).fetch();
            }

            render(accountCategories, periodId, parents, accountId, accountingPeriod);
        }
    
    
    public static void prepareNewTransaction(){
        
        //AccountUserPeriod account = AccountUserPeriod.findById(id);
        List<AccountUserPeriod> listAccounts = AccountUserPeriod.find("period.active = true and user=?", Authenticate.getSessionUser()).fetch();

        render(listAccounts);
    }
    
    public static void doTransaction(List<AccountUserPeriod> account){
        index();
    }
    
    public static void getObjects(final String term, Long periodId){
        //List<AccountUserPeriod> accounts = AccountUserPeriod.find("name like '%?%' and user period.id = ?", term, periodId).fetch(10);
        List<AccountUserPeriod> accounts = AccountUserPeriod.all().fetch();
        System.out.println(accounts.size());
        renderJSON(accounts);
    }
}
