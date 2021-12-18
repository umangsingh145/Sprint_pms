package com.pms.in.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.in.exception.AbstractUserAlreadyExistsException;
import com.pms.in.exception.AbstractUserNotFoundException;
import com.pms.in.exception.IncorrectLoginCredentialsException;
import com.pms.in.entities.AbstractUser;
import com.pms.in.repository.AbstractUserRepository;

@Service
public class AbstractUserService implements IAbstractUserService {

	private boolean isLoggedIn;

	private AbstractUser tempUser;

	private AbstractUser tempPassword;

	public boolean getIsLoggedIn() {
		return isLoggedIn;
	}

	private static final Logger LOG = LoggerFactory.getLogger(AbstractUserService.class);

	@Autowired
	AbstractUserRepository abstractUserRepository;

	public AbstractUser register(AbstractUser abstractUser) {
		LOG.info("Serviceregister");
		if (abstractUserRepository.findByUserName(abstractUser.getUserName()) != null) {
			LOG.error("User already exists");
			throw new AbstractUserAlreadyExistsException();
		} else {
			LOG.info("Register successfully");
			return abstractUserRepository.save(abstractUser);
		}
	}

	@Override
	public AbstractUser login(AbstractUser abstractUser) {
		LOG.info("login");
		try {
			this.tempUser = abstractUserRepository.findByUserName(abstractUser.getUserName());
			this.tempPassword = abstractUserRepository.findByPassword(tempUser.getPassword());

			if (tempUser.getUserName().equalsIgnoreCase(abstractUser.getUserName()) && tempPassword.getPassword().equals(abstractUser.getPassword())) {

				LOG.info("Logged in successfully.");
				isLoggedIn = true;
				return tempUser;
			} else {
				LOG.error("User not found");
				throw new IncorrectLoginCredentialsException();
			}

		} catch (Exception e) {
			LOG.error("User not found");
			throw new IncorrectLoginCredentialsException();
		}

	}

	@Override
	public String logout(String userName) {
		LOG.info("Servicelogout");
		if (isLoggedIn) {
			isLoggedIn = false;
			return "User logged out successfully.";
		} else {
			LOG.error("User not found");
			throw new AbstractUserNotFoundException();
		}
	}
}