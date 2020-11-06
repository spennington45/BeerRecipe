package com.recipe.beerrecipe.jdbcdao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.recipe.beerrecipe.model.RegisterUserDTO;
import com.recipe.beerrecipe.model.User;

@Service
public class UserSqlDAO implements UserDAO {
	RegisterUserDTO registeredUser = new RegisterUserDTO();

	private JdbcTemplate jdbcTemplate;

	public UserSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// Communicates with database to get user by username
	@Override
	public int findIdByUsername(String username) {
		return jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
	}

	// Communicates with database to get username by user id
	@Override
	public User getUserById(Long userId) {
		String sql = "SELECT * FROM users WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		if (results.next()) {
			return mapRowToUser(results);
		} else {
			throw new RuntimeException("userId " + userId + " was not found.");
		}
	}

	// Communicates with database to get all users
	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		String sql = "select * from users";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			User user = mapRowToUser(results);
			users.add(user);
		}

		return users;
	}

	// Communicates with database to get user by username
	@Override
	public User findByUsername(String username) throws UsernameNotFoundException {
		for (User user : this.findAll()) {
			if (user.getUsername().toLowerCase().equals(username.toLowerCase())) {
				return user;
			}
		}
		throw new UsernameNotFoundException("User " + username + " was not found.");
	}

	// add new user to database
	@Override
	public boolean create(String username, String password, String role, String firstName, String lastName, String email) {
		boolean userCreated = false;

		// create user
		String insertUser = "insert into users (username,password_hash,role, first_name, last_name, email) values(?,?,?,?,?,?)";
		String password_hash = new BCryptPasswordEncoder().encode(password);
		String ssRole = "ROLE_" + role.toUpperCase();

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		String id_column = "id";
		userCreated = jdbcTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(insertUser, new String[] { id_column });
			ps.setString(1, username);
			ps.setString(2, password_hash);
			ps.setString(3, ssRole);
			ps.setString(4, firstName);
			ps.setString(5, lastName);
			ps.setString(6, email);
			return ps;
		}, keyHolder) == 1;
		int newUserId = (int) keyHolder.getKeys().get(id_column);
		return userCreated;
	}

	// deletes user by user id
	@Override
	public void deleteUserById(long id) {
		jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
	}
	
	@Override
	public String getUserEmailById(long id) {
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT email FROM users WHERE id = ?", id);
		return results.getString("email");
	}

	// maps data to object by using database column name in table users
	private User mapRowToUser(SqlRowSet rs) {
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setUsername(rs.getString("username"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password_hash"));
		user.setAuthorities(rs.getString("role"));
		user.setActivated(true);
		return user;
	}



}
