package com.revature.p0.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import com.revature.p0.models.Users;
import com.revature.p0.util.collections.LinkedList;
import com.revature.p0.util.collections.List;
import com.revature.p0.util.datasource.ConnectionFactory;

//creteas the user dao which uses the interface crud, which accepts type User
public class UserDao implements CrudDao<Users>{
	
	// creates a method that accepts strings username and password in its parameters
	public Users findbyUsernameAndPassword(String username, String pass) {
	
		//try block to get a connection using connection factory to get a connection 
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			// a string that shares a postgres command where it selects everything from the users table with the same username and password
			String sql = "select * from users where username = ? and pass = ?";
			PreparedStatement ps = conn.prepareStatement(sql); //uses java's prepared statement class
			ps.setString(1, username); //set the first string as username
			ps.setString(2, pass); //set the second string input as password
			ResultSet rs = ps.executeQuery(); // reseult set is equal to the prepared statement's execution
			
			//while loop that looks through a result set until it is false
			while (rs.next()) {
				Users user = new Users(); //defines object user
				user.setUserId(rs.getString("user_id")); //sets the userid as the result id
				user.setFirstName("first_name"); // sets the firstname as the resulted first name
				user.setLastName("last_name"); //sets the last name as the resulted last name
				user.setEmail("email"); //sets the email as the resulted email
				user.setUsername("username");
				user.setPass("pass");
				
				return user;
			}
		}catch(SQLException e) { //catches sql exceptions
			e.printStackTrace();
		}
		
		return null; //if it doesn't return an object user, wwill return null
	}
	
	//will search through database by looking at email
	public Users findByEmail(String email) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from users where email = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Users user = new Users();
				user.setUserId(rs.getString("user_id"));
				user.setFirstName("first_name");
				user.setLastName("last_name");
				user.setEmail("email");
				user.setUsername("username");
				user.setPass("pass");
				
				return user;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//will search through database using username 
	public Users findByUsername(String username) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from users where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Users user = new Users();
				user.setUserId(rs.getString("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setPass(rs.getString("pass"));
				
				return user;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// creates user object to insert into the user table in the database
	@Override
	public Users create(Users newUser) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			newUser.setUserId(UUID.randomUUID().toString());
			
			String sql = "insert into users (user_id, first_name, last_name, email, username, pass) values (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, newUser.getUserId());
			ps.setString(2, newUser.getFirstName());
			ps.setString(3, newUser.getLastName());
			ps.setString(4, newUser.getEmail());
			ps.setString(5, newUser.getUsername());
			ps.setString(6, newUser.getPass());
			
			int rowsInserted = ps.executeUpdate();
			
			if(rowsInserted != 0) {
				return newUser;
			}
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Users>findAll(){ //creates list accepting user objects to find all users in the database
		List<Users> userList = new LinkedList<>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from users";
			Statement s = conn.createStatement();
			
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				Users user = new Users();
				user.setUserId(rs.getString("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setPass(rs.getString("pass"));
				
				userList.add(user);
			}
			return userList;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override 
	public Users findById(String id) {
		return null;
	}
	
	@Override
	public boolean update(Users updatedObj) {
		return false;
	}
	
	@Override
	public boolean delete(String id) {
		return false;
	}
	
}
