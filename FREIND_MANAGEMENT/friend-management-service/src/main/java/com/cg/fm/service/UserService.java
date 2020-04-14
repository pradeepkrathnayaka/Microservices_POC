package com.cg.fm.service;

import com.cg.fm.domain.User;

public interface UserService {
	public User findOneByEmail(String email);
	public boolean existsByEmail(String email);
}
