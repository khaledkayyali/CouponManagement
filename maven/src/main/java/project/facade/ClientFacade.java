package project.facade;

import project.dao.CompaniesDAO;
import project.dao.CouponsDAO;
import project.dao.CustomersDAO;
import project.dbDao.CompaniesDBDAO;
import project.dbDao.CouponsDBDAO;
import project.dbDao.CustomersDBDAO;

import java.sql.SQLException;

public abstract class ClientFacade {

    protected final CustomersDAO customersDAO = new CustomersDBDAO();
    protected final CouponsDAO couponsDAO = new CouponsDBDAO();
    protected final CompaniesDAO companiesDAO = new CompaniesDBDAO();

    public abstract boolean login (String email,String password) throws SQLException, InterruptedException;

}
