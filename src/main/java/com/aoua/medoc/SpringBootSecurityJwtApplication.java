package com.aoua.medoc;
import com.aoua.medoc.repository.RoleRepository;
import com.aoua.medoc.repository.UserRepository;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;


@AllArgsConstructor
@SpringBootApplication @EnableScheduling
public class SpringBootSecurityJwtApplication implements CommandLineRunner {


	@Bean
	FirebaseMessaging firebaseMessaging() throws IOException {
		GoogleCredentials googleCredentials = GoogleCredentials.fromStream(
				new ClassPathResource("rappeltraitement-firebase-adminsdk-fb5jt-4a98979120.json").getInputStream()
		);
		FirebaseOptions firebaseOptions= FirebaseOptions.builder().setCredentials(googleCredentials).build();
		FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions,"my-app");
		return  FirebaseMessaging.getInstance(app);
	}
    @Autowired
    PasswordEncoder encoder;

    //le password que admin prendra lors de sa creation automatique


	@Autowired
    private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
    SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		 String password = "Aoua1414";
		if (roleRepository.findAll().size()==0){
			roleRepository.creationRoleAdmin();


		}

		if (userRepository.findAll().size()==0) {
			userRepository.adminConnect(encoder.encode(password));
			roleRepository.addRoleToUser();
		}
	}
}
