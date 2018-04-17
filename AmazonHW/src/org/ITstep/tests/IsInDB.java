package org.ITstep.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.ITstep.dao.ConnectionToDB;
import org.ITstep.model.Account;
import org.junit.jupiter.api.Test;

class IsInDB {

	@Test
	public Account get(String login, String password) {
		Account account = new Account();
		String sql = "SELECT * FROM accounts WHERE login=? AND password=?";

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionToDB.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, account.getName());
			statement.setString(2, account.getPassword());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				account.setFirstName(resultSet.getString("first_name"));
				account.setEmail(resultSet.getString(4));
				account.setPassword(resultSet.getString(5));
			}

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

		return account;
	}

}
