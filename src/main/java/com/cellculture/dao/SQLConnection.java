package com.cellculture.dao;
import java.sql.*;

//source: https://www.youtube.com/watch?v=AHFBPxWebFQ
public class SQLConnection {

        private static final String url = "jdbc:mysql://127.0.0.1:8889/ExCell";
        private static final String username = "mamp";
        private static final String password = "root";

        public static Connection getConnection()
                throws SQLException
    {

            return DriverManager.getConnection(
                   url, username, password
            );
        }

    }