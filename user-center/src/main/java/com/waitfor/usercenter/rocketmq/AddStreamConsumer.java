package com.waitfor.usercenter.rocketmq;

import com.waitfor.usercenter.dao.user.UserMapper;
import com.waitfor.usercenter.domain.dto.messaging.UserAddBonusMsgDTO;
import com.waitfor.usercenter.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

@Slf4j
public class AddStreamConsumer {
    private final UserService userService;
    @StreamListener(Sink.INPUT)
    public void recieve(UserAddBonusMsgDTO message){
        this.userService.addBonus(message);
    }
}
