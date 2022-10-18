package com.bso.springjpa.Spring.jpa.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="authorities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Authority {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name="native", strategy = "native")
	@Column(name="authorities_id")
	private Long id;
			
	private String name;
	
	@ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
	@JsonBackReference
	private Set<Student> student;
}
