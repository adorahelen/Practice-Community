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

    @Test
    @Transactional
    @DisplayName("findByUser")
    public void findByUserTest() {
        User user = userRepository.findByEmail("test@example.com").orElseThrow();
        List<Comment> comments = commentRepository.findByUser(user);
        comments.forEach(comment -> log.info(comment.toString()));
        assertThat(comments).isNotNull();
        assertThat(comments.size()).isEqualTo(2);
        // 이메일을 통해 유저를 알아내고, 유저를 바탕으로 작성한 모든 댓글을 찾아 조회한다
        // * 추가 할 사항으로는 댓글을 1번 피드 뿐만 아니라 2번, 3번 피드에도 작성하고 (유저 다르게)
            // 개별적인 피드에 무분별하게 달려있는 댓글들이 한번에 조회되는지를 테스트 하길 바람
    }

    // 책에서는 네이티브 쿼리 메서드 (@Query, orm.xml) 을 사용했지만, 여기서는 사용 X -> 자동 생성

}
