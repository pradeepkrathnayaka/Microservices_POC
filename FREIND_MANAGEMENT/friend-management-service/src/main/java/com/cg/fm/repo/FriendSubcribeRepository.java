package com.cg.fm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.fm.domain.FriendSubcribe;

@Repository
public interface FriendSubcribeRepository extends CrudRepository<FriendSubcribe, FriendSubcribe.SubscribePk>{
	
	@Query(value = "SELECT s.TARGET FROM FRIEND_SUBSCRIBE s INNER JOIN USER u  ON u.EMAIL = s.REQUESTOR WHERE u.EMAIL = ?1", nativeQuery = true)
	public List<String> findAllRequestorEmailsByEmailTarget(String emailRequestor);

}
