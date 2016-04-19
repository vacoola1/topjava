package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import javax.sql.DataSource;
import javax.validation.constraints.Null;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;

/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
public class JdbcUserRepositoryImpl implements UserRepository {

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    private class MultiRowMapper implements RowMapper<User> {
        private List<User> users = new ArrayList<>();
        private User currentUser;

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {

            if (currentUser == null || currentUser.getId() != rs.getInt("id")) {
                currentUser = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("calories_per_day"),
                        rs.getBoolean("enabled"),
                        new HashSet<>());

                users.add(currentUser);
            }

            String nameOfRole = rs.getString("role");
            if (nameOfRole != null) {
                Role role = Role.valueOf(rs.getString("role"));
                currentUser.getRoles().add(role);
            }

            return null;
        }

        public List<User> getUsers() {
            return users;
        }
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertUser;

    @Autowired
    public JdbcUserRepositoryImpl(DataSource dataSource) {
        this.insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("USERS")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    @Transactional
    public User save(User user) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("name", user.getName())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("registered", user.getRegistered())
                .addValue("enabled", user.isEnabled())
                .addValue("caloriesPerDay", user.getCaloriesPerDay());

        MapSqlParameterSource mapRoles;

        if (user.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(map);
            user.setId(newKey.intValue());

            for (Role role : user.getRoles()) {
                jdbcTemplate.update("INSERT INTO user_roles (user_id, role) VALUES (?, ?)", user.getId(), role.toString());
            }

        } else {
            namedParameterJdbcTemplate.update(
                    "UPDATE users SET name=:name, email=:email, password=:password, " +
                            "registered=:registered, enabled=:enabled, calories_per_day=:caloriesPerDay WHERE id=:id", map);

            jdbcTemplate.update("DELETE FROM user_roles WHERE user_id=?", user.getId());
            for (Role role : user.getRoles()) {
                jdbcTemplate.update("INSERT INTO user_roles (user_id, role) VALUES (?, ?)", user.getId(), role.toString());
            }
        }


        return user;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
    public User get(int id) {

        MultiRowMapper multiRowMapper = new MultiRowMapper();
        jdbcTemplate.query("SELECT * FROM (SELECT * FROM users WHERE id=?) AS users LEFT OUTER JOIN user_roles ON users.id = user_roles.user_id", multiRowMapper, id);

        return DataAccessUtils.singleResult(multiRowMapper.getUsers());
    }

    @Override
    public User getByEmail(String email) {
        MultiRowMapper multiRowMapper = new MultiRowMapper();
        jdbcTemplate.queryForObject("SELECT * FROM (SELECT * FROM users WHERE email=?) AS users LEFT OUTER JOIN user_roles ON users.id = user_roles.user_id", multiRowMapper, email);
        return DataAccessUtils.singleResult(multiRowMapper.getUsers());
    }

    @Override
    public List<User> getAll() {
        MultiRowMapper multiRowMapper = new MultiRowMapper();
        jdbcTemplate.query("SELECT * FROM users LEFT OUTER JOIN user_roles ON users.id = user_roles.user_id ORDER BY name, email", multiRowMapper);
        return multiRowMapper.getUsers();
    }
}
