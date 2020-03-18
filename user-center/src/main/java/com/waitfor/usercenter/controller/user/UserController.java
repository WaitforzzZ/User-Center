package com.waitfor.usercenter.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waitfor.usercenter.domain.entity.user.User;
import com.waitfor.usercenter.service.user.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	@GetMapping("/{id}")
	public User findById(@PathVariable Integer id){
		return this.userService.findById(id);
	}
}
