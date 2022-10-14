package com.bso.springjpa.Spring.jpa.models;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Valid
@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverrides({
	@AttributeOverride(name="name", column = @Column(name="guardian_name")),
	@AttributeOverride(name="email", column = @Column(name="guardian_email")),
	@AttributeOverride(name="mobile", column = @Column(name="guardian_mobile")),
})
public class Guardian {
	@NotNull(message = "The field Guardian Name is mandatory")
	private String name;
	private String email;
	private String mobile;
}
