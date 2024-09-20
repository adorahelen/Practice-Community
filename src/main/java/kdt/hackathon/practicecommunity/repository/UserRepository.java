package kdt.hackathon.practicecommunity.repository;

import kdt.hackathon.practicecommunity.entitiy.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
