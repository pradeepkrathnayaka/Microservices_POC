package com.cg.fm.service;

import java.util.List;

import com.cg.fm.domain.FriendSubcribe;

public interface FriendSubcribeService {
	FriendSubcribe subscribe(String emailTarget, String emailRequestor);

	List<String> retrieveSubscribers(String email);
}
