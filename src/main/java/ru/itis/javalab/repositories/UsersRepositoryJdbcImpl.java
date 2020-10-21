package ru.itis.javalab.repositories;

import com.zaxxer.hikari.HikariDataSource;
import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_SELECT_BY_AGE = "select * from accounts where age =  ?";
    //language=SQL
    private static final String SQL_SELECT = "select * from accounts";
    //language=SQL
    private static final String SQL_SAVE = "insert into accounts(username, password, age) VALUES (?,?,?)";
    //language=SQL
    private static final String SQL_UPDATE = "update accounts set username = ?, password = ?, age = ? where id = ?";
    //language=SQL
    private static final String SQL_DELETE_BY_ID = "delete from accounts where id = ?";
    //language=SQL
    //private static final String SQL_DELETE = "";

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private final DataSource dataSource;

    private final RowMapper<User> userRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .username(row.getString("username"))
            .password(row.getString("password"))
            .age(row.getInt("age"))
            .build();

    @Override
    public List<User> findAll() {
        return new SimpleJdbcTemplate(dataSource).query(SQL_SELECT, userRowMapper);
    }

    @Override
    public List<User> findAllByAge(Integer age) {
        return new SimpleJdbcTemplate(dataSource).query(SQL_SELECT_BY_AGE, userRowMapper, age);
    }

    @Override
    public Optional<User> findFirstByNameAndLastname(String firstName, String lastName) {
        return Optional.empty();
    }


    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(User entity) {
        new SimpleJdbcTemplate(dataSource).query(SQL_SAVE, userRowMapper, entity.getId(), entity.getUsername(), entity.getPassword(), entity.getAge());
    }

    @Override
    public void update(User entity) {
        new SimpleJdbcTemplate(dataSource).query(SQL_UPDATE, userRowMapper, entity.getId(), entity.getUsername(), entity.getPassword(), entity.getAge());
    }

    @Override
    public void deleteByID(Long id) {
        new SimpleJdbcTemplate(dataSource).query(SQL_DELETE_BY_ID, userRowMapper, id);
        System.out.println("Success");
    }

    @Override
    public void delete(User entity) {
    }
}
