package models.services;

import models.ch.genidea.conta.models.accounting.*;
import models.ch.genidea.conta.models.admin.User;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 01.01.12
 * Time: 11:25
 * To change this template use File | Settings | File Templates.
 */

// @TODO: this class is the facto very similar to Accounting Service, a refactoring using interfaces should be good

public class AccountingUserService {
    
    public static void copyUserModelToPeriod(AccountingUserModel model, AccountingPeriod period){
        copyAllAccountsFromModel(model, period);
    }

    private static void copyAllAccountsFromModel(AccountingUserModel userModel, AccountingPeriod period){
        // find all accounts to be copied
        List<AccountUser> accountsFirstLevel = AccountUser.find("model = ? and parentCategory = null", userModel).fetch();
        for (AccountUser account : accountsFirstLevel){
            copyLeafAccount(account, null, period);
        }

    }

    private static void copyLeafAccount(AccountUser parent, AccountUserPeriod newParent, AccountingPeriod period){
        AccountUserPeriod newAccount = new AccountUserPeriod();
        newAccount.account = parent.account;
        newAccount.model = parent.model;
        newAccount.nameIT = parent.nameIT;
        newAccount.number = parent.number;
        newAccount.parentCategory =  newParent;
        newAccount.period = period;
        newAccount.user = period.user;

        newAccount.save();
      // if account has children copy the children
      // this is necessary to maintain the correct reference
      List<AccountUser> accountLeafs =  AccountUser.find("parentCategory = ?", parent).fetch();
      for (AccountUser accountLeaf :accountLeafs){
          copyLeafAccount(accountLeaf, newAccount, period);
       }

    }
}
