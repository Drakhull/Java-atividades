package com.example.springboot.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springboot.models.UsersModel;


public interface UsersRepository extends JpaRepository<UsersModel, UUID>{
 
}