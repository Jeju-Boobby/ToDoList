package kr.ac.jejunu.service;

import kr.ac.jejunu.model.ToDo;
import kr.ac.jejunu.model.User;
import kr.ac.jejunu.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Boobby on 17-6-9.
 */
@Service
@Transactional
public class ToDoService {
    @Autowired
    private ToDoRepository toDoRepository;


    public ToDo saveToDo(ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    public List<ToDo> getUsersToDoList(User user) {
        return toDoRepository.findAllByUser(user);
    }

    public void deleteToDo(Long toDoNo) {
        toDoRepository.delete(toDoNo);
    }

    public ToDo getToDo(Long toDoNo) {
        return toDoRepository.findOne(toDoNo);
    }
}
