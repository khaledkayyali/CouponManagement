package com.dbDao;

import com.beans.Category;
import com.beans.Company;
import com.beans.Customer;
import com.coupon_db.sql.utils.ConnectionPool;
import com.dao.CouponsDAO;
import com.beans.Coupon;

import java.sql.*;
import java.util.ArrayList;

public class CouponsDBDAO implements CouponsDAO {
    private final ConnectionPool pool;
    private Connection connection;

    public CouponsDBDAO() {
        pool = ConnectionPool.getInstance();
    }

    @Override
    public boolean couponExists(Coupon coupon) throws InterruptedException, SQLException {
        connection = pool.getConnection();
        String query = "SELECT * FROM `coupons`" +
                " WHERE `TITLE` = '" + coupon.getTitle() +
                "' AND `COMPANY_ID` = '" + coupon.getCompanyID() + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(query);
        boolean exists = resultSet.next();

        preparedStatement.close();
        connection.close();
        return exists;
    }

    @Override
    public void addCoupon(Coupon coupon) throws InterruptedException, SQLException {
        connection = pool.getConnection();
        String query = "INSERT INTO COUPON "
                + "(ID,COMPANY_ID,CATEGORY_ID,TITLE,DESCRIPTION,START_DATE,END_DATE,AMOUNT,PRICE,IMAGE) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,coupon.getId());
        preparedStatement.setInt(2,coupon.getCompanyID());
        preparedStatement.setInt(3,coupon.getCategory().getId());
        preparedStatement.setString(4, coupon.getTitle());
        preparedStatement.setString(5, coupon.getDescription());
        preparedStatement.setDate(6, (Date) coupon.getStartDate());
        preparedStatement.setDate(7,(Date) coupon.getEndDate());
        preparedStatement.setInt(8,coupon.getAmount());
        preparedStatement.setDouble(9,coupon.getPrice());
        preparedStatement.setString(10,coupon.getImage());

        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void updateCoupon(Coupon coupon) throws InterruptedException, SQLException {
        connection = pool.getConnection();
        String query = "UPDATE COUPON SET " +
                "ID = ? " +
                "COMPANY_ID = ?" +
                "CATEGORY_ID = ? " +
                "TITLE = ?" +
                "DESCRIPTION = ? " +
                "START_DATE = ? " +
                "END_DATE =? " +
                "AMOUNT = ? " +
                "PRICE = ? " +
                "IMAGE = ? " +
                "WHERE ID = " + coupon.getId() ;

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,coupon.getId());
        preparedStatement.setInt(2,coupon.getCompanyID());
        preparedStatement.setInt(3,coupon.getCategory().getId());
        preparedStatement.setString(4, coupon.getTitle());
        preparedStatement.setString(5, coupon.getDescription());
        preparedStatement.setDate(6, (Date) coupon.getStartDate());
        preparedStatement.setDate(7,(Date) coupon.getEndDate());
        preparedStatement.setInt(8,coupon.getAmount());
        preparedStatement.setDouble(9,coupon.getPrice());
        preparedStatement.setString(10,coupon.getImage());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void deleteCoupon(int couponID) throws InterruptedException, SQLException {

        connection = pool.getConnection();
        String query = "DELETE FROM COUPON WHERE ID = ? ";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,couponID);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

    }

    @Override
    public ArrayList<Coupon> getAllCoupons() throws InterruptedException, SQLException {

        ArrayList<Coupon> coupons = new ArrayList<>();
        connection = pool.getConnection();
        String query = "SELECT * FROM COUPON ";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
        coupons.add(new Coupon(
                resultSet.getInt("ID"),
                resultSet.getInt("COMPANY_ID"),
                resultSet.getInt("CATEGORY_ID"),
                resultSet.getString("TITLE" ),
                resultSet.getString("DESCRIPTION"),
                resultSet.getDate("START_DATE"),
                resultSet.getDate("END_DATE"),
                resultSet.getInt("AMOUNT"),
                resultSet.getInt("PRICE "),
                resultSet.getString("IMAGE ")));
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return coupons;
    }



    public ArrayList<Coupon> getAllCouponsFromCompany(int companyId) throws InterruptedException, SQLException {

            ArrayList<Coupon> coupons = new ArrayList<>();
            connection = pool.getConnection();
            String query = "SELECT * FROM COUPON " +
                    "WHERE COMPANY_ID = ? " ;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,companyId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                coupons.add(new Coupon(
                        resultSet.getInt("ID"),
                        resultSet.getInt("COMPANY_ID"),
                        resultSet.getInt("CATEGORY_ID"),
                        resultSet.getString("TITLE" ),
                        resultSet.getString("DESCRIPTION"),
                        resultSet.getDate("START_DATE"),
                        resultSet.getDate("END_DATE"),
                        resultSet.getInt("AMOUNT"),
                        resultSet.getInt("PRICE "),
                        resultSet.getString("IMAGE ")));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return coupons;
    }

    @Override
    public Coupon getOneCoupon(int couponID) throws InterruptedException, SQLException {

        Coupon coupon = new Coupon();
        connection = pool.getConnection();
        String query = "SELECT * FROM COUPON WHERE ID = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,couponID);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean exists = resultSet.next();
        while (exists){
            coupon = new Coupon(
                    resultSet.getInt("ID"),
                    resultSet.getInt("COMPANY_ID"),
                    resultSet.getInt("CATEGORY_ID"),
                    resultSet.getString("TITLE" ),
                    resultSet.getString("DESCRIPTION"),
                    resultSet.getDate("START_DATE"),
                    resultSet.getDate("END_DATE"),
                    resultSet.getInt("AMOUNT"),
                    resultSet.getInt("PRICE "),
                    resultSet.getString("IMAGE "));
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return coupon;
    }


    @Override
    public ArrayList<Coupon> getCompanyCoupons(Company company, Category CATEGORY) throws InterruptedException, SQLException{
        ArrayList<Coupon> list = new ArrayList<>();
        connection = pool.getConnection();
        String sqlQuery = "SELECT * FROM coupons " +
                "WHERE `COMPANY_ID` = " + company.getId() +
                " AND `CATEGORY_ID` = " + CATEGORY.getId();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            list.add(new Coupon(
                    resultSet.getInt("ID"),
                    resultSet.getInt("COMPANY_ID"),
                    resultSet.getInt("CATEGORY_ID"),
                    resultSet.getString("TITLE"),
                    resultSet.getString("DESCRIPTION"),
                    resultSet.getDate("START_DATE"),
                    resultSet.getDate("END_DATE"),
                    resultSet.getInt("AMOUNT"),
                    resultSet.getInt("PRICE"),
                    resultSet.getString("IMAGE")));
        }

        resultSet.close();
        statement.close();
        connection.close();

        return list;
    }

    @Override
    public ArrayList<Coupon> getCompanyCoupons(Company company, double maxPrice) throws InterruptedException, SQLException{
        ArrayList<Coupon> list = new ArrayList<>();
        connection = pool.getConnection();
        String sqlQuery = "SELECT * FROM coupons " +
                "WHERE `COMPANY_ID`= " + company.getId() +
                " AND `PRICE` IS BETWEEN 0 AND " + (int) maxPrice;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            list.add(new Coupon(
                    resultSet.getInt("ID"),
                    resultSet.getInt("COMPANY_ID"),
                    resultSet.getInt("CATEGORY_ID"),
                    resultSet.getString("TITLE"),
                    resultSet.getString("DESCRIPTION"),
                    resultSet.getDate("START_DATE"),
                    resultSet.getDate("END_DATE"),
                    resultSet.getInt("AMOUNT"),
                    resultSet.getInt("PRICE"),
                    resultSet.getString("IMAGE")));
        }

        resultSet.close();
        statement.close();
        connection.close();

        return list;
    }


    @Override
    public void addCouponPurchase(int customerID, int couponID) throws InterruptedException, SQLException {

        connection = pool.getConnection();
        String query = "INSERT INTO customers_vs_coupons " +
                "WHERE CUSTOMER_ID = ?"
                + "AND COUPON_ID = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,customerID);
        preparedStatement.setInt(2,couponID);
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

    }

    @Override
    public void deleteCouponPurchase(int customerID, int couponID) throws InterruptedException, SQLException {

        connection = pool.getConnection();
        String query = "DELETE * FROM `customers_vs_coupons` " +
                "WHERE `CUSTOMERID` = ?" +
                "AND `COUPONID` = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,customerID);
        preparedStatement.setInt(2,couponID);

        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();


    }

    @Override
    public boolean customerCouponPurchaseExists(int customerId, int couponId) throws InterruptedException, SQLException {
        connection = pool.getConnection();
        String sqlQuery = "SELECT * FROM customers_vs_coupons " +
                "WHERE `CUSTOMER_ID` = " + customerId +
                " AND `COUPON_ID` = " + couponId;
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery(sqlQuery);
        boolean exists = resultSet.next();

        preparedStatement.close();
        resultSet.close();
        connection.close();

        return exists;
    }

    @Override
    public ArrayList<Coupon> getCustomerCoupons(Customer customer) throws InterruptedException, SQLException {
        ArrayList<Coupon> list = new ArrayList<>();
        connection = pool.getConnection();
        String sqlQuery = "SELECT `COUPON_ID` FROM customers_vs_coupons " +
                "WHERE `CUSTOMER_ID` = " + customer.getId();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            list.add(getOneCoupon(resultSet.getInt("COUPON_ID")));
        }

        resultSet.close();
        statement.close();
        connection.close();

        return list;
    }



}
