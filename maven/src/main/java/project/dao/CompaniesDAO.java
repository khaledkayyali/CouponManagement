package project.dao;

import project.beans.Company;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CompaniesDAO {

    public boolean isCompanyExists(String companyEmail,String companyPassword) throws InterruptedException, SQLException;

    public void addCompany(Company company) throws InterruptedException, SQLException;

    public void updateCompany(Company company) throws InterruptedException, SQLException;

    public void deleteCompany( int companyID) throws InterruptedException, SQLException;

    public ArrayList<Company> getAllCompanies() throws InterruptedException, SQLException;

    public Company getOneCompany(int companyID) throws InterruptedException, SQLException;
}
