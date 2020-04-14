package com.cg.fm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.fm.domain.User;
import com.cg.fm.exception.InvalidUserException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidationUtils {
	
//	public static void validateFriendShipCriteria(final String userOneEmail, final String userTwoEmail ) {
//		//String userOneEmail = u1.getEmail(), userTwoEmail = u2.getEmail();
//		log.info("Start :: validateFriendShipCriteria :: {} {}", userOneEmail, userTwoEmail);
//		if (userOneEmail.equalsIgnoreCase(userTwoEmail)) {
//			log.info("InvalidUserexception :: validateFriendShipCriteria :: {} {}", userOneEmail, userTwoEmail);
//			throw new InvalidUserException();
//		}
//		log.info("End :: validateFriendShipCriteria :: {} {}", userOneEmail, userTwoEmail);
//	}

	public static boolean isValidEmail(final String email) {
		log.info("Start :: isValidEmail :: {}", email);
		Pattern pattern = Pattern.compile(Constant.VALID_EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		boolean flag = matcher.matches();
		log.info("End :: isValidEmail :: {}", email);
		return flag;
	}
}
