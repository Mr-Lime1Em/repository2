package com.example.Shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "username не может быть пустым")
	private String username;

	@Size(min = 3, message = "пароль должен состоять из минимум трех элементов")
	@NotBlank(message = "password не может быть пустым")
	private String password;

	private boolean enabled;

	@Email(message = "Должен соответствовать формату email")
	private String email;
	@NotBlank(message = "phoneNumber не может быть пустым")
	private String phoneNumber;
}