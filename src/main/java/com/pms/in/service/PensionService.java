package com.pms.in.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.in.service.PensionService;
import com.pms.in.repository.PensionRepository;
import com.pms.in.exception.AdminDoesNotExistsException;
import com.pms.in.exception.PensionDetailsNotFoundException;
import com.pms.in.entities.PensionDetails;

@Service
public class PensionService implements IPensionService {

	private static final Logger LOG = LoggerFactory.getLogger(IPensionService.class);

	@Autowired
	private AdminService admin;

	@Autowired
	private AbstractUserService abst;

	@Autowired
	private PensionRepository pensionRepository;

	@Override
	public PensionDetails addPensionDetails(PensionDetails pensionDetails) {

		LOG.info("ServiceAddpension");
		if (admin.getIsLoggedIn() || abst.getIsLoggedIn()) {
			if (pensionRepository.findById(pensionDetails.getPensioner_id()) != null)
				return pensionRepository.save(pensionDetails);
			System.out.println(pensionDetails.getPensioner_id() + " already exists.");
			return null;
		} else {
			LOG.info("User have to Login first");
			throw new AdminDoesNotExistsException();
		}
	}

	@Override
	public PensionDetails updatePensionDetails(PensionDetails pensionDetails) {
		LOG.info("ServiceupdatePension");
		if (admin.getIsLoggedIn() || abst.getIsLoggedIn()) {
			if (pensionRepository.findById(pensionDetails.getPensioner_id()) != null)

				return pensionRepository.save(pensionDetails);
			LOG.info(pensionDetails.getPensioner_id() + " does not exist.");
			return null;
		} else {
			LOG.info("User have to Login first");
			throw new AdminDoesNotExistsException();
		}
	}

	@Override
	public PensionDetails viewPensionDetails(int pensionerId) {
		LOG.info("ServiceViewpensiondetails");
		Optional<PensionDetails> empOpt = pensionRepository.findById(pensionerId);
		if (admin.getIsLoggedIn() || abst.getIsLoggedIn()) {
			if (empOpt.isPresent()) {
				LOG.info("pension deatils present");
				return empOpt.get();
			} else {
				LOG.info("pension details is NOT available.");
				throw new PensionDetailsNotFoundException(pensionerId + " this pension details  not found.");
			}
		} else {
			LOG.info("User have to Login first");
			throw new AdminDoesNotExistsException();
		}
	}

	public PensionDetails deletePensionDetails(int pensionerId) {
		LOG.info("servicedeletepensiondetails");
		Optional<PensionDetails> empOpt = pensionRepository.findById(pensionerId);
		if (admin.getIsLoggedIn() || abst.getIsLoggedIn()) {
			if (empOpt.isPresent()) {
				pensionRepository.deleteById(pensionerId);
				return empOpt.get();
			} else {
				throw new PensionDetailsNotFoundException(pensionerId + " this pension details does not exist.");
			}
		} else {
			LOG.info("User have to Login first");
			throw new AdminDoesNotExistsException();
		}
	}

}