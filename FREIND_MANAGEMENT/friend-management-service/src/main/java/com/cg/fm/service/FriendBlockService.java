package com.cg.fm.service;

import java.util.List;

import com.cg.fm.domain.FriendBlock;

public interface FriendBlockService {
	
	public FriendBlock block(String emailRequestor, String emailTarget);

	public boolean isBlocked(String emailRequestor, String emailTarget);

//	public List<String> getAllEmailsWhichAreBlockingTheEmailTarget(String emailRequestor);
//
	public List<String> fetchBlockers(String userOneEmail);

}
