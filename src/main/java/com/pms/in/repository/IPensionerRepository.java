package com.pms.in.repository;

import com.pms.in.entities.Pensioner;

public interface IPensionerRepository
{
	public boolean validatePensioner();
	public Pensioner addPensioner(Pensioner pensioner);
	public void deletePensioner(int pensionerId);
	public Pensioner updatePensioner(Pensioner pensioner);
	public Pensioner viewPensioner(int pensionerId);
}
