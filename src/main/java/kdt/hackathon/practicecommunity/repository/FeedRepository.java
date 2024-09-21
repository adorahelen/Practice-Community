package kdt.hackathon.practicecommunity.repository;

import kdt.hackathon.practicecommunity.entitiy.Category;
import kdt.hackathon.practicecommunity.entitiy.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    @Override
    ArrayList<Feed> findAll(); // -> 오버라이딩을 통해, 반환하는 값을 Iterable 에서 ArrayList 로 수정

    // Find a feed by title
    Optional<Feed> findByTitle(String title);
}

