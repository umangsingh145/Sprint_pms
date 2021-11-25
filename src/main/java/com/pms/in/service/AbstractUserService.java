package com.pms.in.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.in.exception.AbstractUserAlreadyExistsException;
import com.pms.in.exception.AbstractUserNotFoundException;
import com.pms.in.entities.AbstractUser;
import com.pms.in.repository.AbstractUserRepository;

@Service
public class AbstractUserService {

	public boolean isLoggedIn;

	private AbstractUser tempUser;

	private static final Logger LOG = LoggerFactory.getLogger(AbstractUserService.class);

	@Autowired
	AbstractUserRepository abstractUserRepository;

	public AbstractUser register(AbstractUser abstractUser) {
		LOG.info("register");
		if (abstractUserRepository.findByUserName(abstractUser.getUserName()).getUserName()
				.equalsIgnoreCase(abstractUser.getUserName()))
			throw new AbstractUserAlreadyExistsException();
		return abstractUserRepository.save(abstractUser);
	}

	public AbstractUser login(AbstractUser abstractUser) {
		LOG.info("login");
		tempUser = abstractUserRepository.findByUserName(abstractUser.getUserName());
		if (tempUser.getUserName().equalsIgnoreCase(abstractUser.getUserName())) {
			isLoggedIn = true;
			return tempUser;
		}
		throw new AbstractUserNotFoundException();
	}

	public String logout(String userName) {
		LOG.info("logout");
		if (isLoggedIn) {
			isLoggedIn = false;
			return "User logged out successfully.";
		}
		throw new AbstractUserNotFoundException();
	}
}