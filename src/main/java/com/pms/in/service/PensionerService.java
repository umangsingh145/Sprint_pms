package com.pms.in.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pms.in.entities.PensionerDetails;
import com.pms.in.exception.PensionerAlreadyExistsException;
import com.pms.in.exception.PensionerDetailsNotFoundException;
import com.pms.in.repository.PensionerRepository;

@Service
public class PensionerService {
	public static final Logger LOG = LoggerFactory.getLogger(PensionerService.class);

	@Autowired
	private PensionerRepository pensionerRepository;

	public boolean validatePensioner(String name, int pan, int aadhar, String type) {
		LOG.info("START");
		PensionerDetails validatepensioner;

		validatepensioner = pensionerRepository.findByAadhar(aadhar);
		if (validatepensioner != null) {
			if (validatepensioner.getPan() == pan && validatepensioner.getPensionType().equalsIgnoreCase(type)) {
				LOG.info("MATCHED");
				return true;
			}
		} else {
			LOG.info("Invalid pensioner detail provided, please provide valid detail.");
			throw new PensionerDetailsNotFoundException(
					"Invalid pensioner detail provided, please provide valid detail.");

		}
		return false;

	}

	public PensionerDetails addPensionerDetails(PensionerDetails pensionerdetails) {
		LOG.info("Pensioner Details");
		if (!pensionerRepository.existsById(pensionerdetails.getPensioner_id()))
			return pensionerRepository.save(pensionerdetails);
		else {
			LOG.info(pensionerdetails.getPensioner_id() + " already exists.");
			throw new PensionerAlreadyExistsException(
					pensionerdetails.getPensioner_id() + " This Pensioner  ALready Exits.");
		}
	}

	// Update
	public PensionerDetails updatePensionerDetails(PensionerDetails pensionerdetails) {
		LOG.info("UpadtePensionerDetails");
		if (pensionerRepository.existsById(pensionerdetails.getPensioner_id()))
			return pensionerRepository.save(pensionerdetails);

		else {
			LOG.info("Employee is NOT available.");
			throw new PensionerDetailsNotFoundException(" This Pensioner Details is not found.");
		}
	}

//Delete

	public PensionerDetails deletePensionerDetails(int pensioner_id) {
		LOG.info("PensionerDetails");
		if (pensionerRepository.existsById(pensioner_id)) {
			pensionerRepository.deleteById(pensioner_id);
			return null;
		} else {
			LOG.info("Pensioner is NOT available.");
			throw new PensionerDetailsNotFoundException(" Pensioner Details is not found.");
		}
	}

	public List<PensionerDetails> getAllPensionersDetails() {
		System.out.println("Service getAllEmployees");
		return pensionerRepository.findAll();
	}
}