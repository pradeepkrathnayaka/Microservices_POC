package com.cg.fm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.fm.domain.FriendBlock;

@Repository
public interface FriendBlockRepository extends CrudRepository<FriendBlock, FriendBlock.BlockPk> {
	
	@Query(value = "SELECT u.EMAIL FROM USER u INNER JOIN FRIEND_BLOCK b ON u.ID = b.REQUESTOR WHERE u.EMAIL=?1", nativeQuery=true)
	public List<String> findAllBlockedEmailByTargetEmail(@Param("email") String email);

}
