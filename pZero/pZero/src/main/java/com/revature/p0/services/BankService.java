package com.revature.p0.services;

import com.revature.p0.daos.BankAccountDao;
import com.revature.p0.exceptions.InvalidRequestException;
import com.revature.p0.exceptions.ResourcePersistenceException;
import com.revature.p0.models.BankAccount;
import com.revature.p0.util.collections.List;

public class BankService {
	private final BankAccountDao bAccountDao;
	private final UserService userService;
	
	public BankService(BankAccountDao bAccountDao, UserService userService) {
		this.bAccountDao = bAccountDao;
		this.userService = userService;
	}
	
	public void createBankAccount(BankAccount newBAccount) {
		if(!isBankAccountValid(newBAccount)) {
			throw new InvalidRequestException("The information provided was invalid");
		}
		
		newBAccount.setOwner(userService.getSessionUser());
		BankAccount createdAccount = bAccountDao.create(newBAccount);
		
		if(createdAccount == null) {
			throw new ResourcePersistenceException("The account could not be persisted");
		}
		
	}
	
	public boolean updateBalance(double updatedBalance) {
		return bAccountDao.update(updatedBalance);
	}

	
	private boolean isBankAccountValid(BankAccount newBAccount) {
		if(newBAccount == null) return false;
		return (newBAccount.getBankBalance()!=null && newBAccount.getBankBalance() > 0);
	}
	public List<BankAccount> findMyBankAccount(){
		return bAccountDao.findByOwnerId(userService.getSessionUser().getUserId());
	}
	
	public List<BankAccount> findAllBankAccounts(){
		return bAccountDao.findAll();
	}
}
