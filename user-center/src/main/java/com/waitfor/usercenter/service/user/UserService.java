package com.waitfor.usercenter.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waitfor.usercenter.dao.user.UserMapper;
import com.waitfor.usercenter.domain.entity.user.User;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

	private final UserMapper userMapper;
	
	public User findById(Integer id){
		return this.userMapper.selectByPrimaryKey(id);
	}
}
