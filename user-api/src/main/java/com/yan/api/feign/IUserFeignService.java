package com.yan.api.feign;

import com.yan.api.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;


@RequestMapping("/user/feign")
public interface IUserFeignService {

    @PostMapping(value = "/save")
    User save(@RequestBody User user);

    @GetMapping(value = "/findAll")
    Collection<User> findAll();
}
