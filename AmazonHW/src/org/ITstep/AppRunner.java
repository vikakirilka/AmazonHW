package org.ITstep;

import java.util.ArrayList;


import org.ITstep.dao.AccountDAO;
import org.ITstep.dao.InterimTableDAO;
import org.ITstep.dao.GoodDAO;
import org.ITstep.model.AccRandom;
import org.ITstep.model.Account;
import org.ITstep.model.InterimTable;
import org.ITstep.service.Imitator;
import org.ITstep.service.Timer;
import org.openqa.selenium.WebDriver;

public class AppRunner {

 public static void main(String[] args) {
  for (int i = 0; i < 3; i++) {
   Imitator imService = new Imitator();
   AccountDAO accountDAO = new AccountDAO();
   GoodDAO goodDAO = new GoodDAO();
   AccRandom accRand = new AccRandom();
   InterimTable interim = new InterimTable();
   InterimTableDAO interimDAO = new InterimTableDAO();
   Account acc = new Account(accRand.firstName1(), accRand.getEmail(), accRand.getPassword());
   interim.setName(acc.getName());
   WebDriver driver = imService.registerAmazonAccount(acc);
   accountDAO.save(acc);
   ArrayList<String> asins = goodDAO.getAsin();
   Timer.waitSeconds(10);

   int j = 0;
   for (String string : asins) {
    interim.setAsin(string);
    if (j % 2 == 0) {
     driver = imService.addItemToCart(driver, string);
     interim.setAddToCart("+");
    } else {
     interim.setAddToCart("-");
    }
    

    interimDAO.save(interim);
    j++;
   }

   driver.quit();
  }
 }

}