package models.services;

import models.ch.genidea.conta.models.accounting.AccountCategory;
import models.ch.genidea.conta.models.accounting.AccountUser;
import models.ch.genidea.conta.models.accounting.AccountingModel;
import models.ch.genidea.conta.models.accounting.AccountingUserModel;
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
public class AccountingService {
    
    public static void copyModelToUserModel(Long modelId, Long userId){
        User user = User.findById(userId);
        AccountingModel model = AccountingModel.findById(modelId);
        AccountingUserModel userModel = new AccountingUserModel();
        userModel.dateCreated = GregorianCalendar.getInstance().getTime();
        userModel.name = model.nameIT;
        userModel.originalModel = model;
        userModel.user = user;
        userModel.save();
        copyAllAccountsFromModel(model, userModel);


    }

    private static void copyAllAccountsFromModel(AccountingModel model, AccountingUserModel userModel){
        // find all accounts to be copied
        List<AccountCategory> accountsFirstLevel = AccountCategory.find("model = ? and parentCategory = null", model).fetch();
        for (AccountCategory account : accountsFirstLevel){
            copyLeafAccount(account, userModel, null);
        }

    }

    private static void copyLeafAccount(AccountCategory parent, AccountingUserModel model, AccountUser newParent){
        AccountUser newAccount = new AccountUser();
        newAccount.account = parent.account;
                    newAccount.model = model;
                    newAccount.nameIT = parent.nameIT;
                    newAccount.number = parent.number;
                    newAccount.parentCategory =  newParent;
        newAccount.save();
      // if account has children copy the children
      // this is necessary to maintain the correct reference
      List<AccountCategory> accountLeafs =  AccountCategory.find("parentCategory = ?", parent).fetch();
      for (AccountCategory accountLeaf :accountLeafs){
          copyLeafAccount(accountLeaf, model, newAccount);
       }

    }
}
