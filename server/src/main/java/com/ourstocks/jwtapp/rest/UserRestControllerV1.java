package com.ourstocks.jwtapp.rest;

import com.ourstocks.jwtapp.dto.postsDTO.PostDTO;
import com.ourstocks.jwtapp.dto.usersDTO.UserDTO;
import com.ourstocks.jwtapp.model.Status;
import com.ourstocks.jwtapp.model.User;
import com.ourstocks.jwtapp.model.UserFollows;
import com.ourstocks.jwtapp.repository.UserFollowsRepository;
import com.ourstocks.jwtapp.security.jwt.JwtUser;
import com.ourstocks.jwtapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/users/")
@CrossOrigin
public class UserRestControllerV1 {
    private final UserService userService;
    private final UserFollowsRepository userFollowsRepository;

    @Autowired
    public UserRestControllerV1(UserService userService, UserFollowsRepository userFollowsRepository) {
        this.userService = userService;
        this.userFollowsRepository = userFollowsRepository;
    }

//    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Long id){
//        User user = userService.findById(id);
//        if(user == null){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        UserDTO result = UserDTO.fromUser(user);
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }//old version

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUserById(@PathVariable(name = "id") Long id) throws UsernameNotFoundException {
        User user = userService.findById(id);
        if(user == null){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            String message = "No user found";
            throw new UsernameNotFoundException(message);
        }
        UserDTO result = UserDTO.fromUser(user);
        Set<UserFollows> followsList = userFollowsRepository.findAllByDistributor(id);
        Set<UserFollows> distributorsList = userFollowsRepository.findAllBySubscriber(id);
        Map<Object,Object> response = new HashMap<>();
        response.put("user", result);
        response.put("followsList", getFollowList(followsList));
        response.put("askFollowsList", getAskFollowList(followsList));
        response.put("myDistributors",getDistributorList(distributorsList));
        response.put("userPosts",user.getPosts().stream().map(PostDTO::fromPost).collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }
    @RequestMapping(value = "{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUserBy(@RequestBody UserDTO userDTO){
        User user = userService.findByUsername(userDTO.getUsername());
        user = userDTO.toUser(user);
        userService.update(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/askfollow", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<UserFollows>> requestFollow(@PathVariable(name = "id") Long id,
                                                           @AuthenticationPrincipal JwtUser jwtUser){
        User observer = userService.findByUsername(jwtUser.getUsername());
        if(userService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserFollows userFollows = new UserFollows(id,observer.getId(),Status.NOT_ACTIVE);
        userFollowsRepository.save(userFollows);
        Set<UserFollows> updateFollowList = userFollowsRepository.findAllByDistributor(id);
        return new ResponseEntity<>(updateFollowList,HttpStatus.OK);
    }

    @PostMapping(value = "follow/{id}/no", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<UserFollows>> answerFollowNo(@PathVariable(name = "id") Long id,
                                                            @AuthenticationPrincipal JwtUser jwtUser) {
        User user = userService.findByUsername(jwtUser.getUsername());
        User observer = userService.findById(id);
        if(observer == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Set<UserFollows> userFollows = userFollowsRepository.findAllByDistributor(user.getId());
        for(UserFollows u : userFollows){
            if(u.getSubscriber().equals(observer.getId())) {
                u.setStatus(Status.DELETED);
                userFollowsRepository.save(u);
            }
        }
        return new ResponseEntity<>(userFollows,HttpStatus.OK);
    }
    @RequestMapping(value = "follow/{id}/yes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<UserFollows>> answerFollowNo(@PathVariable("id") long id,
                                                           @AuthenticationPrincipal JwtUser jwtUser){
        User user = userService.findByUsername(jwtUser.getUsername());
        User observer = userService.findById(id);
        if(observer == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Set<UserFollows> userFollows = userFollowsRepository.findAllByDistributor(user.getId());
        for(UserFollows u : userFollows){
            if(u.getSubscriber().equals(observer.getId())) {
                u.setStatus(Status.ACTIVE);
                userFollowsRepository.save(u);
            }
        }
        return new ResponseEntity<>(userFollows,HttpStatus.OK);
    }

    public Set<UserDTO> getAskFollowList(Set<UserFollows> followsList){
        Set<UserDTO> askFollowList = new HashSet<>();
        for(UserFollows u : followsList){
            if(u.getStatus() == Status.NOT_ACTIVE)
                askFollowList.add(UserDTO.fromUser(userService.findById(u.getSubscriber())));
        }
        return askFollowList;
    }

    public Set<UserDTO> getFollowList(Set<UserFollows> followsList){
        Set<UserDTO> followList = new HashSet<>();
        for(UserFollows u : followsList){
            if(u.getStatus() == Status.ACTIVE)
                followList.add(UserDTO.fromUser(userService.findById(u.getSubscriber())));
        }
        return followList;
    }

    public Set<UserDTO> getDistributorList(Set<UserFollows> distributorsList){
        Set<UserDTO> distributorList = new HashSet<>();
        for(UserFollows u : distributorsList){
            if(u.getStatus() == Status.ACTIVE)
                distributorList.add(UserDTO.fromUser(userService.findById(u.getDistributor())));
        }
        return distributorList;
    }

}
