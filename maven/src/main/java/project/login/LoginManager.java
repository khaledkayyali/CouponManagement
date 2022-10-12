package project.login;

import project.facade.AdminFacade;
import project.facade.ClientFacade;
import project.facade.CompanyFacade;
import project.facade.CustomerFacade;


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

    public ClientFacade login(String email, String password, ClientType clientType) throws Exception, InvalidLoginCredentialsException, ClientTypeNotFoundException {
        switch (ClientType.getClientType(clientType.getType())) {
            case ADMINISTRATOR:
                facade = new AdminFacade();
                break;
            case COMPANY:
                facade = new CompanyFacade();
                break;
            case CUSTOMER:
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





