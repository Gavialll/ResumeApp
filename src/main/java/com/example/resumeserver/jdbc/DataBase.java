package com.example.resumeserver.jdbc;

import org.postgresql.Driver;

import java.sql.*;

public class DataBase {
    private final String url = "jdbc:postgresql://ec2-52-212-228-71.eu-west-1.compute.amazonaws.com:5432/d1nl565c8vgbgm";
    private final String user = "fbrfbhbwoicuqg";
    private final String password = "6c3d2409c6735d8348487f69ea985c3426caa1caac46cd7afd08530ebade2131";

    public DataBase() throws SQLException {
        DriverManager.registerDriver(new Driver());
    }

    public int getCount() throws SQLException {
        int count;
        Connection connection = DriverManager.getConnection(url,user, password);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("select * from Count where id = 0");
        rs.next();
        count = rs.getInt("count");
        rs.close();
        st.close();

        return count;
    }

    public void setCount(int count) throws SQLException {
        Connection connection = DriverManager.getConnection(url,user, password);
        Statement st = connection.createStatement();
        st.executeUpdate("update count set count = " + count + " where id = 0;");
        st.close();
    }
}
