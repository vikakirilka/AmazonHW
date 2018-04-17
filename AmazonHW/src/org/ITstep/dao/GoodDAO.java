package org.ITstep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.ITstep.model.Good;

public class GoodDAO {

	
	 public ArrayList getAsin() {
		  
		  ArrayList<String> asins = new ArrayList();
		  
		  Connection con = null;
		  PreparedStatement statement = null;
		  String sql = "SELECT asin FROM goods";
		  ResultSet resultSet = null;
		  try {
		   con = ConnectionToDB.getConnection();
		   statement = con.prepareStatement(sql);
		   resultSet = statement.executeQuery();
		   while(resultSet.next()) {
		    asins.add(resultSet.getString("asin"));
		   }
		  } catch (SQLException e) {
		   e.printStackTrace();
		  }finally{
		   try {
			   statement.close();
		    con.close();
		   } catch (SQLException e) {
		    e.printStackTrace();
		   }
		  }
		  
		  return asins;
		 }
}
