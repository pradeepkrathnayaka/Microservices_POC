package com.cg.fm.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Predeep
 *
 */
@ApiModel(description = "User model")
@Entity
@Table(name = "USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

	public User(String email, String username) {
		this.email=email;
		this.username=username;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "id in user table", accessMode = AccessMode.READ_ONLY)
	private Long id;

	@Column(nullable = false, unique = true)
	@NotNull
	@ApiModelProperty(value = "user email")
	private String email;

	@Column(nullable = false, unique = true)
	@ApiModelProperty(value = "user name")
	private String username;
	
	@OneToMany( mappedBy="id.user1Id", cascade=CascadeType.ALL, orphanRemoval = true)
	private Set<FriendRelation> friends = new HashSet<>();
	

}
