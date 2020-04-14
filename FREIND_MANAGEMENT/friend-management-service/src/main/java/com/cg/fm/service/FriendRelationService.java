package com.cg.fm.service;

import java.util.List;

import com.cg.fm.domain.FriendRelation;
import com.cg.fm.dto.FriendEmailListDto;
import com.cg.fm.dto.NotifyUpdateDto;
import com.cg.fm.dto.RequestorTargetEmailDto;
import com.cg.fm.exception.InvalidEmailException;
import com.cg.fm.exception.InvalidUserException;

public interface FriendRelationService {
	
	public FriendRelation createFriendConnection(FriendEmailListDto friendEmailListDto) 
			throws InvalidUserException, InvalidEmailException;

	public List<String> retrieveFriendsEmailsByRequestorEmail(String email);

	public List<String> retrieveCommonFriends(FriendEmailListDto friendEmailListDto);

	public void subcribeFriend(RequestorTargetEmailDto subscriptionDto);

	public void blockFriend(RequestorTargetEmailDto subscriptionDto);

	public List<String> notifyEmailList(NotifyUpdateDto notifyUpdateDto);
}
