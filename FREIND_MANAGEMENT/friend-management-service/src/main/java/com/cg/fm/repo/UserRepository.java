package com.cg.fm.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.fm.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	public Optional<User> findOneByEmail(String email);

	public Optional<User> findOneByUsername(String username);

	@Query(value = "SELECT COUNT(u.EMAIL)>0 FROM USER u WHERE u.EMAIL=?1", nativeQuery=true)
	public boolean existsByEmail(@Param("email") String email);
}
