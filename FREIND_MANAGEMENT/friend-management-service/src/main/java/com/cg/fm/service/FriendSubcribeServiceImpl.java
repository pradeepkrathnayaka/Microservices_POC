package com.cg.fm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fm.domain.FriendSubcribe;
import com.cg.fm.domain.User;
import com.cg.fm.exception.SubscriptionRequestRepeatedApiException;
import com.cg.fm.repo.FriendSubcribeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FriendSubcribeServiceImpl implements FriendSubcribeService {

	private FriendSubcribeRepository subscriptionRepo;

	private UserService userService;
	
	public FriendSubcribeRepository getSubscriptionRepo() {
		return subscriptionRepo;
	}
	@Autowired
	public void setSubscriptionRepo(FriendSubcribeRepository subscriptionRepo) {
		this.subscriptionRepo = subscriptionRepo;
	}
	public UserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public FriendSubcribe subscribe(String emailRequestor, String emailTarget) {
		log.debug("[subscribe]-emailTarget={}, emailRequestor={}", emailTarget, emailRequestor);
//		try {
			User u1 = userService.findOneByEmail(emailRequestor);
			User u2 = userService.findOneByEmail(emailTarget);			
			FriendSubcribe sub = new FriendSubcribe(u1.getEmail(), u2.getEmail());			
			if (subscriptionRepo.existsById(sub.getId())) {
				throw new SubscriptionRequestRepeatedApiException();
			}
			return subscriptionRepo.save(sub);
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw e;
//		}
	}

	@Override
	public List<String> retrieveSubscribers(String email) {
        log.debug("[retrieveSubscribers]-email={}", email);
        return subscriptionRepo.findAllRequestorEmailsByEmailTarget(email);
	}

}
