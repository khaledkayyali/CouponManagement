package project.facade;

import project.beans.Company;
import project.beans.Category;
import project.beans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyFacade extends ClientFacade{

    private int companyID;

    public CompanyFacade() {
    }

    @Override
    public boolean login(String email, String password) throws SQLException, InterruptedException {
        return companiesDAO.isCompanyExists(email, password);
    }

    public void addCoupon(Coupon coupon) throws SQLException, InterruptedException {
        if (!couponsDAO.couponExists(coupon)){
        couponsDAO.addCoupon(coupon);
      }else
            System.out.println("We have coupon with same title at this company!! you can't add it");
    }

    public void updateCoupon(Coupon coupon) throws SQLException, InterruptedException {
       if (!couponsDAO.couponExists(coupon)){
           System.out.println("the coupon does not exists");
       }
        if (coupon.getId() != couponsDAO.getOneCoupon(coupon.getId()).getId()){
            System.out.println("You can't update couponID");
        }
        if (couponsDAO.getOneCoupon(coupon.getId()).getCompanyID() != coupon.getCompanyID()){
            System.out.println("You can't update companyID");
        }
        couponsDAO.updateCoupon(coupon);
    }

    public void deleteCoupon(int couponID) throws SQLException, InterruptedException {

        couponsDAO.deleteCoupon(couponID);
    }

    public ArrayList getCompanyCoupons() throws SQLException, InterruptedException {
        return couponsDAO.getAllCouponsFromCompany(companyID);
    }

    public ArrayList getCompanyCoupons(Company company, Category category) throws SQLException, InterruptedException {

        return couponsDAO.getCompanyCoupons(company,category);

    }
    public ArrayList getCompanyCoupons(Company company,double maxPrice) throws SQLException, InterruptedException {

        return couponsDAO.getCompanyCoupons(company,maxPrice);
    }

    public Company getCompanyDetails() throws SQLException, InterruptedException {

        return companiesDAO.getOneCompany(companyID);
    }


}
