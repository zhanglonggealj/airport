package com.esop.airport.task;

import com.esop.airport.domain.model.TBasUser;
import com.esop.airport.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-12 15:39
 **/
@Component
public class UserTask {

    @Autowired
    UserService userService;

    @Async
    public void updateUser (TBasUser user) {
        int ret = userService.updataUser(user);
    }
}
