package kdt.hackathon.practicecommunity.repository;

import kdt.hackathon.practicecommunity.entitiy.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
