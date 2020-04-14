package com.cg.fm.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Check;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "FriendRelation model")
@Entity
@Table(name = "FRIEND_RELATION")
//@Check(constraints = "PID1 < PID2")
@Data
//@NamedNativeQuery(name = "FriendRelationship.fetchFriends",
//query = "SELECT personTwo AS friend FROM FriendRelationship WHERE personOne =:person" + " UNION "
//    + "SELECT personOne AS friend FROM FriendRelationship WHERE personTwo =:person")
public class FriendRelation implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Embeddable
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class FriendRPk implements Serializable {
		private static final long serialVersionUID = 1L;
		private Long user1Id, user2Id;
	}

	@EmbeddedId
	private FriendRPk id = new FriendRPk();

//	@JoinColumn(name = "UID1")
//	@MapsId("uid1")
//	@ManyToOne
//	private User user1Id;
//
//	@JoinColumn(name = "UID2")
//	@MapsId("uid2")
//	@ManyToOne
//	private User user2Id;
	
    @Column(nullable = false)
    private String status;
    

	public FriendRelation(Long userOneId, Long userTwoId, String status) {
		this.setId(new FriendRPk(userOneId, userTwoId));
		this.setStatus(status);
	}


	public FriendRelation() {
		
	}
}
