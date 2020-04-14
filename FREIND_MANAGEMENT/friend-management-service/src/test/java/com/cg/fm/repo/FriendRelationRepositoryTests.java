package com.cg.fm.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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


@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FriendRelationRepositoryTests {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private FriendRelationRepository friendRepo;

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
		FriendRelation fr1 = new FriendRelation(1L, 2L, "Pending");
        entityManager.persist(fr1);
        entityManager.flush();

        // when
        Iterable<FriendRelation> listAll = friendRepo.findAll();
        listAll.forEach(System.out::println);
        
        // when
        List<FriendRelation> list = friendRepo.findByFriendRPkUser1Id(1L);
        list.forEach(System.out::println);
        
        // then
        assertEquals(list.size(), 1);
    }

}
