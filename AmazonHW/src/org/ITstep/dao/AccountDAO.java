package org.ITstep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;

//import org.ITstep.DAO.DBConnection;
import org.ITstep.model.AccRandom;
import org.ITstep.model.Account;
//import org.ITstep.model.Account;

public class AccountDAO {

	public void save(Account account) {

		Connection connection = null;
		PreparedStatement statement = null;

		String sql = "INSERT INTO accounts(login, email, password) VALUES(?,?,?)";

		try {
			connection = ConnectionToDB.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, account.getName());
			statement.setString(1, account.getEmail());
			statement.setString(2, account.getPassword());
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
	
//	public Account get(String login) {
//		Account account = new Account();
//		String sql = "SELECT * FROM accounts WHERE login=?";
//
//		Connection connection = null;
//		PreparedStatement statement = null;
//		ResultSet resultSet = null;
//
//		try {
//			connection = ConnectionToDB.getConnection();
//			statement = connection.prepareStatement(sql);
//			statement.setString(1, login);
//			resultSet = statement.executeQuery();
//			while (resultSet.next()) {
//				account.setEmail(resultSet.getString("first_name"));
////				account.setSecondName(resultSet.getString(3));
////				account.setEmail(resultSet.getString(4));
////				account.setPassword(resultSet.getString(5));
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				statement.close();
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return account;
//	}
//	
//	
//	public void update(Account accUpdated) {
//		Connection connection = null;
//		PreparedStatement statement = null;
//
//		String sql = "UPDATE Accounts SET sign_in=? WHERE login=?";
//
//		try {
//			connection = ConnectionToDB.getConnection();
//			statement = connection.prepareStatement(sql);
//			statement.setString(1, accUpdated.getEmail());
////			statement.setString(2, accUpdated.getPassword());
////			statement.setString(3, "+");
//			statement.setString(4, "+");
//			//statement.setString(5, "-");
//			statement.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				statement.close();
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}

}
