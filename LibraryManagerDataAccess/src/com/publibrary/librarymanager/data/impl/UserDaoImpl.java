package com.publibrary.librarymanager.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.publibrary.librarymanager.data.UserDao;
import com.publibrary.librarymanager.data.util.DbConnector;
import com.publibrary.librarymanager.data.util.JdbcCloses;
import com.publibrary.librarymanager.model.User;

public class UserDaoImpl implements UserDao {

	@Override
	public User getUser(String username) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user = null;
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT ID, USERNAME, DISPLAY_NAME "
					+ " FROM APP_USER "
							+ "WHERE LOWER(USERNAME) = ? ");
			stmt.setString(1, username.toLowerCase());
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("ID"));
				user.setUsername(rs.getString("USERNAME"));
				user.setDisplayName(rs.getString("DISPLAY_NAME"));
			}
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(rs);
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
		return user;
		
	}

} 
