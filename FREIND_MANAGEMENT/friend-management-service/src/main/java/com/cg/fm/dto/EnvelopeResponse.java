package com.cg.fm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Predeep
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnvelopeResponse<T> {
	public String code;
	public T data;
	public String msg;
}
