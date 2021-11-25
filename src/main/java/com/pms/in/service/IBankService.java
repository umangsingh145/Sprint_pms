package com.pms.in.service;

import com.pms.in.entities.BankDetails;

public interface IBankService
{
	public BankDetails addBank(BankDetails bankDetails);
	public void deleteBank(	Long accno);
}