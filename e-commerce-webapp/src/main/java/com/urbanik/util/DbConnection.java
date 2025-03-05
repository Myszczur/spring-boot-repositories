package com.urbanik.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author kurbanik
 */
public class DbConnection {

    public Connection getConnection() throws SQLException, IOException {
        return dbConnection();
    }

    private Connection dbConnection() throws IOException, SQLException {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/ecommerceDB");
            return ds.getConnection();
        } catch (Exception e) {
            Properties properties = new Properties();
            try (InputStream is = DbConnection.class.getClassLoader().getResourceAsStream("application.properties")) {
                properties.load(is);
            }
            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            return DriverManager.getConnection(url, username, password);
        }
    }
}
