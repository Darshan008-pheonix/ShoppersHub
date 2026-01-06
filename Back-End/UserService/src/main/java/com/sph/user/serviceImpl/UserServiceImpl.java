package com.sph.user.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sph.user.dto.UserRequestDto;
import com.sph.user.dto.UserResponseDto;
import com.sph.user.entity.AccountStatus;
import com.sph.user.entity.IdGeneration;
import com.sph.user.entity.User;
import com.sph.user.exception.UserNotFoundException;
import com.sph.user.repo.IdGenerationRepo;
import com.sph.user.repo.UserRepo;
import com.sph.user.service.UserMapper;
import com.sph.user.service.UserService;
import com.sph.util.dto.ResponseDto;
import com.sph.util.service.CommonUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepository;

	@Autowired
	IdGenerationRepo sequence;
	
	@Autowired
	UserMapper userMapper;

	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	//private static ModelMapper mapper = new ModelMapper();

	private String generateId() {
		IdGeneration gen = new IdGeneration();
		sequence.save(gen);

		if (gen.getId() < 10) {
			return ("SPHU00" + gen.getId());
		} 
		else if (gen.getId() < 100) {
			return ("SPHU0" + gen.getId());
		} 
		else {
			return ("SPHU" + gen.getId());
		}
	}

	@Override
	public ResponseDto<Object> createUser(UserRequestDto userRequest) {
		User user = userMapper.toEntity(userRequest);
		
		user.setUserId(generateId());
		user.setStatus(AccountStatus.ACTIVE); 		    
		user.setActive(true);
		user.setPassword(encoder.encode(userRequest.getPassword()));
		
		User savedUser = userRepository.save(user);
		UserResponseDto responseData = userMapper.toDto(savedUser);
		return CommonUtils.prepareResponse("User created successfully", responseData, HttpStatus.CREATED.value());
	}

	@Override
	public UserResponseDto getUserById(String id) {
		return userMapper.toDto(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found")));
	}

	@Override
	public List<UserResponseDto> getAllUsers() {
		return userMapper.toDtoList(userRepository.findAll());
	}

	@Override
	public UserResponseDto updateUser(String id, UserRequestDto request) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

		//user.getName(request.setName());

		user.setEmail(request.getEmail());

		return userMapper.toDto(user);
	}

	@Override
	public ResponseDto<Object> deleteUser(String id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

		userRepository.delete(user);
		return CommonUtils.prepareResponse("User deleted successfully", null, HttpStatus.OK.value());

	}
	
	
}