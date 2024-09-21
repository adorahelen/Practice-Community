package kdt.hackathon.practicecommunity.TestRepository;


import kdt.hackathon.practicecommunity.entitiy.Category;
import kdt.hackathon.practicecommunity.entitiy.Feed;
import kdt.hackathon.practicecommunity.entitiy.User;
import kdt.hackathon.practicecommunity.repository.FeedRepository;
import kdt.hackathon.practicecommunity.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FeedRepositoryTest {

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private UserRepository userRepository;




    @Test
    public void insertFeedTest() {
        User user = userRepository.findByEmail("test@example.com").orElseThrow();


        Feed feed = Feed.builder()
                .title("How to Learn Spring Boot")
                .content("Spring Boot is easy to learn if you follow the right tutorials.")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .category(Category.QuestionAndAnswer)
                .build();

        feed.setUser(user); // Setting relationships

        Feed savedFeed = feedRepository.save(feed);

        assertThat(savedFeed).isNotNull();
        assertThat(savedFeed.getTitle()).isEqualTo("How to Learn Spring Boot");
    }
}