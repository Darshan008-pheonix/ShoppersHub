package com.sph.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sph.user.dto.UserDto;
import com.sph.user.entity.User;
import com.sph.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	// Create User
    @PostMapping
    public User createUser(@RequestBody UserDto dto) {
        return userService.createUser(dto);
    }

    // Get User
    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    // Get User by Email (Auth Service)
    @GetMapping("/email/{email}")
    public User getByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    // Update User
//    @PutMapping("/{id}")
//    public void updateUser(@PathVariable String id, @RequestBody UserDto dto) {
//        userService.updateUser(id, dto);
//    }

}
