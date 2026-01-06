package com.sph.user.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sph.user.dto.UserRequestDto;
import com.sph.user.dto.UserResponseDto;
import com.sph.user.entity.User;
import com.sph.user.exception.UserNotFoundException;
import com.sph.user.repo.IdGenerationRepo;
import com.sph.user.repo.UserRepo;
import com.sph.user.service.UserMapper;

class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepo userRepository;

	@Mock
	private IdGenerationRepo sequence;

	@Mock
	private UserMapper userMapper;

	@Spy
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateUser() {
	    UserRequestDto requestDto = new UserRequestDto();
	    requestDto.setEmail("test@example.com");
	    requestDto.setName("John Doe");
	    requestDto.setPassword("password123");

	    User userEntity = new User();
	    userEntity.setEmail(requestDto.getEmail());
	    userEntity.setName(requestDto.getName());

	    User savedUser = new User();
	    savedUser.setUserId("SPHU001");
	    savedUser.setEmail(requestDto.getEmail());
	    savedUser.setName(requestDto.getName());

	    UserResponseDto responseDto = new UserResponseDto();
	    responseDto.setUserId("SPHU001");
	    responseDto.setEmail(requestDto.getEmail());
	    responseDto.setName(requestDto.getName());

	    when(userMapper.toEntity(requestDto)).thenReturn(userEntity);
	    when(userRepository.save(any(User.class))).thenReturn(savedUser);
	    when(userMapper.toDto(savedUser)).thenReturn(responseDto);

	    var response = userService.createUser(requestDto);

	    assertNotNull(response);
	    assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	    assertEquals("User created successfully", response.getMessage());
	    assertEquals("SPHU001", ((UserResponseDto) response.getData()).getUserId());
	}


	@Test
	void testGetUserById_Found() {
	    String userId = "SPHU001";
	    User user = new User();
	    user.setUserId(userId);

	    UserResponseDto responseDto = new UserResponseDto();
	    responseDto.setUserId(userId);

	    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
	    when(userMapper.toDto(user)).thenReturn(responseDto);

	    UserResponseDto result = userService.getUserById(userId);

	    assertNotNull(result);
	    assertEquals(userId, result.getUserId());
	}

	@Test
	void testGetUserById_NotFound() {
	    String userId = "SPHU999";
	    when(userRepository.findById(userId)).thenReturn(Optional.empty());

	    assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
	}


	@Test
	void testGetAllUsers() {
	    User user1 = new User();
	    user1.setUserId("SPHU001");
	    User user2 = new User();
	    user2.setUserId("SPHU002");

	    List<User> users = Arrays.asList(user1, user2);
	    List<UserResponseDto> dtos = Arrays.asList(new UserResponseDto(), new UserResponseDto());

	    when(userRepository.findAll()).thenReturn(users);
	    when(userMapper.toDtoList(users)).thenReturn(dtos);

	    List<UserResponseDto> result = userService.getAllUsers();

	    assertEquals(2, result.size());
	    verify(userRepository, times(1)).findAll();
	}

	@Test
	void testUpdateUser() {
	    String userId = "SPHU001";

	    UserRequestDto requestDto = new UserRequestDto();
	    requestDto.setEmail("newemail@example.com");

	    User user = new User();
	    user.setUserId(userId);
	    user.setEmail("oldemail@example.com");

	    UserResponseDto responseDto = new UserResponseDto();
	    responseDto.setUserId(userId);
	    responseDto.setEmail("newemail@example.com");

	    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
	    when(userMapper.toDto(user)).thenReturn(responseDto);

	    UserResponseDto result = userService.updateUser(userId, requestDto);

	    assertEquals("newemail@example.com", result.getEmail());
	}

	@Test
	void testDeleteUser() {
	    String userId = "SPHU001";
	    User user = new User();
	    user.setUserId(userId);

	    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
	    doNothing().when(userRepository).delete(user);

	    var response = userService.deleteUser(userId);

	    assertEquals(HttpStatus.OK.value(), response.getStatus());
	    assertEquals("User deleted successfully", response.getMessage());
	}

}
