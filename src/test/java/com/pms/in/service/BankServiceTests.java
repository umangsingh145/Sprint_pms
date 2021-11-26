package com.pms.in.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.pms.in.entities.BankDetails;
import com.pms.in.repository.BankRepository;

@SpringBootTest
public class BankServiceTests {

	@Autowired
	private BankService bankservice;
	@MockBean
	private BankRepository bankRepository;

	@Test
	public void addBank() {

		BankDetails bank = new BankDetails((long) 100, "SBI", "Pune", "SBI1800", "Urvesh");
		when(bankRepository.save(bank)).thenReturn(bank);
		assertEquals(bank, bankservice.addBank(bank));
	}

	@Test
	public void deleteBankDetails() {
		BankDetails bank = bankRepository.getById((long) 100);
		bankRepository.delete(bank);
		Optional<BankDetails> bankOpt = Optional.empty();
		if (bankOpt.isPresent()) {
			bank = bankOpt.get();
		}
		Assertions.assertThat(bank).isNull();
	}
	


}
