package com.cg.fm.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "FriendSubcribe model")
@Entity
@Table(name = "FRIEND_SUBSCRIBE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendSubcribe implements Serializable {

	private static final long serialVersionUID = 6180210400731762562L;

	@Embeddable
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class SubscribePk implements Serializable {
		private static final long serialVersionUID = 1L;
		private String requestor, target;
	}

	@EmbeddedId
	private SubscribePk id = new SubscribePk();

//	@ManyToOne
//	@MapsId("requestor")
//	private User requestor;
//
//	@ManyToOne
//	@MapsId("target")
//	private User target;

	public FriendSubcribe(String emailRequestor, String emailTarget) {
		this.setId(new SubscribePk(emailRequestor, emailTarget));
	}

}
