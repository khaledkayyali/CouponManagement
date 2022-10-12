package project.dao;

import project.beans.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomersDAO {
    public boolean isCustomerExists(String email,String password) throws InterruptedException, SQLException;

    public void addCustomer(Customer customer) throws InterruptedException, SQLException;

    public void updateCustomer(Customer customer) throws InterruptedException, SQLException;

    public void deleteCustomer( int customerID) throws InterruptedException, SQLException;

    public ArrayList<Customer> getAllCustomers() throws InterruptedException, SQLException;

    public Customer getOneCustomer(int customerID) throws InterruptedException, SQLException;
}
