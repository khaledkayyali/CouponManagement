package project;

import project.login.ClientTypeNotFoundException;
import project.login.InvalidLoginCredentialsException;

public class Program {
    public static void main(String[] args) {
        try {
            Test.testAll();
        } catch (Exception | InvalidLoginCredentialsException | ClientTypeNotFoundException e) {
            e.printStackTrace();
        }
    }
}
