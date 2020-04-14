package com.cg.fm.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.fm.dto.EmailRequestDto;
import com.cg.fm.dto.FriendEmailListDto;
import com.cg.fm.dto.FriendResponseDto;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class FriendRelationControllerTests extends AbstractTest{

	private final String URL = "/friends/";
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@BeforeAll
	public void setUpBeforeClass() throws Exception {
		super.setUp();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUpBeforeEach() throws Exception {
	}

	@AfterEach
	void tearDownAfterEach() throws Exception {
	}

	@Test
	@Order(1)
	public void test() {
		assertNotNull(mockMvc);
	}

	@Test
	@Order(2)
	public void addFriend() throws Exception {
	}

	@Test
	@Order(3)
	public void listFriends() throws Exception {
		String uri = "/friends/list";

		EmailRequestDto obj = new EmailRequestDto();
		obj.setEmail("pradeep@gmail.com");

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(APPLICATION_JSON_UTF8)
				.content(mapToJson(obj))
				.accept(APPLICATION_JSON_UTF8)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
		FriendResponseDto resp = mapFromJson(content, FriendResponseDto.class);
		assertTrue(resp.getFriends().size() > 0);		
	}

	@Test
	@Order(4)
	public void commonFriend() throws Exception {
		String uri = "/friends/common";
		FriendEmailListDto  obj = new FriendEmailListDto();
		//String[] a = new String[]{"pradeep@gmail.com", "pradeep@gmail.com"};
		obj.setFriends(Arrays.asList("", "rathnayaka@gmail.com"));

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(APPLICATION_JSON_UTF8)
				.content(mapToJson(obj))
				.accept(APPLICATION_JSON_UTF8)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
		
		FriendResponseDto resp = mapFromJson(content, FriendResponseDto.class);
		assertTrue(resp.getFriends().size()>0);
	
	}

	@Test
	@Order(5)
	public void subcribeFriend() throws Exception {
	}

	@Test
	@Order(6)
	public void blockFriend() throws Exception {
	}

	@Test
	@Order(7)
	public void notifyEmailList() throws Exception {
	}
	
	
	
//	private void readJson() {
//        ClassPathResource resource = new ClassPathResource("testcases/addFriend_00.json");
//        try (InputStream inputStream = resource.getInputStream()) {
//            testnames = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
//                    .lines()
//                    .collect(Collectors.toList());
//        } catch (IOException ex) {
//            System.out.println(String.join("\n", Stream.of(ex.getStackTrace())
//                    .map(trace -> trace.toString())
//                    .collect(Collectors.toList())));
//
//            throw new Error(ex.toString());
//        }
//	}

}
