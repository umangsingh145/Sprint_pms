package com.pms.in.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pms.in.controller.PensionController;
import com.pms.in.entities.PensionDetails;
import com.pms.in.service.PensionService;

@RestController
public class PensionController {
	
	private static final Logger LOG = LoggerFactory.getLogger(PensionController.class);

	@Autowired
	private PensionService pensionService;

	@PostMapping(path= "/addpension")
	public PensionDetails addPension(@RequestBody PensionDetails pensionDetails){
		LOG.info("Adding pension Details");
		
		return pensionService.addPensionDetails(pensionDetails);
		
	}
	
	@PutMapping("/updatepension")
	public PensionDetails updatepension(@RequestBody  PensionDetails pensionDetails ){
		LOG.info("update pension");
		return pensionService.updatePensionDetails(pensionDetails);
				
	}
	
	@GetMapping("/getpensiondetailsbyid/{pensionid}")
	public PensionDetails viewPension(@PathVariable (name = "pensionid") int pensionid){
		LOG.info("get pension details");
		return pensionService.viewPensionDetails(pensionid);
		
	}
	
	@DeleteMapping("/deletepensionbyid/{pensionid}")
	public PensionDetails deletePension(@PathVariable int pensionid){
		LOG.info("Delete pension");
		return pensionService.deletePensionDetails(pensionid);
		
	}
}
