package com.demowebshop.register;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Com.DemoWebShop.GenericUtilities.BaseClass;
import Com.DemoWebShop.GenericUtilities.ExcelUtility;
import Com.DemoWebShop.GenericUtilities.JavaUtility;
import Com.DemoWebShop.GenericUtilities.ListnersImplementation;
import Com.DemoWebShop.GenericUtilities.ScreenShortUtility;

import Com.DemoWebShop.Object_Repository.RegisterPage;
import Com.DemoWebShop.Object_Repository.RegisterResultPage;
import Com.DemoWebShop.Object_Repository.WelComePage;

@Listeners(ListnersImplementation.class)
public class RegisterTest extends BaseClass {

	@Test(groups = {"Regression"}, dataProvider = "RegisterDetails", dataProviderClass = ExcelUtility.class)
	public void registerAsNewUser(String firstName,String lastName, String gender,
			String emailA,String emailB,String password) throws FileNotFoundException, IOException {
		
/*  //Read the data from Excel file//
		ExcelUtility eu=new ExcelUtility();
		String firstName=eu.readDataFromSpecificCell("Sheet1", 1, 1);
		String lastName=eu.readDataFromSpecificCell("Sheet1", 1, 2);
		String gender=eu.readDataFromSpecificCell("Sheet1", 1, 3);
		String emailA=eu.readDataFromSpecificCell("Sheet1", 1, 4);
		String emailB=eu.readDataFromSpecificCell("Sheet1", 1, 5);
		String password=eu.readDataFromSpecificCell("Sheet1", 1, 6);
*/
	    WelComePage wp=new WelComePage(driver);
	    RegisterPage rp=new RegisterPage(driver);
	    RegisterResultPage rrp=new RegisterResultPage(driver);
	    //java utility class
	    JavaUtility ju=new JavaUtility();
	   
		//Click on Register link
		wp.clickOnRegister();
		//Registration
		rp.registerAsNewUser(gender, firstName, lastName, emailA, ju.getRandomNumber(),emailB, password);
		
		//Verify if registration is successful
        Assert.assertTrue(rrp.getSuccessMessage().isDisplayed());
	}
}
