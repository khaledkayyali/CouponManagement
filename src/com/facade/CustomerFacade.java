package com.facade;

import com.beans.Category;
import com.beans.Coupon;
import com.beans.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CustomerFacade extends ClientFacade {

    private int customerID;

    public CustomerFacade() {
    }

    @Override
    public boolean login(String email, String password) throws SQLException, InterruptedException {

        return customersDAO.isCustomerExists(email, password);
    }

    public void purchaseCoupon(Coupon coupon, Customer customer) throws SQLException, InterruptedException {
        if (couponsDAO.customerCouponPurchaseExists(customer.getId(), coupon.getId())) {
            System.out.println("unable to purchase!");
        }
        if (couponsDAO.getOneCoupon(coupon.getId()).getAmount() <= 0) {
            System.out.println("unable to purchase , quantity!! " + coupon.getAmount());
        }
        couponsDAO.addCouponPurchase(customer.getId(), coupon.getId());

    }

    public ArrayList<Coupon> getCustomerCoupons(Customer customer) throws SQLException, InterruptedException {

        return couponsDAO.getCustomerCoupons(customer);
    }

    public ArrayList<Coupon> getCustomerCoupons(Customer customer, Category CATEGORY) throws SQLException, InterruptedException {
        ArrayList<Coupon> list = getCustomerCoupons(customer);
        list.removeIf(coupon -> !coupon.getCategory().equals(CATEGORY));
        return list;
    }

    public ArrayList<Coupon> getCustomerCoupons(Customer customer, double maxPrice) throws SQLException, InterruptedException {
        ArrayList<Coupon> list = getCustomerCoupons(customer);
        list.removeIf(coupon -> coupon.getPrice() > maxPrice);
        return list;
    }

    public Customer getCustomerDetails(Customer customer) throws SQLException, InterruptedException {
        return customersDAO.getOneCustomer(customer.getId());
    }
}