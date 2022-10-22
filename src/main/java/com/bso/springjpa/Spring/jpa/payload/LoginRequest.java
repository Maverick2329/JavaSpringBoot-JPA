package com.bso.springjpa.Spring.jpa.payload;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {

	@NotBlank
	private String Email;
	
	@NotBlank
	private String password;
}
