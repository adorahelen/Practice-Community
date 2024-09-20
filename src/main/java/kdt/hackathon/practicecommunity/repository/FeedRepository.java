package kdt.hackathon.practicecommunity.repository;

import kdt.hackathon.practicecommunity.entitiy.Feed;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface FeedRepository extends CrudRepository<Feed, Long> {
    @Override
    ArrayList<Feed> findAll(); // -> 오버라이딩을 통해, 반환하는 값을 Iterable 에서 ArrayList 로 수정
}
