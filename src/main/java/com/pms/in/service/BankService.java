package com.pms.in.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.in.exception.AdminDoesNotExistsException;
import com.pms.in.exception.BankAlreadyExistsException;
import com.pms.in.exception.BankDoesNotExistsException;
import com.pms.in.entities.BankDetails;
import com.pms.in.repository.BankRepository;

@Service
public class BankService implements IBankService {

	private static final Logger LOG = LoggerFactory.getLogger(BankService.class);

	@Autowired
	private BankRepository bankRepository;

	@Autowired
	private AdminService admin;

	@Autowired
	private AbstractUserService abst;

	boolean result = true;

	public BankDetails getBankDetails(Long accno) {
		LOG.info("ServicegetBankDetailsById");
		Optional<BankDetails> bankOpt = bankRepository.findById(accno);
		if (admin.getIsLoggedIn() || abst.getIsLoggedIn()) {
			if (bankOpt.isPresent()) {
				LOG.info("bank Details get successfully");
				return bankOpt.get();
			} else {
				LOG.info("Bank does not exists");
				throw new BankDoesNotExistsException("Bank does not exists");
			}
		} else {
			LOG.info("User have to Login first");
			throw new AdminDoesNotExistsException();
		}
	}

	@Override
	public BankDetails addBank(BankDetails bankDetails) {
		LOG.info("ServiceaddBankDetails");
		if (admin.getIsLoggedIn() || abst.getIsLoggedIn()) {
			if (!bankRepository.existsById(bankDetails.getAccno())) {
				LOG.info("Bank Details added successfully");
				return bankRepository.save(bankDetails);
			} else {
				LOG.info("This bank already exist in the database");
				throw new BankAlreadyExistsException("This bank already exists in the database");
			}
		} else {
			LOG.info("User have to Login first");
			throw new AdminDoesNotExistsException();
		}
	}

	public BankDetails updateBankDetails(BankDetails bankDetails) {
		LOG.info("ServiceupdateBankDeatils");
		if (admin.getIsLoggedIn() || abst.getIsLoggedIn()) {
			if (bankRepository.existsById(bankDetails.getAccno())) {
				LOG.info("Bank Details update successfully");
				return bankRepository.save(bankDetails);
			} else {
				LOG.info("This bank already exist in the database");
				throw new BankDoesNotExistsException("This bank already exists in the database");
			}
		} else {
			LOG.info("User have to Login first");
			throw new AdminDoesNotExistsException();
		}
	}

	public void deleteBank(Long accno) {
		LOG.info("ServicedeleteBankDeatils");
		if (admin.getIsLoggedIn() || abst.getIsLoggedIn()) {
			if (bankRepository.findById(accno) != null) {
				LOG.info("Bank Details added successfully");
				bankRepository.deleteById(accno);
			} else {
				LOG.info("This bank already exist in the database");
				throw new BankDoesNotExistsException("This bank already exists in the database");
			}
		} else {
			LOG.info("User have to Login first");
			throw new AdminDoesNotExistsException();
		}
	}

}