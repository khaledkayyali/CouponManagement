package com;

import java.sql.SQLException;

public class Program {
    public static void main(String[] args)  {
        try {
            Test.testAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
