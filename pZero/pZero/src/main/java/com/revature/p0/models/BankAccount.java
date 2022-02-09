package com.revature.p0.models;

import java.util.Objects;

public class BankAccount {
	private String bankId;
	private Double bankBalance;
	private Users owner;
	
	public BankAccount() {
		super();
	}

	public BankAccount(String bankId, Double bankBalance, Users owner) {
		super();
		this.bankId = bankId;
		this.bankBalance = bankBalance;
		this.owner = owner;
	}

	public BankAccount(Double bankBalance, Users owner) {
		super();
		this.bankBalance = bankBalance;
		this.owner = owner;
	}

	public BankAccount(Double bankBalance) {
		super();
		this.bankBalance = bankBalance;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public Double getBankBalance() {
		return bankBalance;
	}

	public void setBankBalance(Double bankBalance) {
		this.bankBalance = bankBalance;
	}

	public Users getOwner() {
		return owner;
	}

	public void setOwner(Users owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "BankAccount [bankId=" + bankId + ", bankBalance=" + bankBalance + ", owner=" + owner + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(bankBalance, bankId, owner);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		return Objects.equals(bankBalance, other.bankBalance) && Objects.equals(bankId, other.bankId)
				&& Objects.equals(owner, other.owner);
	}
	
	
	
}
