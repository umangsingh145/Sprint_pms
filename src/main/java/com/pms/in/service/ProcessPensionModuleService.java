package com.pms.in.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.in.entities.PensionDetails;
import com.pms.in.repository.ProcessPensionModuleRepository;

@Service
public class ProcessPensionModuleService {
	
	private static final Logger LOG=LoggerFactory.getLogger(ProcessPensionModuleService.class);
	
	@Autowired
	private ProcessPensionModuleRepository processPensionModuleRepository;
	
	public PensionDetails validateDetails(String name, int aadhar, int pan, String type) {
		
		LOG.info("service");
		
		return null;	
		
	}
	
	

}
