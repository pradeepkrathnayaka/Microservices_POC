package com.cg.fm.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Predeep
 *
 */
@Getter
@Setter
public class FriendResponseDto extends BaseResponseDto {
	
	private List<String> friends;
	private int count;

	public FriendResponseDto() {
		super();
	}

	public FriendResponseDto(boolean success, List<String> friends) {
		super(success);
		this.friends = friends;
		this.count = friends.size();
	}
}
