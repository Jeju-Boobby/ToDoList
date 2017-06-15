package kr.ac.jejunu.repository;

import kr.ac.jejunu.model.ToDo;
import kr.ac.jejunu.model.ToDoStatus;
import kr.ac.jejunu.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Boobby on 17-6-9.
 */
public interface ToDoRepository extends PagingAndSortingRepository<ToDo, Long> {

    List<ToDo> findAllByUserAndToDoStatus(User user, ToDoStatus toDoStatus, Sort sort);

    List<ToDo> findAllByUserAndToDoStatus(User user, ToDoStatus toDo);

    List<ToDo> findAllByUser(User user);
}
