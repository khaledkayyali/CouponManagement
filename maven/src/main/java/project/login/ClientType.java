package project.login;

public enum ClientType {
    ADMINISTRATOR("admin"),
    COMPANY("company"),
    CUSTOMER("customer");

    private final String type;

    public String getType() {
        return this.type;
    }

    ClientType(String type) {
        this.type = type;
    }

    public static ClientType getClientType(String type) throws ClientTypeNotFoundException {
        for (ClientType clientType : values()) {
            if (clientType.type.equals(type)) {
                return clientType;
            }
        }
        throw new ClientTypeNotFoundException("Could not find ClientType of type : " + type);
    }

}
