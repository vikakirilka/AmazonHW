package org.ITstep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.ITstep.model.Account;
import org.ITstep.model.InterimTable;

public class InterimTableDAO {

	public void save(InterimTable interim) {

		Connection connection = null;
		PreparedStatement statement = null;

		String sql = "INSERT INTO interim_table(name, ASIN, add_to_cart) VALUES(?,?,?)";

		try {
			connection = ConnectionToDB.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, interim.getName());
			statement.setString(2, interim.getAsin());
			statement.setString(3, interim.getAddToCart());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
}
