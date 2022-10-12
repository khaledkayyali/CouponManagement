package project.dao;

import project.beans.Company;
import project.beans.Category;
import project.beans.Coupon;
import project.beans.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CouponsDAO {
    boolean couponExists(Coupon coupon) throws InterruptedException, SQLException;

    public void addCoupon(Coupon coupon) throws InterruptedException, SQLException;

    public void updateCoupon(Coupon coupon) throws InterruptedException, SQLException;

    public void deleteCoupon( int couponID) throws InterruptedException, SQLException;

    public ArrayList<Coupon> getAllCoupons() throws InterruptedException, SQLException;

    public ArrayList<Coupon> getAllCouponsFromCompany (int companyId) throws InterruptedException, SQLException;

    public Coupon getOneCoupon(int couponID) throws InterruptedException, SQLException;

    ArrayList<Coupon> getCompanyCoupons(Company company, Category CATEGORY) throws InterruptedException, SQLException;

    ArrayList<Coupon> getCompanyCoupons(Company company, double maxPrice) throws InterruptedException, SQLException;

    public void addCouponPurchase(int customerID,int couponID) throws InterruptedException, SQLException;

    public void deleteCouponPurchase(int customerID,int couponID) throws InterruptedException, SQLException;

    public boolean customerCouponPurchaseExists(int customerId, int couponId) throws InterruptedException, SQLException;

    public ArrayList<Coupon> getCustomerCoupons(Customer customer) throws InterruptedException, SQLException;

}
