package kr.ac.jejunu.repository;

import kr.ac.jejunu.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Boobby on 17-6-8.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findById(String id);

    void deleteById(String id);
}
