package kdt.hackathon.practicecommunity.TestRepository;


import kdt.hackathon.practicecommunity.entitiy.Comment;
import kdt.hackathon.practicecommunity.entitiy.Feed;
import kdt.hackathon.practicecommunity.entitiy.User;
import kdt.hackathon.practicecommunity.repository.CommentRepository;
import kdt.hackathon.practicecommunity.repository.FeedRepository;
import kdt.hackathon.practicecommunity.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class CommentRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(CommentRepositoryTest.class);
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

    // List<Comment> findByFeedId(Long feedId);
    @Test
    @Transactional
    @DisplayName("findByFeedId")
    public void findByFeedIdTest() {
        Long feedId = 1L; // 1번 피드에 대한 댓글들 조회
        List<Comment> comments = commentRepository.findByFeedId(feedId);
     //   System.out.println(comments.toString());
        // 에러1. 지연로딩으로 엔티티를 설정했기에, @트랜잭션 붙여줘야함
        // 에러2. 엔티티 연관관계 매핑으로 인한, 순환참조(무한)발생 [feed <-> Comment] * .toString
                // => comment entity 에 @ToString(exclude = "feed")로 출력 제외
                // 순환참조 = avoid circular reference
        comments.forEach(comment -> log.info(comment.toString()));

        assertThat(comments).isNotNull();
        assertThat(comments.size()).isEqualTo(2); // 생성되어 있는 댓글은 2개


    }

}
