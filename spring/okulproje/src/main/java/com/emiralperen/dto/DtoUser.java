package com.emiralperen.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DtoUser {
	
	
	private String username;
	private String password;
	private String role;
	
}
