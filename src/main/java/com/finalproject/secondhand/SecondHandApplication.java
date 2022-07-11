package com.finalproject.secondhand;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@SecurityScheme(name = "Authorization", description = "Isi value dengan ketik 'Bearer(spasi)accessToken' dari login", scheme = "Basic", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER)
public class SecondHandApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondHandApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
