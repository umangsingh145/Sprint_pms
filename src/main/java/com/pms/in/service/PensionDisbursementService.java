package com.pms.in.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.in.entities.BankDetails;
import com.pms.in.entities.PensionDetails;
import com.pms.in.entities.PensionerDetails;
import com.pms.in.repository.BankRepository;
import com.pms.in.repository.PensionRepository;
import com.pms.in.repository.PensionerRepository;

@Service
public class PensionDisbursementService {

	private static Logger LOG = LoggerFactory.getLogger(PensionDisbursementService.class);

	@Autowired
	private PensionService pensionService;

	@Autowired
	private BankRepository bankRepository;

	@Autowired
	private PensionerRepository pensionerRepository;
	
	@Autowired
	private PensionRepository pensionRepository;

	private static final Map<String, Double> banks = createMap();

	private static Map<String, Double> createMap() {
		LOG.info("START");

		Map<String, Double> tempBanks = new HashMap<>();
		tempBanks.put("SBI", 500.0);
		tempBanks.put("IOB", 500.0);
		tempBanks.put("BYTECARD", 550.0);
		tempBanks.put("PANNIER", 550.0);
		LOG.info("END");

		return tempBanks;
	}

	public double getBankServiceCharge(String bankName) {
		if (banks.containsKey(bankName.toUpperCase()))
			return banks.get(bankName.toUpperCase());
		else
			return 0;
	}

	public PensionDetails calculatePension(int aadhar) {
		LOG.info("START");
		PensionerDetails pensionerDetails=pensionerRepository.findByAadhar(aadhar);
		PensionDetails pensionDetails=pensionRepository.getById(pensionerDetails.getPensioner_id());
		BankDetails bankDetails=bankRepository.getById(pensionerDetails.getAcc_No());
		int salary = pensionerDetails.getSalary();
		String typeOfPension = pensionerDetails.getPensionType();
		double pension = -1;
		if (typeOfPension.equalsIgnoreCase("Self")) {
			pension = salary/2;
		} else {
			pension = salary/4;

		}
		pensionDetails.setAmount(pension);
		pensionDetails.setCharges(getBankServiceCharge(bankDetails.getBankName()));
		pensionService.updatePensionDetails(pensionDetails);
		LOG.info("END");

		return pensionDetails;
	}

	public boolean processPension(PensionDetails pensionDetails) {
		LOG.info("START");
		PensionerDetails pensionerDetails=pensionerRepository.getById(pensionDetails.getPensioner_id());
		BankDetails bankDetails=bankRepository.getById(pensionerDetails.getAcc_No());

		double bankServiceCharge = banks.get(bankDetails.getBankName().toUpperCase());
		if ((pensionDetails.getAmount().equals(calculatePension(pensionerDetails.getAadhar()).getAmount()))
				&& (pensionDetails.getCharges().equals(bankServiceCharge))) {
			pensionDetails.setCharges(bankServiceCharge);
			pensionService.updatePensionDetails(pensionDetails);
			return true;

		}
		LOG.info("END");

		return false;
	}

}
