package com.assia.controller;

import com.assia.domain.user.PagingObject;
import com.assia.exception.NotFoundException;
import com.assia.model.user.UserForm;
import com.assia.model.user.UserModel;
import com.assia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

@RestController
@RequestMapping(AbstractController.API +"/users")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping
    public PagingObject<UserModel> getAllUsers(Pageable pageable,
                                               @RequestParam(required = false, defaultValue = "") String username,
                                               @RequestParam(required = false, defaultValue = "") String fullname) {
        return userService.getAllUsers(pageable, username, fullname);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserModel getById(@PathVariable("id") BigInteger id) {
        return this.userService.getById(id).map(com.assia.domain.user.User::toUser).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") BigInteger id) {
        this.userService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserModel update(@PathVariable("id") BigInteger id, @Valid @RequestBody UserForm userForm) {
        return this.userService.update(id, userForm).map(com.assia.domain.user.User::toUser).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserModel insert(@Valid @RequestBody UserForm userForm) {
        return this.userService.create(userForm).toUser();
    }
}
