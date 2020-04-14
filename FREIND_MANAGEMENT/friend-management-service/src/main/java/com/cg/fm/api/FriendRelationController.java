package com.cg.fm.api;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fm.config.SwaggerConfig;
import com.cg.fm.domain.FriendRelation;
import com.cg.fm.dto.BaseResponseDto;
import com.cg.fm.dto.EmailRequestDto;
import com.cg.fm.dto.FriendEmailListDto;
import com.cg.fm.dto.FriendResponseDto;
import com.cg.fm.dto.NotifyUpdateDto;
import com.cg.fm.dto.NotifyUpdateResponseDto;
import com.cg.fm.dto.RequestorTargetEmailDto;
import com.cg.fm.exception.InvalidEmailException;
import com.cg.fm.exception.InvalidRequestApiException;
import com.cg.fm.service.FriendRelationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author Predeep
 *
 */
@Api(value = "Friend Management", tags = { SwaggerConfig.TAG_1 })
@SwaggerDefinition()
@RestController
@RequestMapping("/friends")
@Validated
@Slf4j
@CrossOrigin
public class FriendRelationController {

	@Autowired
	private FriendRelationService friendService;

	@ApiIgnore
	@RequestMapping(value = "/ignore", method = RequestMethod.GET)
	public String jsonTest() {
		log.info("ApiIgnore");
		return "ApiIgnore!";
	}

	@ApiOperation(value = "Add specific friends by email address ", response = BaseResponseDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK -> Invalid Request, must not be null, should be two emails"),
			@ApiResponse(code = 201, message = "OK -> Invalid email provided in the request"),
			@ApiResponse(code = 201, message = "OK -> Email 1 must be registered"),
			@ApiResponse(code = 201, message = "OK -> Email 3 must be registered"),
			@ApiResponse(code = 202, message = "Accepted -> The user has been updated successfully"),
			@ApiResponse(code = 202, message = "failed -> The user has been updated successfully"),
			@ApiResponse(code = 204, message = "NO CONTENT -> The user has been removed successfully")
			})
	@PostMapping(value = "/add")
	public ResponseEntity<BaseResponseDto> addFriend(
			@ApiParam(value = "AddFriend DTO object") @Valid @RequestBody FriendEmailListDto friendEmailListDto)
			throws InvalidEmailException {
		
		if (Objects.isNull(friendEmailListDto.getFriends()) || friendEmailListDto.getFriends().size() != 2) {
			//log.info("InvalidRequestException :: createFriend {} {} ", friendEmailListDto.getFriends().get(0),friendEmailListDto.getFriends().get(1));
			throw new InvalidRequestApiException();
		}
		log.info("Start :: add friend :: {} {}", friendEmailListDto.getFriends().get(0),
				friendEmailListDto.getFriends().get(1));
	
		FriendRelation friendConnection = friendService.createFriendConnection(friendEmailListDto);
		log.info("Create a friend connection between two email addresses successfully" + friendConnection);
		return new ResponseEntity<BaseResponseDto>(new BaseResponseDto(true), HttpStatus.OK);
	}

	@ApiOperation(value = " Retrieve the friends list for an email address", response = FriendResponseDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK -> Invalid Request, must not be null"),
			@ApiResponse(code = 201, message = "OK -> Invalid email provided in the request"),
			@ApiResponse(code = 201, message = "OK -> Email must be registered"),
			@ApiResponse(code = 202, message = "Accepted -> The data has been fetched successfully"),
			@ApiResponse(code = 202, message = "failed -> The user has been updated successfully"),
			})
	@PostMapping(value = "/list")
	public ResponseEntity<FriendResponseDto> listFriends(
			@ApiParam(value = "List Friend by email DTO object") @Valid @RequestBody EmailRequestDto email) {
		if (Objects.isNull(email) || StringUtils.isEmpty(email.getEmail())) {
			log.info("InvalidRequestException :: list friends {} ", email.getEmail());
			throw new InvalidRequestApiException("902.NotNull");
		}
		List<String> emailList = friendService.retrieveFriendsEmailsByRequestorEmail(email.getEmail());
		FriendResponseDto resp = new FriendResponseDto(true, emailList);
		return ResponseEntity.ok().body(resp);
	}

	@ApiOperation(value = "Retrieve the common friends list between two email addresses", notes = "", response = FriendResponseDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK -> Invalid Request, must not be null, should be two emails"),
			@ApiResponse(code = 201, message = "OK -> Invalid email provided in the request"),
			@ApiResponse(code = 201, message = "OK -> Email 1 must be registered"),
			@ApiResponse(code = 201, message = "OK -> Email 2 must be registered"),
			@ApiResponse(code = 202, message = "Accepted -> The user has been updated successfully"),
			@ApiResponse(code = 202, message = "failed -> The user has been updated successfully"),
			@ApiResponse(code = 204, message = "NO CONTENT -> The user has been removed successfully") 
	        })
	@PostMapping(value = "/common")
	public ResponseEntity<FriendResponseDto> commonFriend(
			@ApiParam(value = "Friend Email List DTO object") @Valid @RequestBody FriendEmailListDto friendEmailListDto) {
		log.info("commonFriend!");
		if (Objects.isNull(friendEmailListDto) || Objects.isNull(friendEmailListDto.getFriends())
				|| friendEmailListDto.getFriends().size() != 2) {
			log.info("InvalidRequestException :: common friend between {} ",	friendEmailListDto);
			throw new InvalidRequestApiException();
		}		
		List<String> commonFriends = friendService.retrieveCommonFriends(friendEmailListDto);
		return ResponseEntity.ok().body(new FriendResponseDto(true, commonFriends));
	}

	@ApiOperation(value = "﻿Subscribe to updates from an email address", notes = "", response = BaseResponseDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK -> Invalid Request, must not be null, should be two emails"),
			@ApiResponse(code = 201, message = "OK -> Invalid email provided in the request"),
			@ApiResponse(code = 201, message = "OK -> Email 1 must be registered"),
			@ApiResponse(code = 201, message = "OK -> Email 2 must be registered"),
			@ApiResponse(code = 202, message = "Accepted -> The user has been updated successfully"),
			@ApiResponse(code = 202, message = "failed -> The user has been updated successfully"),
			@ApiResponse(code = 204, message = "NO CONTENT -> The user has been removed successfully")  
	        })
	@PostMapping(value = "/subcribe")
	public ResponseEntity<BaseResponseDto> subcribeFriend(@RequestBody RequestorTargetEmailDto requestorTargetEmailDto) {
		log.info("Subcribe!");
		if (Objects.isNull(requestorTargetEmailDto) || StringUtils.isEmpty(requestorTargetEmailDto.getRequestor())
				|| StringUtils.isEmpty(requestorTargetEmailDto.getTarget())) {
			log.info("InvalidRequestException :: subcribe friend {} ",	requestorTargetEmailDto);
			throw new InvalidRequestApiException();
		}	
		friendService.subcribeFriend(requestorTargetEmailDto);
		log.info("Create a friend connection between two email addresses successfully");
		return new ResponseEntity<BaseResponseDto>(new BaseResponseDto(true), HttpStatus.OK);
	}

	@ApiOperation(value = "﻿Block updates from an email address", notes = "", response = BaseResponseDto.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Connector configuration for the given zone is successfully created."), 
	        @ApiResponse(code = 201, message = "Connector configuration for the given zone is successfully created."), 
	        @ApiResponse(code = 201, message = "Connector configuration for the given zone is successfully created."),
	        @ApiResponse(code = 201, message = "Connector configuration for the given zone is successfully created.") 
	        })
	@PostMapping(value = "/block")
	public ResponseEntity<BaseResponseDto> blockFriend(@RequestBody RequestorTargetEmailDto requestorTargetEmailDto) {
		log.info("block!");
		if (Objects.isNull(requestorTargetEmailDto) || StringUtils.isEmpty(requestorTargetEmailDto.getRequestor())
				|| StringUtils.isEmpty(requestorTargetEmailDto.getTarget())) {
			log.info("InvalidRequestException :: block friend {} ",	requestorTargetEmailDto);
			throw new InvalidRequestApiException();
		}	
		friendService.blockFriend(requestorTargetEmailDto);
		return new ResponseEntity<BaseResponseDto>(new BaseResponseDto(true), HttpStatus.OK);
	}

	@ApiOperation(value = "﻿Retrieve all email addresses that can receive updates from an email address", notes = "")
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Connector configuration for the given zone is successfully created."), 
	        @ApiResponse(code = 201, message = "Connector configuration for the given zone is successfully created."), 
	        @ApiResponse(code = 201, message = "Connector configuration for the given zone is successfully created."),
	        @ApiResponse(code = 201, message = "Connector configuration for the given zone is successfully created.") 
	        })
	@PostMapping(value = "/notify")
	public ResponseEntity<NotifyUpdateResponseDto> notifyEmailList(@RequestBody NotifyUpdateDto notifyUpdateDto) {
		log.info("notify!");
		if (Objects.isNull(notifyUpdateDto) || StringUtils.isEmpty(notifyUpdateDto.getSender())
				|| StringUtils.isEmpty(notifyUpdateDto.getText())) {
			log.info("InvalidRequestException :: block friend {} ",	notifyUpdateDto);
			throw new InvalidRequestApiException();
		}
		log.info(String.format("Sender[%s] Text[%s]", notifyUpdateDto.getSender(), notifyUpdateDto.getText()));
		log.info("Start :: notifyUpdateDto :: {} {}", notifyUpdateDto.getSender(), notifyUpdateDto.getText());
		List<String> recipentList = friendService.notifyEmailList(notifyUpdateDto);
		log.info("End :: notifyUpdateDto :: {} {}", notifyUpdateDto.getSender(), notifyUpdateDto.getText());
		//log.info("Exception :: notifyUpdateDto :: {} {}", notifyUpdateDto.getSender(), notifyUpdateDto.getText());
		return new ResponseEntity<NotifyUpdateResponseDto>(new NotifyUpdateResponseDto(true, recipentList), HttpStatus.OK);
	}
}
