package com.ticket_reservation.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ticket_reservation.entity.User;
import com.ticket_reservation.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // 테스트용 사용자 데이터 추가
        if (userRepository.count() == 0) {
            // abc@abc.com 이메일, 1234 비밀번호로 사용자 추가
            User user = new User();
            user.setEmail("abc@abc.com");
            user.setUsername("abc");
            user.setPassword(passwordEncoder.encode("1234")); // 비밀번호 암호화
            user.setRole("ROLE_USER");

            userRepository.save(user);
        }
    }
}
