package com.pms.in.repository;

import com.pms.in.entities.BankDetails;

public interface IBankRepository
{
	public BankDetails addBank(BankDetails bankDetails);
	public void deleteBank(int accno);
}