package kdt.hackathon.practicecommunity.TestRepository;


import kdt.hackathon.practicecommunity.entitiy.Comment;
import kdt.hackathon.practicecommunity.entitiy.Feed;
import kdt.hackathon.practicecommunity.entitiy.User;
import kdt.hackathon.practicecommunity.repository.CommentRepository;
import kdt.hackathon.practicecommunity.repository.FeedRepository;
import kdt.hackathon.practicecommunity.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeedRepository feedRepository;

    @Test
    public void insertCommentTest() {
        User user = userRepository.findByEmail("test@example.com").orElseThrow();
        Feed feed = feedRepository.findByTitle("How to Learn Spring Boot").orElseThrow();

        Comment comment = Comment.builder()
                .content("This is a very helpful article!")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        comment.setUser(user); // Setting relationships
        comment.setFeed(feed);

        Comment savedComment = commentRepository.save(comment);

        assertThat(savedComment).isNotNull();
        assertThat(savedComment.getContent()).isEqualTo("This is a very helpful article!");
    }
}
