package br.com.cesarsicas.springstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringstoreApplication {

	public static void main(String[] args) {
		//System.out.println("Pass:" + new BCryptPasswordEncoder().encode("123456"));
		SpringApplication.run(SpringstoreApplication.class, args);
	}

}
