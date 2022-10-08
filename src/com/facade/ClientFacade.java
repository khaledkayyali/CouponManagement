package com.facade;

import com.dao.CompaniesDAO;
import com.dao.CouponsDAO;
import com.dao.CustomersDAO;
import com.dbDao.CompaniesDBDAO;
import com.dbDao.CouponsDBDAO;
import com.dbDao.CustomersDBDAO;

import java.sql.SQLException;

public abstract class ClientFacade {

    protected final CustomersDAO customersDAO = new CustomersDBDAO();
    protected final CouponsDAO couponsDAO = new CouponsDBDAO();
    protected final CompaniesDAO companiesDAO = new CompaniesDBDAO();

    public abstract boolean login (String email,String password) throws SQLException, InterruptedException;

}
