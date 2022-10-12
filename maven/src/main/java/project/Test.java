package project;

import project.beans.Company;
import project.beans.Customer;
import project.dailyJob.CouponExpirationDailyJob;
import project.facade.AdminFacade;
import project.facade.CompanyFacade;
import project.facade.CustomerFacade;
import project.login.ClientType;
import project.login.ClientTypeNotFoundException;
import project.login.InvalidLoginCredentialsException;
import project.login.LoginManager;

public class Test {
    public static void testAll() throws Exception, InvalidLoginCredentialsException, ClientTypeNotFoundException {

        LoginManager loginManager = LoginManager.getInstance();
        AdminFacade adminUser = (AdminFacade) loginManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);

        adminUser.addCompany(new Company(1, "Intel", "intel@hotmail.com", "intel"));
        adminUser.addCompany(new Company(2, "Microsoft", "microsoft@hotmail.com", "microsoft"));
        System.out.println(adminUser.getAllCompanies());


        Company company = new Company(3, "Google", "google@gmail.com", "google");
        CompanyFacade companyUser = (CompanyFacade) loginManager.login(company.getCompanyEmail(), company.getCompanyPassword(), ClientType.COMPANY);
        // companyUser.addCoupon(new Coupon(001,3, Category.getCategory(1),"googleCoupon","DiscountCoupon",1/2/2022,30/11/2022,3,100,"@@@"));
        companyUser.getCompanyDetails();


        Customer customer = new Customer(101, "Abdo", "Dodo", "abdo@hotmail.com", "abdo");
        CustomerFacade customerUser = (CustomerFacade) loginManager.login(customer.getCustomerEmail(), customer.getPassword(), ClientType.CUSTOMER);
        customerUser.getCustomerDetails(customer);


        CouponExpirationDailyJob couponExpirationDailyJob = new CouponExpirationDailyJob();
        Thread jobThread = new Thread(couponExpirationDailyJob);
        jobThread.start();
        couponExpirationDailyJob.stop();

    }
}
