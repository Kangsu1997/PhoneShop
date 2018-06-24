package com.assia.controller;

import com.assia.model.user.UserModel;
import com.assia.model.user.UserForm;
import com.assia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(AbstractController.API+"/registers")
public class RegisterController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserModel insert(@Valid @RequestBody UserForm userForm) {
        return this.userService.create(userForm).toUser();
    }
}
