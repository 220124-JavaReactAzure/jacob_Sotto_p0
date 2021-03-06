package com.revature.p0.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import com.revature.p0.models.BankAccount;
import com.revature.p0.util.collections.LinkedList;
import com.revature.p0.util.collections.List;
import com.revature.p0.util.datasource.ConnectionFactory;

public class BankAccountDao implements CrudDao<BankAccount> {

	public List<BankAccount> findByOwnerId(String id){
		List<BankAccount> bankList = new LinkedList<>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select user_id from banks"; // selects all bank accounts in the bank table (can later include specific bank accounts)
			Statement s = conn.createStatement();
			
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				BankAccount account = new BankAccount();
				account.setBankId(rs.getString("bank_id"));
				account.setBankBalance(rs.getDouble("bank_amount"));				
				
				bankList.add(account);
			}
			return bankList;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	// this method will update the balance of the bank account
public boolean update(double updatedBalance) {
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "update banks set bank_amount = ?"; // will update banks 

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, updatedBalance);
			ps.executeUpdate();

			return true;
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		
		return false;
	}

	// creates new bank accounts
	@Override
	public BankAccount create(BankAccount newBAccount) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			newBAccount.setBankId(UUID.randomUUID().toString()); // initializes a randomly generated string
			
			String sql = "insert into banks (bank_id, bank_amount, user_id) values (?, ?, ?)"; // sql statement that inserts itself data into the bank account table
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, newBAccount.getBankId()); //will set the bank id 
			ps.setDouble(2, newBAccount.getBankBalance()); //sets the balance
			ps.setString(3, newBAccount.getOwner().getUserId()); //finds the owner
			
			int rowsInserted = ps.executeUpdate();
			
			if(rowsInserted != 0) {
				return newBAccount;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// will look for all the bank accounts in bank table
	@Override
	public List<BankAccount>findAll(){
		List<BankAccount> bankList = new LinkedList<>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from banks";
			Statement s = conn.createStatement();
			
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				BankAccount account = new BankAccount();
				account.setBankId(rs.getString("bank_id"));
				account.setBankBalance(rs.getDouble("bank_amount"));				
				
				bankList.add(account);
			}
			return bankList;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BankAccount findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(BankAccount updatedObj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
