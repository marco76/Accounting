package controllers;

import models.ch.genidea.conta.models.Actor;
import models.ch.genidea.conta.models.Client;
import models.ch.genidea.conta.models.Invoice;
import models.ch.genidea.conta.models.MonthlyData;
import models.services.InvoiceService;
import models.services.StatsService;
import org.joda.time.DateTime;
import play.*;
import play.cache.Cache;
import play.data.binding.As;
import play.db.jpa.JPA;
import play.mvc.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import models.*;

import javax.persistence.Query;

public class Application extends Controller {

    public static void indexUser() {
        //List<MonthlyData> monthlyData = (List<MonthlyData>)Cache.get("monthlyData");
        //if (monthlyData == null) {
         List<MonthlyData>
                 monthlyData = StatsService.getMonthlyStats(DateTime.now().toDate(), 6, Authenticate.getSessionUser().id);
         //  Cache.set("monthlyData", monthlyData);
        //}

        render(monthlyData);
    }

    public static void index(){
        if (session.get("userId") != null){
          indexUser();
        }
        render();
    }







}