package com.dbDao;

import com.coupon_db.sql.utils.ConnectionPool;
import com.dao.CompaniesDAO;
import com.beans.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompaniesDBDAO implements CompaniesDAO {
    private final ConnectionPool pool;
    private Connection connection;

    public CompaniesDBDAO() {
        pool = ConnectionPool.getInstance();
    }

    @Override
    public boolean isCompanyExists(String companyEmail, String companyPassword) throws InterruptedException, SQLException {
    connection = pool.getConnection();
    String query = "SELECT * FROM `companies` "
            + "WHERE `EMAIL` = " + companyEmail
            + " AND `PASSWORD` = " + companyPassword;

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(query);
        boolean exists = resultSet.next();
        preparedStatement.close();
        connection.close();
        resultSet.close();
        return exists;
    }

    @Override
    public void addCompany(Company company) throws InterruptedException, SQLException {
    connection = pool.getConnection();
    String query = "INSERT INTO `companies` "
            + "ID, NAME, EMAIL , PASSWORD "
            + " VALUES (?,?,?) ";

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setString(1, company.getName());
    preparedStatement.setString(2, company.getCompanyEmail());
    preparedStatement.setString(3, company.getCompanyPassword());

    preparedStatement.execute();
    preparedStatement.close();
    connection.close();

    }

    @Override
    public void updateCompany(Company company) throws InterruptedException, SQLException {

        connection = pool.getConnection();
        String query = "UPDATE `COMPANIES`"
                +  "SET `NAME` =? "
                + "SET `EMAIL` = ? "
                + "SET `PASSWORD` = ? "
                + " WHERE `ID` = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, company.getName());
        preparedStatement.setString(2, company.getCompanyEmail());
        preparedStatement.setString(3, company.getCompanyPassword());
        preparedStatement.setInt(4,company.getId());

        preparedStatement.executeUpdate();
        preparedStatement.close();
       connection.close();
    }

    @Override
    public void deleteCompany(int companyID) throws InterruptedException, SQLException {

        connection = pool.getConnection();
        String query = "DELETE FROM `COMPANIES` WHERE `ID` = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,companyID );
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

    }

    @Override
    public ArrayList<Company> getAllCompanies() throws InterruptedException, SQLException {
        
        ArrayList<Company> list = new ArrayList<>();
        
        connection = pool.getConnection();
        String query = "SELECT * FROM COMPANIES ";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(query);
        
        while(resultSet.next()){
            list.add(new Company(
                    resultSet.getInt("ID"),
                    resultSet.getString("NAME"),
                    resultSet.getString("EMAIL"),
                    resultSet.getString("PASSWORD")));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        
        return list;
    }

    @Override
    public Company getOneCompany(int companyID) throws InterruptedException, SQLException {
        Company company = null;
        connection = pool.getConnection();
        String query = "SELECT * FROM COMPANIES WHERE ID = " + companyID;
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet =preparedStatement.executeQuery(query);
        boolean exists = resultSet.next();
        if (exists){
            company = new Company(
                    resultSet.getInt("ID"),
                    resultSet.getString("NAME"),
                    resultSet.getString("EMAIL"),
                    resultSet.getString("PASSWORD"));
        }else {
            System.out.println("company not found !!");
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return company;
    }
}
