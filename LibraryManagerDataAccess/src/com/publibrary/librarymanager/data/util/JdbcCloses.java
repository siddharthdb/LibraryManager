package com.publibrary.librarymanager.data.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcCloses {
	public static void closeIgnoreError(ResultSet rs) {
		try { if (rs != null && !rs.isClosed()) rs.close(); } catch (SQLException e) { }
	}
	
	public static void closeIgnoreError(Connection conn) {
		try { if (conn != null && !conn.isClosed()) conn.close(); } catch (SQLException e) { }
	}
	
	public static void closeIgnoreError(PreparedStatement stmt) {
		try { if (stmt != null && !stmt.isClosed()) stmt.close(); } catch (SQLException e) { }
	}
}
