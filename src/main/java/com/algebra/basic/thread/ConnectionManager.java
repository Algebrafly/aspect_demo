package com.algebra.basic.thread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author al
 * @date 2020/1/10 13:46
 * @description spring-bean ：做到同一事务多DAO共享同一Connection，必须在一个共同的外部类使用ThreadLocal保存Connection。
 */
public class ConnectionManager {
    private static ThreadLocal<Connection> connectionHolder = ThreadLocal.withInitial(() -> {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test", "username",
                    "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    });
    public static Connection getConnection() {
        return connectionHolder.get();
    }
    public static void setConnection(Connection conn) {
        connectionHolder.set(conn);
    }
}
