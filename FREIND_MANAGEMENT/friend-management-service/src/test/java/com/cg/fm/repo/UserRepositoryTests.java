package com.cg.fm.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.fm.domain.FriendRelation;
import com.cg.fm.domain.User;

@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepo;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	@Test
    public void whenFindByName_thenReturnEmployee() {
        // given
		User u1 = new User("pradeep@gmail.com", "pradeep");
        entityManager.persist(u1);
        
        User u2 = new User("pradeep1@gmail.com", "pradeep1");
        entityManager.persist(u2);
        entityManager.flush();

        // when
        Iterable<User> listAll = userRepo.findAll();
        listAll.forEach(System.out::println);
        
        // when
        Optional<User> user = userRepo.findById(1L);
        System.out.println(user);
        
        Optional<User> user1 = userRepo.findOneByEmail("pradeep@gmail.com");
        System.out.println(user1.get());
        
        // then
        //assertEquals(list.size(), 1);
    }

}
