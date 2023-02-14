package com.ourstocks.jwtapp.rest;

import com.ourstocks.jwtapp.dto.usersDTO.UserDTO;
import com.ourstocks.jwtapp.model.User;
import com.ourstocks.jwtapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserRestControllerV1 {
    private final UserService userService;

    @Autowired
    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDTO result = UserDTO.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUserBy(@RequestBody UserDTO userDTO){
        User user = userService.findByUsername(userDTO.getUsername());
        user = userDTO.toUser(user);
        userService.update(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }




}
