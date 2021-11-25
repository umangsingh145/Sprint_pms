package com.pms.in.service;

import com.pms.in.entities.AbstractUser;

public interface IAbstractUserService {

	public AbstractUser login(AbstractUser abstractUser);
	public String logout(String userName);
}