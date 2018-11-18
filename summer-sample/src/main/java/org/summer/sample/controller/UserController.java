package org.summer.sample.controller;

import org.summer.container.annotation.Component;
import org.summer.sample.entity.User;
import org.summer.sample.service.UserService;

import javax.annotation.Resource;

/**
 * @author 钟宝林
 * @date 2018-11-05 16:48
 **/
@Component
public class UserController {

    @Resource
    private UserService userService;

    public User getUser() {
        return userService.getUser();
    }

}
