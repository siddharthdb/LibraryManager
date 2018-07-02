package com.publibrary.librarymanager.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.publibrary.librarymanager.data.ListItemDao;
import com.publibrary.librarymanager.data.util.DbConnector;
import com.publibrary.librarymanager.data.util.JdbcCloses;
import com.publibrary.librarymanager.model.ListItem;

public class ListItemDaoImpl implements ListItemDao {

	@Override
	public List<ListItem> getListItemByUserId(int userId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ListItem> listItems = new ArrayList<ListItem>();
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT ID, VALUE "
					+ " FROM LIST_ITEM "
							+ "WHERE USER_ID = ? ");
			stmt.setInt(1, userId);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				ListItem listItem = new ListItem();
				listItem.setId(rs.getInt("ID"));
				listItem.setValue(rs.getString("VALUE"));
				
				listItems.add(listItem);
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
		
		return listItems;
	}

	@Override
	public ListItem getListItemById(int listItemId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ListItem listItem = null;
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT USER_ID, VALUE "
					+ " FROM LIST_ITEM "
							+ "WHERE ID = ? ");
			stmt.setInt(1, listItemId);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				listItem = new ListItem();
				listItem.setId(listItemId);
				listItem.setId(rs.getInt("USER_ID"));
				listItem.setValue(rs.getString("VALUE"));
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
		
		return listItem;
	}

	@Override
	public ListItem saveListItem(ListItem listItem) {
		if (listItem.getId() == 0) {
			insertListItem(listItem);
		} else {
			updateListItem(listItem);
		}
		return listItem;
	}
	
	private void updateListItem(ListItem listItem) {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					" UPDATE LIST_ITEM SET VALUE = ? WHERE ID = ?");
		
			stmt.setString(1, listItem.getValue());			
			stmt.setInt(2, listItem.getId());
			stmt.executeUpdate();
			
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
	}

	private void insertListItem(ListItem listItem) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"INSERT INTO LIST_ITEM(USER_ID, VALUE) VALUES (?, ?)");
			stmt.setInt(1, listItem.getUserId());
			stmt.setString(2, listItem.getValue());			
			stmt.executeUpdate();
			
			stmt = connection.prepareStatement("SELECT @@IDENTITY AS 'Identity'");  
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				listItem.setId(rs.getInt("Identity"));
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(rs);
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
	}
	
	

	@Override
	public void deleteListItem(int listItemId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					" DELETE FROM LIST_ITEM WHERE ID = ?");
			stmt.setInt(1, listItemId);
			
			stmt.executeUpdate();
			
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {			
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
	}

}
