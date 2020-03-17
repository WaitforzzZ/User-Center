package com.waitfor.usercenter;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LombokTest {

//	public static final Logger logger = LoggerFactory.getLogger(LombokTest.class); 
	public static void main(String[] args) {
		//建造者模式
		UserRegisterDTO build = UserRegisterDTO.builder()
			.email("sas")
			.password("123")
			.confirmPassword("123")
			.agreement(true)
			.build();
		log.info("构造出来的UserRegisterDTO = {}", build);

	}

}
@Getter
@Setter
@Builder
class UserRegisterDTO{
	private String email;
	private String password;
	private String confirmPassword;
	private boolean agreement;
}