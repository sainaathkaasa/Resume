package com.ResumeProject.Resume;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Base64;

@SpringBootApplication
public class ResumeApplication {

	public static void main(String[] args) {

		//byte[] key = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();
		//System.out.println(Base64.getEncoder().encodeToString(key));

		//BCryptPasswordEncoder p = new BCryptPasswordEncoder();
		//String ep = p.encode("admin@123");
		//System.out.println(ep);

		SpringApplication.run(ResumeApplication.class, args);

	}

}
