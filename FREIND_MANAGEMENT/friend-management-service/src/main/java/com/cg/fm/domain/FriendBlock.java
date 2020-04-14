package com.cg.fm.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Blocking model")
@Entity
@Table(name = "FRIEND_BLOCK")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendBlock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Embeddable
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BlockPk implements Serializable {
		private static final long serialVersionUID = 1L;
		private String requestor, target;
	}

	@EmbeddedId
	private BlockPk id = new BlockPk();

//	@JoinColumn(name = "TARGET")
//	@MapsId("targetId")
//	@ManyToOne
//	private User target;
//
//	@JoinColumn(name = "REQUESTOR")
//	@MapsId("requestorId")
//	@ManyToOne
//	private User requestor;

	public FriendBlock(String emailRequestor, String emailTarget) {
		this.setId(new BlockPk(emailRequestor, emailTarget));
	}

}
