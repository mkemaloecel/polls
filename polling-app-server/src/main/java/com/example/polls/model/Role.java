package com.example.polls.model;

import org.hibernate.annotations.NaturalId;
import javax.persistence.*;

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
@SequenceGenerator(name = "role_pk_sequence", sequenceName = "role_id_seq", allocationSize = 1)
@Table(name = "role")
public class Role implements IdLong {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_pk_sequence")
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;

}
