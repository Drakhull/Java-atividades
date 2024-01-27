package com.example.springboot.dtos;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.springboot.models.AddressModel;
import com.example.springboot.models.PhoneModel;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record UsersRecordDto(@NotBlank 			String name, 		
							 @NotBlank	@Email 	String email, 
							 @NotBlank 			String password, 	
							 @NotBlank	@CPF 	String cpf, 
							 ZonedDateTime creationDate, 
							 ZonedDateTime updateDate, 
							 @NotNull	@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthDate,
							 @NotBlank 			String profile,		
							 @Valid @NotNull 	AddressModel address,
							 @NotEmpty List<@Valid @NotNull PhoneModel> phoneNumbers) {
	
	
}