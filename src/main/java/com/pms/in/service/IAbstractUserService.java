package com.pms.in.service;

import com.pms.in.entities.AbstractUser;

public interface IAbstractUserService {

	public abstract AbstractUser login(AbstractUser abstractUser);
	public abstract String logout(String userName);
}