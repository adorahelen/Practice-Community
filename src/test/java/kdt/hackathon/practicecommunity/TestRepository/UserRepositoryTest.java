package kdt.hackathon.practicecommunity.TestRepository;

import kdt.hackathon.practicecommunity.entitiy.User;
import kdt.hackathon.practicecommunity.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void insertUserTest() {
        User user = User.builder()
                .email("test@example.com")
                .password("password123")
                .name("John Doe")
                .phoneNumber("010-1234-5678")
                .birthDate("1990-01-01")
                .role("USER")
                .build();

        User savedUser = userRepository.save(user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getEmail()).isEqualTo("test@example.com");
    }
}