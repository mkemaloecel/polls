package com.example.polls.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "vote_pk_sequence", sequenceName = "vote_id_seq", allocationSize = 1)
@Table(name = "vote", uniqueConstraints = {
	@UniqueConstraint(columnNames = {
		"poll_id",
		"user_id"
	})
})
public class Vote implements IdLong {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vote_pk_sequence")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "poll_id", nullable = false)
	private Poll poll;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "choice_id", nullable = false)
	private Choice choice;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@CreationTimestamp
	@Column(name = "create_date_time")
	private Instant createDateTime;

	@UpdateTimestamp
	@Column(name = "update_date_time")
	private Instant updateDateTime;

}
