package com.example.resumeserver;

import org.postgresql.Driver;

import java.sql.*;
import static com.example.resumeserver.dao.DataBase.URL;
import static com.example.resumeserver.dao.DataBase.PASSWORD;
import static com.example.resumeserver.dao.DataBase.USER;

public class DataBase {
    public DataBase() throws SQLException {
        DriverManager.registerDriver(new Driver());
    }

    // Get size visitors
    public int getCount() throws SQLException {
        int count;
        Connection connection = DriverManager.getConnection(URL,USER, PASSWORD);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("select * from Count where id = 0");
        rs.next();
        count = rs.getInt("count");
        rs.close();
        st.close();

        return count;
    }

    // Set size visitors.
    public void setCount(int count) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement st = connection.createStatement();
        st.executeUpdate("update count set count = " + count + " where id = 0;");
        st.close();
    }
}
