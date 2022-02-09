package com.revature.p0.services;


import com.revature.p0.daos.UserDao;
import com.revature.p0.exceptions.AuthenticationException;
import com.revature.p0.exceptions.InvalidRequestException;
import com.revature.p0.exceptions.ResourcePersistenceException;
import com.revature.p0.models.Users;
import com.revature.p0.util.collections.List;

public class UserService {

	private final UserDao userDao;
	private Users sessionUser;
	
	public UserService (UserDao userDao) {
		this.userDao = userDao;
		this.sessionUser = null;
	}
	
	public Users getSessionUser() {
		return sessionUser;
	}
	
	public Users registerNewUser(Users newUser) {
		if(!isUserValid(newUser)) {
			throw new InvalidRequestException("Invalid user data");
		}
		
		boolean usernameAvailable = userDao.findByUsername(newUser.getUsername()) ==null;
		boolean emailAvailable = userDao.findByEmail(newUser.getEmail()) == null;
		
		if(!usernameAvailable || !emailAvailable) {
			if(!usernameAvailable && emailAvailable) {
				throw new ResourcePersistenceException("The provided username was already taken");
				
			}else if(usernameAvailable) {
				throw new ResourcePersistenceException("The provided eamil was already taken");
				
			}else {
				throw new ResourcePersistenceException("The provided username and email were already taken");
			}
		}
		
		Users persistedUser = userDao.create(newUser);
		
		if(persistedUser == null) {
			throw new ResourcePersistenceException("The user could not be persisted");
		}
		
		return persistedUser;
	}	
	
	public List<Users> getAllUsers(){
		return userDao.findAll();
	}
	
	public void authenticateUser(String username, String password) {
		if(username == null|| username.trim().equals("") || password == null || password.trim().equals("")) {
			throw new InvalidRequestException("Either the username  or password is  an invalid entry. Please try logging in again.");
		}
		
		Users authenticatedUser = userDao.findbyUsernameAndPassword(username, password);
		
		if(authenticatedUser == null) {
			throw new AuthenticationException("Unauthenticated user, information provided was not found.");
		}
		sessionUser = authenticatedUser;
	}
	
	public boolean isUserValid(Users newUser) {
		if(newUser == null) return false;
		if(newUser.getFirstName() == null || newUser.getFirstName().trim().equals("")) return false;
		if(newUser.getLastName() == null || newUser.getLastName().trim().equals("")) return false;
		if(newUser.getEmail() == null || newUser.getEmail().trim().equals("")) return false;
		if(newUser.getUsername() == null || newUser.getUsername().trim().equals("")) return false;
		
		return newUser.getPass() != null || !newUser.getPass().trim().equals("");
		
	}
	
	
	public void logout() {
		sessionUser = null;
	}
	
	public boolean isSessionActive() {
		return sessionUser != null;
	}
}
