package com.bso.springjpa.Spring.jpa.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name="tbl_students", uniqueConstraints = @UniqueConstraint(name="email_unique", columnNames = "email_address"))
public class Student {
	@Id
	@SequenceGenerator(name="student_sequence", sequenceName = "studen_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "student_sequence")
	@Column(name = "student_id")
	private Long studentId;
	@Column(columnDefinition = "varchar(50)")
	@NotBlank(message="field 'firstName' is mandatory")
	@Size(min = 1, max = 25, message = "The name needs to have atleast 2 letters")
	@Pattern(regexp = "[a-zA-Z]+$", message = "Name just allow letters")
	private String firstName;
	private String lastName;
	@Column(name="email_address", nullable = false)
	@NotBlank(message="field 'email' is mandatory")
	@Pattern(regexp =  "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email format is not correct")
	private String emailId;	
	@NotBlank(message = "field 'password' is mandatory")
	private String password;
	@NotBlank(message = "field 'role' is mandatory")
	private String role;
	@Embedded
	private Guardian guardian;
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Course> courses;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="student_authorities_table", 
			   joinColumns = {
					   @JoinColumn(name="student_id", referencedColumnName = "student_id")
					   },
			   inverseJoinColumns = {
					   @JoinColumn(name="authorities_id", referencedColumnName = "authorities_id")
			   }
	)
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Set<Authority> authorities; 
	
	//@JsonManagedReference
}
