package com.waitfor.usercenter.rocketmq;

import com.waitfor.usercenter.dao.bonus.BonusEventLogMapper;
import com.waitfor.usercenter.dao.user.UserMapper;
import com.waitfor.usercenter.domain.dto.messaging.UserAddBonusMsgDTO;
import com.waitfor.usercenter.domain.entity.bonus.BonusEventLog;
import com.waitfor.usercenter.domain.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RocketMQMessageListener(consumerGroup = "consumer-group", topic = "add-bonus")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AddBonusListener implements RocketMQListener<UserAddBonusMsgDTO> {
    private final UserMapper userMapper;
    private final BonusEventLogMapper bonusEventLogMapper;
    @Override
    public void onMessage(UserAddBonusMsgDTO message) {
        // 当收到消息的时候， 执行的业务
        // 1. 为用户加积分
        Integer userId = message.getUserId();
        User user = this.userMapper.selectByPrimaryKey(userId);
        Integer bonus = message.getBonus();
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
