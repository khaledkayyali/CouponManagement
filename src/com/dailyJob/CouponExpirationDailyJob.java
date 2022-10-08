package com.dailyJob;

import com.beans.Coupon;
import com.dao.CouponsDAO;
import com.dbDao.CouponsDBDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CouponExpirationDailyJob implements Runnable{

    private CouponsDAO couponsDAO = new CouponsDBDAO();
    private boolean quit = false;

    public CouponExpirationDailyJob(CouponsDAO couponsDAO, boolean quit) {
        this.couponsDAO = couponsDAO;
        this.quit = quit;
    }

    public CouponExpirationDailyJob() {

    }

    @Override
    public void run() {
        while (!quit) {
            ArrayList<Coupon> coupons = new ArrayList<>();
            try {
                coupons = couponsDAO.getAllCoupons();
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }
            for (Coupon coupon : coupons) {
                if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
                    try {
                        couponsDAO.deleteCoupon(coupon.getId());
                    } catch (InterruptedException | SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public void stop(){
        quit = true;
    }
}
