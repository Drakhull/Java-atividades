package com.example.springboot.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dtos.UsersRecordDto;
import com.example.springboot.models.UsersModel;
import com.example.springboot.repositories.UsersRepository;

import jakarta.validation.Valid;

@RestController
public class UsersController {
	
	@Autowired
	UsersRepository usersRepository;
	
	@PostMapping("/users")
	public ResponseEntity<UsersModel> saveUser(@RequestBody @Valid UsersRecordDto usersRecordDto){
		var usersModel = new UsersModel();
		BeanUtils.copyProperties(usersRecordDto, usersModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(usersRepository.save(usersModel));
	}
}