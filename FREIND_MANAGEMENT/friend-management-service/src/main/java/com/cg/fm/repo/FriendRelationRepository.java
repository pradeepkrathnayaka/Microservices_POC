package com.cg.fm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.fm.domain.FriendRelation;

@Repository
public interface FriendRelationRepository extends CrudRepository<FriendRelation, FriendRelation.FriendRPk> {

	@Query(value = "SELECT EMAIL FROM USER WHERE ID IN ( SELECT r.USER2ID  FROM USER u INNER JOIN FRIEND_RELATION r ON u.ID = r.USER1ID WHERE u.EMAIL=?1) ", nativeQuery=true)
	public List<String> findAllFriendsByEmail(@Param("email") String email);
	
	@Query(value = "SELECT * FROM FRIEND_RELATION WHERE USER1ID =?1", nativeQuery=true)
	public List<FriendRelation> findByFriendRPkUser1Id(final Long id);    

}
