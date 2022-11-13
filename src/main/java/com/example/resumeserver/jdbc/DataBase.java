package com.example.resumeserver.jdbc;

import org.postgresql.Driver;

import java.sql.*;

public class DataBase {
    private final String url = "jdbc:postgresql://ec2-54-220-255-121.eu-west-1.compute.amazonaws.com:5432/d8pr1qp61dee7o";
    private final String user = "dqratdzdcapspm";
    private final String password = "476f7943073c2a6211d9d761da458f1f0f5af1283b6edb0dd9733b6e1594580d";

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
