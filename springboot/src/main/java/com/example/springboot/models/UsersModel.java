package com.example.springboot.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;


@Entity
@Table(name = "TB_USERS")
public class UsersModel extends RepresentationModel<UsersModel>implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID idUser;
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String cpf;
	@Column
	private String profile;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private ZonedDateTime creationDate;

	@Column
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime updateDate;
    @Column
    private LocalDate birthDate;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
	private AddressModel address;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<PhoneModel> phoneNumbers;
	
	
	@PrePersist
    private void prePersistFunction() {
		this.updateDate = null;
        this.creationDate = ZonedDateTime.now();
    }
	@PreUpdate
    private void preUpdateFunction() {
        this.updateDate = ZonedDateTime.now();
    }
	
	
	public UUID getIdUser() {
		return idUser;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
	//  integer strength = 12;
	//  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(strength);
	//  this.password = passwordEncoder.encode(password);
		this.password = password;
	}
	
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	public ZonedDateTime getCreationDate() {
		return creationDate;
	}
//	public void setCreationDate(ZonedDateTime creationDate) {
//		this.creationDate = creationDate;
//	}
	
	
	public ZonedDateTime getUpdateDate() {
		return updateDate;
	}
//	public void setUpdateDate(ZonedDateTime updateDate) {
//		this.updateDate = updateDate;
//	}
	
	
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	
	public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }
    
    public List<PhoneModel> getPhoneNumbers() {
        return phoneNumbers;
    }
    public void setPhoneNumbers(List<PhoneModel> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

}