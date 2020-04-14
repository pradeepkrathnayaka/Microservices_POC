package com.cg.fm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fm.domain.User;
import com.cg.fm.exception.EmailNotRegisteredApiException;
import com.cg.fm.repo.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

    @Override
	public User findOneByEmail(String email) {
    	log.info("Find user by email {}", email);
    	return userRepo.findOneByEmail(email)
    			.orElseThrow(() -> new EmailNotRegisteredApiException(email));
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepo.existsByEmail(email);
	}

}
