package kr.ac.jejunu.service;

import kr.ac.jejunu.model.ToDo;
import kr.ac.jejunu.model.ToDoCategory;
import kr.ac.jejunu.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Boobby on 17-6-9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ToDoServiceTest {
    @Autowired
    private ToDoService toDoService;

    @Autowired
    private UserService userService;

    private User writer;

    @Before
    public void setup() {
        writer = new User();

        String id = "Boobby";
        String password = "1234";
        String name = "부은형";
        String job = "학생";

        writer.setId(id);
        writer.setPassword(password);
        writer.setName(name);
        writer.setJob(job);

        userService.saveUser(writer);
    }

    @Test
    public void saveToDoTest() {
        ToDo toDo = new ToDo();

        ToDoCategory category = ToDoCategory.APPOINTMENT;
        String title = "친구들과 거하게 한잔하기";
        Timestamp planTime = Timestamp.valueOf("2017-07-10 21:00:00");
        String text = "죽지말자";

        toDo.setUser(writer);
        toDo.setCategory(category);
        toDo.setTitle(title);
        toDo.setPlanTime(planTime);
        toDo.setText(text);

        ToDo savedToDo = toDoService.saveToDo(toDo);

        assertThat(savedToDo.getUser().getUser_no(), is(writer.getUser_no()));
        assertThat(savedToDo.getCategory(), is(category));
        assertThat(savedToDo.getTitle(), is(title));
        assertThat(savedToDo.getPlanTime(), is(planTime));
        assertThat(savedToDo.getText(), is(text));
    }

    @Test
    public void getUsersToDoListTest() {
        final int COUNT = 20;

        for (int i = 0; i < COUNT; i++) {
            ToDo toDo = new ToDo();

            ToDoCategory category = ToDoCategory.APPOINTMENT;
            String title = (i + 1) + "번";
            Timestamp planTime = Timestamp.valueOf("2017-07-10 21:" + i + ":00");
            String text = "가나다라 " + i;

            toDo.setUser(writer);
            toDo.setCategory(category);
            toDo.setTitle(title);
            toDo.setPlanTime(planTime);
            toDo.setText(text);

            toDoService.saveToDo(toDo);
        }

        List<ToDo> toDoList = toDoService.getUsersToDoList(writer);
        for (ToDo toDo : toDoList) {
            System.out.println(toDo.getTitle());
        }

        assertThat(toDoList.size(), is(COUNT));
    }

}