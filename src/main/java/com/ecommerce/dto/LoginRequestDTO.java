package com.ecommerce.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author shivam.rai
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {

	@NotEmpty(message = "Please enter username")
	private String username;
	
	@Size(max=16,min=6,message = "Paswword length should be minimum 6 and max 16")
	private String password;
	
	
	
	
}
