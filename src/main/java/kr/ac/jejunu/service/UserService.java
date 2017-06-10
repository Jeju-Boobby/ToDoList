package kr.ac.jejunu.service;

import kr.ac.jejunu.model.User;
import kr.ac.jejunu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.AccessDeniedException;

/**
 * Created by Boobby on 17-6-8.
 */
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        if (userRepository.findById(user.getId()) != null) {
            throw new DuplicateKeyException("이미 사용중인 아이디입니다.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User findUser(String id) {
        User user = userRepository.findById(id);
        return user;
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    /**
     * user의 Id와 Password는 인증용
     * Name과 직업만 변경가능
     * @param user
     */
    public void update(User user) throws AccessDeniedException {
        User oldUser = userRepository.findById(user.getId());
        String encodedPassword = oldUser.getPassword();

        boolean check = passwordEncoder.matches(user.getPassword(), encodedPassword);

        if (check == false) {
            throw new AccessDeniedException("비밀번호가 틀립니다.");
        }

        user.setUser_no(oldUser.getUser_no());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
