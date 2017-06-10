package kr.ac.jejunu.security.service;

import kr.ac.jejunu.model.User;
import kr.ac.jejunu.repository.UserRepository;
import kr.ac.jejunu.security.model.LoginUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Boobby on 17-5-15.
 */
@Service
@Transactional
public class LoginUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepository.findById(id);

        if (user == null) {
            throw new UsernameNotFoundException("User not found. Login failed.");
        }

        return new LoginUserDetails(user);
    }
}
