package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.dto.ConditionSearch;
import ir.maktab.dto.UserDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class UserServiceTest {
    static UserService userService;

    @BeforeAll
    static void init() {
        userService = new AnnotationConfigApplicationContext(SpringConfig.class).getBean(UserService.class);

    }

    @Test
    void getUsersByCondition() {
        ConditionSearch condition = ConditionSearch.builder()
                .firstName("ali")
                .build();
        List<UserDto> userByCondition = userService.getUserByCondition(condition);
        System.out.println(userByCondition.size());
        userByCondition.forEach(System.out::println);
    }

}
