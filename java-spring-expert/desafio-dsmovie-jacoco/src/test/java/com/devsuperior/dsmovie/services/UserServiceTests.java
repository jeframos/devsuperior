package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.entities.UserEntity;
import com.devsuperior.dsmovie.projections.UserDetailsProjection;
import com.devsuperior.dsmovie.repositories.UserRepository;
import com.devsuperior.dsmovie.tests.UserDetailsFactory;
import com.devsuperior.dsmovie.tests.UserFactory;
import com.devsuperior.dsmovie.utils.CustomUserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration
public class UserServiceTests {

	@InjectMocks
	private UserService service;

	@Mock
	private UserRepository repository;

	@Mock
	private CustomUserUtil userUtil;

	private String username, nonExistingUsername;
	private UserEntity user;
	private List<UserDetailsProjection> userDetails;

	@BeforeEach
	void setUp() {
		username = "maria@gmail.com";
		nonExistingUsername = "user@gmail.com";

		user = UserFactory.createUserEntity();

		userDetails = UserDetailsFactory.createCustomAdminUser(username);
	}

	@Test
	public void authenticatedShouldReturnUserEntityWhenUserExists() {
		Mockito.when(userUtil.getLoggedUsername()).thenReturn(username);
		Mockito.when(repository.findByUsername(username)).thenReturn(Optional.of(user));

		UserEntity result = service.authenticated();
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getUsername(), username);
	}

	@Test
	public void authenticatedShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists() {
		Mockito.doThrow(ClassCastException.class).when(userUtil).getLoggedUsername();

		Assertions.assertThrows(UsernameNotFoundException.class, () ->
				service.authenticated()
		);
	}

	@Test
	public void loadUserByUsernameShouldReturnUserDetailsWhenUserExists() {
		Mockito.when(repository.searchUserAndRolesByUsername(username)).thenReturn(userDetails);

		UserDetails result = service.loadUserByUsername(username);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getUsername(), username);
	}

	@Test
	public void loadUserByUsernameShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists() {
		Mockito.when(repository.searchUserAndRolesByUsername(nonExistingUsername)).thenReturn(new ArrayList<>());

		Assertions.assertThrows(UsernameNotFoundException.class, () ->
				service.loadUserByUsername(nonExistingUsername)
		);
	}
}
