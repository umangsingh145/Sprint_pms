package com.pms.in.repository;

import com.pms.in.entities.PensionDetails;

public interface IPensionRepository
{
	public PensionDetails addPensionDetails(PensionDetails pensionDetails);
	public PensionDetails updatePensionDetails(PensionDetails pensionDetails);
	public PensionDetails viewPensionDetails(PensionDetails pensionDetails);
	public void deletePensionDetails(int pensionerId);
}