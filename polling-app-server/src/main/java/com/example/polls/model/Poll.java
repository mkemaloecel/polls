package com.example.polls.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
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
@SequenceGenerator(name = "poll_pk_sequence", sequenceName = "poll_id_seq", allocationSize = 1)
@Table(name = "poll")
public class Poll implements IdLong {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poll_pk_sequence")
	private Long id;

	@NotBlank
	@Size(max = 140)
	private String question;

	@CreatedBy
	private Long createdBy;

	@LastModifiedBy
	private Long updatedBy;

	@CreationTimestamp
	@Column(name = "create_date_time")
	private Instant createDateTime;

	@UpdateTimestamp
	@Column(name = "update_date_time")
	private Instant updateDateTime;

	@OneToMany(
		mappedBy = "poll",
		cascade = CascadeType.ALL,
		fetch = FetchType.EAGER,
		orphanRemoval = true
	)
	@Size(min = 2, max = 6)
	@Fetch(FetchMode.SELECT)
	@BatchSize(size = 30)
	private List<Choice> choices = new ArrayList<>();

	@NotNull
	private Instant expirationDateTime;

	public void addChoice(Choice choice) {
		choices.add(choice);
		choice.setPoll(this);
	}

	public void removeChoice(Choice choice) {
		choices.remove(choice);
		choice.setPoll(null);
	}

}
