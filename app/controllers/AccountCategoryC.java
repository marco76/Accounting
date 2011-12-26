package controllers;

import models.ch.genidea.conta.models.accounting.AccountCategory;
import models.ch.genidea.conta.models.accounting.AccountingModel;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marcomolteni
 * Date: 25.12.11
 * Time: 14:20
 * To change this template use File | Settings | File Templates.
 */
public class AccountCategoryC extends Controller {
    
    public static void create(Long accountingModelId, Long accountCategoryId){
        AccountingModel accountingModel = AccountingModel.findById(accountingModelId);
        render(accountingModel, accountCategoryId);
    }

    public static void prepareCreateSubCategory(Long accountingModelId, Long accountCategoryId){
            AccountCategory accountCategory = null;
            AccountingModel accountingModel = null;
            if(accountCategoryId != null){
                accountCategory = AccountCategory.findById(accountCategoryId);
                accountingModel = accountCategory.model;
            }
            accountingModel = AccountingModel.findById(accountingModelId);
            render(accountingModel, accountCategory);
        }
    

    public static void doCreateSubCategory(AccountCategory accountCategory, Long modelId, Long accountCategoryId) {
            AccountingModel accountingModel = AccountingModel.findById(modelId);
            if (accountCategoryId != null){
            AccountCategory accountCategoryParent = AccountCategory.findById(accountCategoryId);
                accountCategory.parentCategory = accountCategoryParent;
            }
            accountCategory.model = accountingModel;

            accountCategory.save();
            list(accountingModel.id, accountCategoryId);
        }
    
    
    public static void list(Long accountingModelId, Long accountCategoryId){
        List<AccountCategory> parents = new ArrayList<AccountCategory>();
        List<AccountCategory> accountCategories = null;
        Long parentCategoryId = accountCategoryId;
        AccountingModel accountingModel = AccountingModel.findById(accountingModelId);

        if (accountCategoryId == null){
              accountCategories = AccountCategory.find("select a from AccountCategory a, AccountingModel m where a.model = m and a.parentCategory is EMPTY and m.id = ? order by a.number", accountingModelId).fetch();
        } else {
            // find parent
            AccountCategory parent = AccountCategory.findById(accountCategoryId);
            while (parent != null){
                parents.add(0,parent);
                parent = AccountCategory.find("select a from AccountCategory a where a = ?", parent.parentCategory).first();
            }
            accountCategories = AccountCategory.find("select a from AccountCategory a where a.parentCategory.id = ? order by a.number", accountCategoryId).fetch();
        }
        
        render(accountCategories, accountingModelId, parents, parentCategoryId, accountingModel);
    }

    public static void delete (Long accountCategoryId){
        AccountCategory accountCategory = AccountCategory.findById(accountCategoryId);
        Long parentId = null;
        if (accountCategory.parentCategory != null){
            parentId = accountCategory.parentCategory.getId();
        }
        Long modelId = accountCategory.model.id;
        accountCategory.delete();
        list(modelId, parentId);
        
    }
    
    public static void prepareUpdate(Long accountTypeId) {
                AccountCategory accountCategory = AccountCategory.findById(accountTypeId);
                render(accountCategory);
    }

            public static void doUpdate(AccountCategory accountCategory) {
                AccountCategory existing = AccountCategory.findById(accountCategory.id);
                accountCategory.model = existing.model;
                accountCategory.parentCategory = existing.parentCategory;

                accountCategory.save();
                list(accountCategory.model.id, accountCategory.parentCategory.id);
                }
    
}
