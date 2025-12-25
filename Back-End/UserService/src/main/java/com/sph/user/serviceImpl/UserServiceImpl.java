package com.sph.user.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sph.user.dto.UserDto;
import com.sph.user.entity.IdGeneration;
import com.sph.user.entity.User;
import com.sph.user.repo.IdGenerationRepo;
import com.sph.user.repo.UserRepo;
import com.sph.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo repo;

	@Autowired
	IdGenerationRepo sequence;

	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	private static ModelMapper mapper = new ModelMapper();

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
	public User createUser(UserDto dto) {

		User user = mapper.map(dto, User.class);

		String id = generateId();
		user.setId(id);
		user.setPasswordHash(encoder.encode(user.getPasswordHash()));

		User saved = repo.save(user);
		//UserDto data = mapper.map(saved, UserDto.class);
		return saved;
	}

	@Override
	public User getUser(String id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
	}

	@Override
	public User getUserByEmail(String email) {
		return repo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
	}

	@Override
	public void updateUser(String id, UserDto dto) {
//	 	User user = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
//        user.setName(dto);
//        user.setPhone(request.phone);
//        user.setUpdatedAt(LocalDateTime.now());
//        repo.save(user);
	}

	@Override
	public void blockUser(String id) {
		User user = getUser(id);
		user.setStatus("BLOCKED");
		repo.save(user);
	}

	@Override
	public void activateUser(String id) {
		User user = getUser(id);
		user.setStatus("ACTIVE");
		repo.save(user);
	}

}
