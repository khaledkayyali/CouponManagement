package com.login;

import java.io.PrintStream;

public enum ClientType {
    ADMINISTRATOR("admin"),
    COMPANY("company"),
    CUSTOMER("customer");


    private String type;

    public String getType() {
        return type;
    }

    ClientType(String type) {
    }

    private static ClientType getClientType(String type) {
       ClientType clientTypes = null;
        for (ClientType clientType : values()){
            if (clientType.type.equals(type)){
                return clientType;
            }
        }
        return clientTypes;
    }
}
