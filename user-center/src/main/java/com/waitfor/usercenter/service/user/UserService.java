package com.waitfor.usercenter.service.user;

import com.waitfor.usercenter.dao.bonus.BonusEventLogMapper;
import com.waitfor.usercenter.domain.dto.messaging.UserAddBonusMsgDTO;
import com.waitfor.usercenter.domain.entity.bonus.BonusEventLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waitfor.usercenter.dao.user.UserMapper;
import com.waitfor.usercenter.domain.entity.user.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserService {

	private final UserMapper userMapper;
	private final BonusEventLogMapper bonusEventLogMapper;

	public User findById(Integer id){
		return this.userMapper.selectByPrimaryKey(id);
	}

    @Transactional(rollbackFor = Exception.class)
	public void addBonus(UserAddBonusMsgDTO msgDTO){
		// 当收到消息的时候， 执行的业务
		// 1. 为用户加积分
		Integer userId = msgDTO.getUserId();
		User user = this.userMapper.selectByPrimaryKey(userId);
		Integer bonus = msgDTO.getBonus();
		user.setBonus(user.getBonus()+ bonus);
		this.userMapper.updateByPrimaryKey(user);

		// 2. 记录日志到bonus_event_log表里面
		this.bonusEventLogMapper.insert(
				BonusEventLog.builder()
						.userId(userId)
						.value(bonus)
						.event("CONTRIBUTE")
						.createTime(new Date())
						.description("投稿加积分..")
						.build()
		);
		log.info("积分添加完毕...");
	}
}
