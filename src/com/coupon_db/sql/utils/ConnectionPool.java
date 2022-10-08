package com.coupon_db.sql.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ConnectionPool {
    private static final String URL = "jdbc:mysql://localhost:3306/coupon_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final Set<Connection> connections = new HashSet<>();
    private static ConnectionPool instance = null;

    private ConnectionPool() {
        try {
            Class.forName(JDBC_DRIVER);
            for (int i = 50; i > 0; i--) {
                connections.add(DriverManager.getConnection(URL, USER, PASSWORD));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public synchronized Connection getConnection() throws InterruptedException {
        Connection connection = null;
        if (connections.isEmpty()) {
            wait();
        } else {
            Iterator<Connection> it = connections.iterator();
            connection = it.next();
            connections.remove(connection);
        }
        return connection;
    }

    public void restoreConnection(Connection connection) {
        connections.add(connection);
        notifyAll();
    }

    public static void closeAll() {
        Iterator<Connection> iterator = connections.iterator();
        while (iterator.hasNext() & iterator.next() != null) {
            try {
                iterator.next().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
