package com.wsv.core.dal;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceDal {

    BasicDataSource dataSource = DataBaseUtility.getDataSource();

    /**
     * Get JSON file of a service by the token.
     * @param token
     * @return
     * @throws SQLException
     */
    public static String getServiceDefinitionByToken(String token) throws SQLException {
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("token cannot be null or empty");
        }

        String json = null;
        Connection connection = null;
        try {
            int index = 1;
            BasicDataSource dataSource = DataBaseUtility.getDataSource();
            connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM service WHERE token = ? AND published = 1");
            pstmt.setString(index, token);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    json = resultSet.getString("jsonFile");
                }
            } catch (Exception e) {
                e.printStackTrace();
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return json;
    }
}
