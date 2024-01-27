package com.example.springboot.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


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
	
	@GetMapping("/users")
	public ResponseEntity<List<UsersModel>> getAllUsers(){
		List<UsersModel> usersList = usersRepository.findAll();
		if(!usersList.isEmpty()) {
			for(UsersModel user : usersList) {
				UUID id = user.getIdUser();
				user.add(linkTo(methodOn(UsersController.class).getOneUser(id)).withSelfRel());
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(usersList);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<Object> getOneUser(@PathVariable(value="id") UUID id){
		Optional<UsersModel> user0= usersRepository.findById(id);
		if(user0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
		}
		user0.get().add(linkTo(methodOn(UsersController.class).getAllUsers()).withRel("Users List"));
		return ResponseEntity.status(HttpStatus.OK).body(user0.get());
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable(value="id") UUID id,
											 @RequestBody @Valid UsersRecordDto usersRecordDto){
		Optional<UsersModel> user0= usersRepository.findById(id);
		if(user0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
		}
		
		var usersModel = user0.get();
		BeanUtils.copyProperties(usersRecordDto, usersModel);
		return ResponseEntity.status(HttpStatus.OK).body(usersRepository.save(usersModel));
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable(value="id") UUID id){
		Optional<UsersModel> user0= usersRepository.findById(id);
		if(user0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
		}
		usersRepository.delete(user0.get());
		return ResponseEntity.status(HttpStatus.OK).body("Usuário excluído com sucesso!");
	}
}