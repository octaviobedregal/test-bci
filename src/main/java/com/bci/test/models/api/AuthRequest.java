package com.bci.test.models.api;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AuthRequest {
	@NotNull @Email
	private String email;
	
	@NotNull
	private String password;
	
}
