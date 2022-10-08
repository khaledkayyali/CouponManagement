package com;

import com.beans.Category;
import com.beans.Company;
import com.beans.Coupon;
import com.beans.Customer;
import com.dailyJob.CouponExpirationDailyJob;
import com.facade.AdminFacade;
import com.facade.CompanyFacade;
import com.facade.CustomerFacade;
import com.login.ClientType;
import com.login.LoginManager;

import java.sql.SQLException;

public class Test {
    public static void testAll() throws Exception {

        LoginManager loginManager = LoginManager.getInstance();
        AdminFacade adminUser = (AdminFacade) loginManager.login("admin@admin.com","admin", ClientType.ADMINISTRATOR);

        adminUser.addCompany(new Company(1,"Intel","intel@hotmail.com","intel"));
        adminUser.addCompany(new Company(2,"Microsoft","microsoft@hotmail.com","microsoft"));
        System.out.println(adminUser.getAllCompanies());


        Company company = new Company(3,"Google","google@gmail.com","google");
        CompanyFacade companyUser =(CompanyFacade) loginManager.login(company.getCompanyEmail(), company.getCompanyEmail(),ClientType.COMPANY);
       // companyUser.addCoupon(new Coupon(001,3, Category.getCategory(1),"googleCoupon","DiscountCoupon",1/2/2022,30/11/2022,3,100,"@@@"));
        companyUser.getCompanyDetails();


        Customer customer = new Customer(101,"Abdo","Dodo","abdo@hotmail.com","abdo");
        CustomerFacade customerUser = (CustomerFacade) loginManager.login(customer.getCustomerEmail(), customer.getPassword(), ClientType.CUSTOMER);
        customerUser.getCustomerDetails(customer);


        CouponExpirationDailyJob couponExpirationDailyJob = new CouponExpirationDailyJob();
        Thread jobThread = new Thread(couponExpirationDailyJob);
        jobThread.start();
        couponExpirationDailyJob.stop();












    }
}
