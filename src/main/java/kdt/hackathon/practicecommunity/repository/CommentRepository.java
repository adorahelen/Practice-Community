package kdt.hackathon.practicecommunity.repository;

import kdt.hackathon.practicecommunity.entitiy.Comment;
import kdt.hackathon.practicecommunity.entitiy.Feed;
import kdt.hackathon.practicecommunity.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 단순한 CRUD 작업이 아니라, 페이지 처리와 정렬 작업시 JpaRepo 사용을 권장
    // 1. Find all comments for a specific feed (feedId)
    List<Comment> findByFeed(Feed feed);

    // 2. Find all comments by a specific user (userId)
    List<Comment> findByUser(User user);

    // Find comments by content (or part of content)
    List<Comment> findByContentContaining(String keyword);

}
