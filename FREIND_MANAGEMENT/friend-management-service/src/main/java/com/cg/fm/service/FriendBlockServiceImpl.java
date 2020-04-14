package com.cg.fm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.fm.domain.FriendBlock;
import com.cg.fm.domain.User;
import com.cg.fm.exception.BlockeFriendException;
import com.cg.fm.repo.FriendBlockRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FriendBlockServiceImpl implements FriendBlockService {
	
	private FriendBlockRepository blockingRepo;

	private UserService userService;

	public FriendBlockRepository getBlockingRepo() {
		return blockingRepo;
	}

	@Autowired
	public void setBlockingRepo(FriendBlockRepository blockingRepo) {
		this.blockingRepo = blockingRepo;
	}

	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.fm.service.BlockingService#block(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public FriendBlock block(String emailRequestor, String emailTarget) {
		log.debug("[block]-emailTarget={}, emailRequestor={}", emailTarget, emailRequestor);
		try {
			User u1 = userService.findOneByEmail(emailRequestor);
			User u2 = userService.findOneByEmail(emailTarget);
			FriendBlock blocking = new FriendBlock(u1.getEmail(), u2.getEmail());
			if (blockingRepo.existsById(blocking.getId())) {
				throw new BlockeFriendException("907");
			}
			return blockingRepo.save(blocking);
		} catch (Exception e) {
			log.error("" + e.getMessage());
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.fm.service.BlockingService#isBlocked(java.lang.String,
	 * java.lang.String)
	 */
	@Transactional(readOnly = true)
	@Override
	public boolean isBlocked(String emailRequestor, String emailTarget) {
		User u1 = userService.findOneByEmail(emailRequestor);
		User u2 = userService.findOneByEmail(emailTarget);
		FriendBlock blocking = new FriendBlock(u1.getEmail(), u2.getEmail());
		boolean blocked = blockingRepo.existsById(blocking.getId());
		log.debug("[isBlocked]-emailTarget={}, emailRequestor={}, blocked={}", emailTarget, emailRequestor, blocked);
		return blocked;
	}

	@Transactional(readOnly = true)
	@Override
	public List<String> fetchBlockers(String email) {
		try {
			log.debug("[getAllEmailsWhichAreBlockingTheEmailTarget]-emailTarget={}", email);
			return blockingRepo.findAllBlockedEmailByTargetEmail(email);
		} catch (Exception e) {
			throw e;
		}
	}

}
