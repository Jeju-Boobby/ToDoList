package kr.ac.jejunu.security.model;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

/**
 * Created by Boobby on 17-5-15.
 */
public class LoginUserDetails extends User {
    private static final long serialVersionUID = 1L;

    private kr.ac.jejunu.model.User user;

    public LoginUserDetails(kr.ac.jejunu.model.User user) {
        super(user.getId(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getJob()));

        this.user = user;
    }

    public kr.ac.jejunu.model.User getUser() {
        return user;
    }
}
