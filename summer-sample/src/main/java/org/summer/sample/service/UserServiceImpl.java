package org.summer.sample.service;

import org.summer.container.annotation.Component;
import org.summer.sample.entity.User;

/**
 * @author 钟宝林
 * @date 2018-11-05 16:30
 **/
@Component
public class UserServiceImpl implements UserService {
    @Override
    public User getUser() {
        User user = new User();
        user.setId("id");
        user.setName("name");
        return user;
    }
}
