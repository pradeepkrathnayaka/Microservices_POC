package com.cg.fm.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cg.fm.domain.FriendRelation;
import com.cg.fm.domain.User;
import com.cg.fm.dto.FriendEmailListDto;
import com.cg.fm.dto.NotifyUpdateDto;
import com.cg.fm.dto.RequestorTargetEmailDto;
import com.cg.fm.exception.BlockeFriendException;
import com.cg.fm.exception.EmailRegisterRepeatedApiException;
import com.cg.fm.exception.InvalidEmailException;
import com.cg.fm.exception.InvalidRequestApiException;
import com.cg.fm.exception.InvalidUserException;
import com.cg.fm.repo.FriendRelationRepository;
import com.cg.fm.util.Constant;
import com.cg.fm.util.ValidationUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Predeep
 *
 */
@Service
@Slf4j
public class FriendRelationServiceImpl implements FriendRelationService {

	@Autowired
	private FriendRelationRepository friendRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FriendSubcribeService subscriptionService;
	
	@Autowired
	private FriendBlockService blockingService;
		
	/* (non-Javadoc)
	 * @see com.cg.fm.service.FriendRelationshipService#createFriendConnection(com.cg.fm.dto.FriendEmailListDto)
	 */
	public FriendRelation createFriendConnection(FriendEmailListDto friendEmailListDto)
			throws InvalidUserException, InvalidEmailException {
		log.info("Start :: createFriendConnection");
		String email_1 = friendEmailListDto.getFriends().get(0);
		String email_2 = friendEmailListDto.getFriends().get(1);

		log.debug("[Create a friend Connection]-email1={}, email2={}", email_1, email_2);
		System.out.println("[Create a friend Connection]-email1={}, email2={}"+ email_1+ email_2);

		User u1 = userService.findOneByEmail(email_1);
		User u2 = userService.findOneByEmail(email_2);
		
		FriendRelation persisted = friendRepo.save(buildFriendConnection(u1, u2));
		
		log.debug(persisted.toString());
		log.info("End :: createFriendConnection");
		return persisted;
	}
	
	
	/**
	 * @param u1
	 * @param u2
	 * @return
	 */
	private FriendRelation buildFriendConnection(final User u1, final User u2) {
		String userOneEmail = u1.getEmail(), userTwoEmail = u2.getEmail();
		long userOneId = u1.getId(), userTwoId = u2.getId();

		log.info("Start :: buildFriendConnection {} {} ", userOneEmail, userTwoEmail);
		if(ValidationUtils.isValidEmail(userOneEmail)) {
			throw new InvalidEmailException();
		}
		if(ValidationUtils.isValidEmail(userTwoEmail)) {
			throw new InvalidEmailException();
		}
		if (userOneEmail.equalsIgnoreCase(userTwoEmail)) {
			log.info("InvalidUserexception :: validateFriendShipCriteria :: {} {}", userOneEmail, userTwoEmail);
			throw new InvalidUserException("903.1");
		}
		
		if (checkBothAreAlreadyFriends(userOneId, userTwoId)) {
			log.info("DuplicateInvitaionException :: createFriend {} {} ", userOneEmail, userTwoEmail);
			throw new EmailRegisterRepeatedApiException(userOneEmail, userTwoEmail);
		}
		if (blockingService.isBlocked(userOneEmail, userTwoEmail)) {
			log.info("FriendBlockedException :: createFriend {} {} ", userOneEmail, userTwoEmail);
			throw new BlockeFriendException("908");
		}
		log.info("End :: buildFriendConnection {} {} ", userOneEmail, userTwoEmail);
		return new FriendRelation(userOneId, userTwoId, Constant.STS_PENDING);
	}
	
	/**
	 * @param userOneId
	 * @param userTwoId
	 * @return
	 */
	private boolean checkBothAreAlreadyFriends(long userOneId, long userTwoId) {
		log.info("Start :: checkBothAreAlreadyFriends {} {} ", userOneId, userTwoId);
		List<FriendRelation> userOneFriends = friendRepo.findByFriendRPkUser1Id(userOneId);
		log.info("End :: checkBothAreAlreadyFriends {} {} ", userOneId, userTwoId);
		return userOneFriends
				.stream()
				.anyMatch(e -> userTwoId == e.getId().getUser2Id());
	}

	/* (non-Javadoc)
	 * @see com.cg.fm.service.FriendRelationshipService#retrieveCommonFriends(com.cg.fm.dto.FriendEmailListDto)
	 */
	@Override
	public List<String> retrieveCommonFriends(FriendEmailListDto friendEmailListDto) {
		try {
			log.info("Start :: retrieveCommonFriends");
			List<String> friends = friendEmailListDto.getFriends();
			String userOneEmail = friends.get(0);
			String userTwoEmail = friendEmailListDto.getFriends().get(1);
			log.debug("[retrieveCommonFriends]-email1={}, email2={}", userOneEmail, userTwoEmail);
			if (!ValidationUtils.isValidEmail(userOneEmail)) {
				throw new InvalidEmailException("902.1", new String[]{userOneEmail});
			}			
			if (!ValidationUtils.isValidEmail(userOneEmail)) {
				throw new InvalidEmailException("902.11", new String[]{userOneEmail});
			}
			
			if (userOneEmail.equalsIgnoreCase(userTwoEmail)) {
				log.info("InvalidUserexception :: validateFriendShipCriteria :: {} {}", userOneEmail, userTwoEmail);
				throw new InvalidUserException("903.1");
			}
			
			List<String> friends1Emails = friendRepo.findAllFriendsByEmail(userOneEmail);
			List<String> friends2Emails = friendRepo.findAllFriendsByEmail(userTwoEmail);

			log.info("End :: retrieveCommonFriends");
			
			return friends1Emails.stream()
					.filter(s -> friends2Emails.contains(s))
					.collect(Collectors.toList());
			
		} catch (Exception e) {
			log.warn(e.getMessage());
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see com.cg.fm.service.FriendRelationshipService#retrieveFriendsByEmail(java.lang.String)
	 */
	@Transactional(readOnly = true)
	@Override
	public List<String> retrieveFriendsEmailsByRequestorEmail(String email) {
		try {
			log.info("Start :: retrieveFriendsEmailsByRequestorEmail :: {}", email);
			ValidationUtils.isValidEmail(email);
			if(userService.existsByEmail(email)) {
				List<String> emailList = friendRepo.findAllFriendsByEmail(email);
				log.info("Start :: retrieveFriendsEmailsByRequestorEmail :: {}", email);
				return emailList;
			} else {
				return Collections.emptyList();
			}			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cg.fm.service.FriendRelationshipService#subcribe(com.cg.fm.dto.
	 * RequestorTargetEmailDto)
	 */
	@Override
	public void subcribeFriend(RequestorTargetEmailDto requestorTargetEmailDto) {
		try {
			String requestorEmail = requestorTargetEmailDto.getRequestor();
			String targetEmail = requestorTargetEmailDto.getTarget();
			
			log.info("Start :: subcribe {} {} ", requestorEmail,
					requestorTargetEmailDto.getTarget());
			if (!ValidationUtils.isValidEmail(requestorEmail)) {
				throw new InvalidEmailException("902.1", new String[]{requestorEmail});
			}			
			if (!ValidationUtils.isValidEmail(targetEmail)) {
				throw new InvalidEmailException("902.11", new String[]{targetEmail});
			}
			if (requestorEmail.equalsIgnoreCase(targetEmail)) {
				log.info("InvalidUserexception :: validateFriendShipCriteria :: {} {}", requestorEmail, targetEmail);
				throw new InvalidUserException("903.1");
			}
			subscriptionService.subscribe(requestorEmail, targetEmail);
			log.info("End :: subcribe {} {} ", requestorEmail,
					targetEmail);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see com.cg.fm.service.FriendRelationshipService#block(com.cg.fm.dto.RequestorTargetEmailDto)
	 */
	@Override
	public void blockFriend(RequestorTargetEmailDto requestorTargetEmailDto) {
		try {
			String requestorEmail = requestorTargetEmailDto.getRequestor();
			String targetEmail = requestorTargetEmailDto.getTarget();
			log.info("Start :: block {} {} ", requestorEmail, targetEmail);
			if(StringUtils.isEmpty(requestorTargetEmailDto.getRequestor()) ||
					StringUtils.isEmpty(requestorTargetEmailDto.getTarget())) {
				throw new InvalidRequestApiException("902.NotNull");
			}
			ValidationUtils.isValidEmail(requestorTargetEmailDto.getRequestor());
			ValidationUtils.isValidEmail(requestorTargetEmailDto.getTarget());
			blockingService.block(requestorTargetEmailDto.getRequestor(), requestorTargetEmailDto.getTarget());
			log.info("End :: block {} {} ", requestorTargetEmailDto.getRequestor(),
					requestorTargetEmailDto.getTarget());
		} catch (Exception e) {
			log.warn(e.getMessage());
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see com.cg.fm.service.FriendRelationshipService#notify(com.cg.fm.dto.NotifyUpdateDto)
	 */
	@Transactional(readOnly = true)
	@Override
	public List<String> notifyEmailList(NotifyUpdateDto notifyUpdateDto) {
		log.info("Start :: notify {} {}", notifyUpdateDto.getSender(), notifyUpdateDto.getText());

		if (null == notifyUpdateDto.getSender() || null == notifyUpdateDto.getText()) {
			log.info("InvalidRequestException :: getUpdate {} {}", notifyUpdateDto.getSender(),
					notifyUpdateDto.getText());
			throw new InvalidRequestApiException("Invalid request : Request doesnt contains sender or text or null");
		}
		ValidationUtils.isValidEmail(notifyUpdateDto.getSender());
		userService.findOneByEmail(notifyUpdateDto.getSender());

		List<String> sendorFriends = friendRepo.findAllFriendsByEmail(notifyUpdateDto.getSender());
		List<String> senderSubscribers = subscriptionService.retrieveSubscribers(notifyUpdateDto.getSender());
		List<String> senderBlockers = blockingService.fetchBlockers(notifyUpdateDto.getSender());
		List<String> extractedMailsFromSenderText = extractEmailsFromSenderText(notifyUpdateDto.getText());

		List<String> recipentsList = buildRecipients(notifyUpdateDto.getSender(), sendorFriends, senderSubscribers,
				senderBlockers, extractedMailsFromSenderText);

		log.info("End :: notify {} {}", notifyUpdateDto.getSender(), notifyUpdateDto.getText());
		return recipentsList;
	}

	/**
	 * @param textWithEmail
	 * @return
	 */
	private List<String> extractEmailsFromSenderText(final String textWithEmail) {
		log.info("Start :: extractMailsFromText :: {}", textWithEmail);
		Pattern pattern = Pattern.compile(Constant.EMAIL_REGEX_MATCHER);
		Matcher matcher = pattern.matcher(textWithEmail);
		List<String> extractedEmails = new ArrayList<String>();
		while (matcher.find()) {
			extractedEmails.add(matcher.group());
		}
		log.info("End :: extractMailsFromText :: {}", textWithEmail);
		return extractedEmails;
	}
	
	/**
	 * @param sender
	 * @param senderFriends
	 * @param senderSubscribers
	 * @param senderBlockers
	 * @param extractedMailsFromSenderText
	 * @return
	 */
	private List<String> buildRecipients(final String sender, final List<String> senderFriends,
			final List<String> senderSubscribers, final List<String> senderBlockers,
			final List<String> extractedMailsFromSenderText) {
		log.info("Start :: buildRecipients {}", sender);
		Set<String> setOfRecipents = new HashSet<>();
		setOfRecipents.addAll(senderFriends);
		setOfRecipents.addAll(senderSubscribers);
		setOfRecipents.addAll(extractedMailsFromSenderText);
		setOfRecipents.removeAll(senderBlockers);
		setOfRecipents.remove(sender);
		List<String> recipents = new ArrayList<String>(setOfRecipents);
		log.info("End :: buildRecipients {}", sender);
		return recipents;
	}
}
