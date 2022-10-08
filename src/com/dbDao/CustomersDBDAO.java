package com.dbDao;

import com.coupon_db.sql.utils.ConnectionPool;
import com.dao.CustomersDAO;
import com.beans.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomersDBDAO implements CustomersDAO {

    private final ConnectionPool pool;
    private Connection connection;

    public CustomersDBDAO() {
       pool = ConnectionPool.getInstance();
    }

    @Override
    public boolean isCustomerExists(String customerEmail, String password) throws InterruptedException, SQLException {
    connection = pool.getConnection();

    String query = "SELECT * FROM CUSTOMER " +
            "WHERE EMAIL = " + customerEmail
            + "AND PASSWORD =" + password;

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(query);
        boolean exists = resultSet.next();
        preparedStatement.close();
        resultSet.close();
        connection.close();

    return exists;
    }

    @Override
    public void addCustomer(Customer customer) throws InterruptedException, SQLException {

        connection = pool.getConnection();
        String query = "INSERT INTO CUSTOMER"
                + "FIRSTNAME , LASTNAME, CUSTOMEREMAIL, PASSWORD"
                + "VALUES (?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,customer.getFirstName());
        preparedStatement.setString(2,customer.getLastName());
        preparedStatement.setString(3, customer.getCustomerEmail());
        preparedStatement.setString(4, customer.getPassword());

        preparedStatement.execute();
        preparedStatement.close();
        connection.close();

    }

    @Override
    public void updateCustomer(Customer customer) throws InterruptedException, SQLException {

        connection = pool.getConnection();
        String query = "UPDATE CUSTOMER "
                + "SET FIRSTNAME = ?"
                + "SET LASTNAME = ?"
                + "SET CUSTOMEREMAIL = ?"
                + "SET PASSWORD = ? "
                + "WHERE ID = ? ";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, customer.getFirstName());
        preparedStatement.setString(2, customer.getLastName());
        preparedStatement.setString(3, customer.getCustomerEmail());
        preparedStatement.setString(4, customer.getPassword());
        preparedStatement.setInt(5,customer.getId());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

    }

    @Override
    public void deleteCustomer(int customerID) throws InterruptedException, SQLException {

        connection = pool.getConnection();
        String query = "DELETE * FROM CUSTOMER WHERE ID = " + customerID;

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

    }

    @Override
    public ArrayList<Customer> getAllCustomers() throws InterruptedException, SQLException {
        ArrayList<Customer> list = new ArrayList<>();

        connection = pool.getConnection();
        String query = "SELECT * FROM CUSTOMER";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(query);

        while (resultSet.next()){
            list.add(new Customer(
                    resultSet.getInt("ID"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("customerEmail"),
                    resultSet.getString("password")));
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return list;
    }

    @Override
    public Customer getOneCustomer(int customerID) throws InterruptedException, SQLException {
        Customer customer = new Customer();

        connection = pool.getConnection();
        String query = "SELECT * FROM CUSTOMER WHERE ID = ?" + customerID;

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(query);

        while (resultSet.next()){
            customer = new Customer(
                    resultSet.getInt("ID"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("customerEmail"),
                    resultSet.getString("password"));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return customer;
    }
}
