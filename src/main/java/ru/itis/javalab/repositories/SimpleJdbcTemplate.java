package ru.itis.javalab.repositories;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleJdbcTemplate {
    private DataSource dataSource;

    public SimpleJdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }
            resultSet = statement.executeQuery();

            List<T> tList = new ArrayList<>();
            while (resultSet.next()) {
                T t = rowMapper.mapRow(resultSet);
                tList.add(t);
            }
            return tList;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ignore) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignore) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }
}