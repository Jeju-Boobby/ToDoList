package kr.ac.jejunu.repository;

import kr.ac.jejunu.model.ToDo;
import kr.ac.jejunu.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Boobby on 17-6-9.
 */
public interface ToDoRepository extends CrudRepository<ToDo, Long>{

    List<ToDo> findAllByUser(User user);
}
