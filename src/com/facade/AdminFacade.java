package com.facade;

import com.beans.Company;
import com.beans.Coupon;
import com.beans.Customer;
import com.dao.CompaniesDAO;
import com.dbDao.CompaniesDBDAO;

import java.awt.image.AreaAveragingScaleFilter;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminFacade extends ClientFacade{

    public AdminFacade() {
    }

    @Override
    public boolean login(String email, String password) {
        String adminEmail = "admin@admin.com";
        String adminPassword = "admin";

        boolean signIn;
        if (email == adminEmail && password == adminPassword ){
            return signIn = true;
        }else
        return signIn = false;
    }

    public void addCompany(Company company) throws SQLException, InterruptedException {
        if (companiesDAO.isCompanyExists(company.getCompanyEmail(), company.getCompanyPassword())){
            System.out.println("company email is already exists" + company.getCompanyEmail());
        }

        for (Company companies : companiesDAO.getAllCompanies()){
            if (company.getName() == companies.getName()){
                System.out.println("Company name is already exists");
            }
        }
        companiesDAO.addCompany(company);
    }

    public void updateCompany(Company company) throws SQLException, InterruptedException {
        if (!companiesDAO.isCompanyExists(company.getCompanyEmail(), company.getCompanyPassword())){
            System.out.println("unable to update company, the company don't exists");
        }
        if (company.getId() != companiesDAO.getOneCompany(company.getId()).getId()){
            System.out.println("unable to update CompanyID");
        }
        if (companiesDAO.getOneCompany(company.getId()).getName() != company.getName()){
            System.out.println("Unable to update Company Name");
        }
        companiesDAO.updateCompany(company);
    }

    public void deleteCompany(int companyID) throws SQLException, InterruptedException {
       for (Coupon coupon : couponsDAO.getAllCoupons()){
           if (coupon.getCompanyID() == companyID){
               couponsDAO.deleteCoupon(coupon.getId());
           }
       }
        companiesDAO.deleteCompany(companyID);
    }

    public ArrayList getAllCompanies() throws SQLException, InterruptedException {

        return companiesDAO.getAllCompanies();
    }

    public Company getOneCompany(int companyID) throws SQLException, InterruptedException {

        return companiesDAO.getOneCompany(companyID);
    }

    public void addCustomer(Customer customer) throws SQLException, InterruptedException {

        if (customersDAO.isCustomerExists(customer.getCustomerEmail(), customer.getPassword())){
            System.out.println("Customer email is already exists ");
        }
        customersDAO.addCustomer(customer);
    }

    public void updateCustomer(Customer customer) throws SQLException, InterruptedException {

        if (customersDAO.getOneCustomer(customer.getId()).getId() != customer.getId()){
            System.out.println("Unable to update customerId");
        }
        customersDAO.updateCustomer(customer);
    }

    public void deleteCustomer(int customerID) throws SQLException, InterruptedException {
        customersDAO.deleteCustomer(customerID);

    }

    public ArrayList getAllCustomers() throws SQLException, InterruptedException {

        return customersDAO.getAllCustomers();
    }

    public Customer getOneCustomer(int customerID) throws SQLException, InterruptedException {

        return customersDAO.getOneCustomer(customerID);
    }
}
