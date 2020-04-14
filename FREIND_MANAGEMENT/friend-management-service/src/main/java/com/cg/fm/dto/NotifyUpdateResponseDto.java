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
public class NotifyUpdateResponseDto extends BaseResponseDto {
	private List<String> recipients;

	public NotifyUpdateResponseDto(boolean success, List<String> recipients) {
		super(success);
		this.recipients = recipients;
	}
}
