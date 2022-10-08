package com.login;

import com.facade.AdminFacade;
import com.facade.ClientFacade;
import com.facade.CompanyFacade;
import com.facade.CustomerFacade;
import exceptions.InvalidLoginCredentialsException;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class LoginManager {

    private static LoginManager instance;
    private static ClientFacade facade;

    private LoginManager() {
    }

    public static LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }

    public ClientFacade login(String email, String password, ClientType clientType) throws Exception{
        switch (clientType.getType()) {
            case "admin":
                facade = new AdminFacade();
                break;
            case "company":
                facade = new CompanyFacade();
                break;
            case "customer":
                facade = new CustomerFacade();
                break;
            default:
                System.out.println("Not Type");
        }
        if (facade.login(email, password))
            return facade;
        else
            throw new InvalidLoginCredentialsException("Could not Authenticate using email & password: " + email + ", " + password);
    }
}





