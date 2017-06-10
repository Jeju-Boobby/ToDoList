package kr.ac.jejunu.service;

import kr.ac.jejunu.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.nio.file.AccessDeniedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Boobby on 17-6-8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void 저장하고읽기() throws AccessDeniedException {
        // Create, Read Test
        User user = new User();

        String id = "Boobby";
        String password = "1234";
        String name = "부은형";
        String job = "학생";

        user.setId(id);
        user.setPassword(password);
        user.setName(name);
        user.setJob(job);

        userService.saveUser(user);

        User savedUser = userService.findUser(id);

        assertThat(savedUser.getId(), is(id));
        assertThat(passwordEncoder.matches(password, savedUser.getPassword()), is(true));
        assertThat(savedUser.getName(), is(name));
        assertThat(savedUser.getJob(), is(job));


        // Delete Test
//        userService.deleteUser(changedId);
//
//        User deletedUser = userService.findUser(changedId);
//
//        assertThat(deletedUser, nullValue());
    }

    @Test
    public void 수정하기() throws AccessDeniedException {
        User user = new User();

        String id = "Boobby";
        String password = "1234";
        String name = "부은형";
        String job = "학생";

        user.setId(id);
        user.setPassword(password);
        user.setName(name);
        user.setJob(job);


        userService.saveUser(user);

        User savedUser = new User();

        String changedId = "Boobby";
        String changedPassword = "1234";
        String changedName = "부부부";
        String changedJob = "교수";

        savedUser.setId(changedId);
        savedUser.setPassword(changedPassword);
        savedUser.setName(changedName);
        savedUser.setJob(changedJob);

        userService.update(savedUser);

        User changedUser = userService.findUser(changedId);

        assertThat(changedUser.getId(), is(changedId));
        assertThat(passwordEncoder.matches(changedPassword, changedUser.getPassword()), is(true));
        assertThat(changedUser.getName(), is(changedName));
        assertThat(changedUser.getJob(), is(changedJob));
    }
}