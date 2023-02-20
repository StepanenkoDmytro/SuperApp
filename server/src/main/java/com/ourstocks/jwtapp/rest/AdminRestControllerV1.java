package com.ourstocks.jwtapp.rest;

import com.ourstocks.jwtapp.dto.usersDTO.AdminUserDto;
import com.ourstocks.jwtapp.dto.usersDTO.UserDTO;
import com.ourstocks.jwtapp.model.User;
import com.ourstocks.jwtapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

    private final UserService userService;

    @Autowired
    public AdminRestControllerV1(UserService userService) {
        this.userService = userService;
    }

//    public ResponseEntity<List<UserDTO>> getUserDTOs(){
//        List<UserDTO> listUsers = userService.getAll().stream().map(UserDTO::fromUser).collect(Collectors.toList());
//        return ResponseEntity<>(listUsers,HttpStatus.OK);
//    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = AdminUserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
