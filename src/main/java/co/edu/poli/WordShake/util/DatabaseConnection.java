package co.edu.poli.WordShake.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

        private static final String url = "jdbc:mysql://" + Config.get("DB_HOST") + ":" + Config.get("DB_PORT") + "/" + Config.get("DB_NAME");
        private static final String user = Config.get("DB_USER");
        private static final String pass = Config.get("DB_PASSWORD");

        private static Connection myConn;

        public static Connection getInstance() throws SQLException {
            if (myConn == null) {
                myConn = DriverManager.getConnection(url,user,pass);
            }
            return myConn;
        }
    }

