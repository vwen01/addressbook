package com.application.addressbook.controllers;

import com.application.addressbook.models.Friend;
import com.application.addressbook.models.User;
import com.application.addressbook.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "${resource.path}")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("user/add")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {

        if (user.getName() == null) {
            throw new IllegalArgumentException("Received null user name");
        }

        return ResponseEntity.ok(userService.addNewUser(user));
    }

    @GetMapping("user/address_book/{userId}")
    public ResponseEntity<Set<Friend>> getFriends(@PathVariable(value = "userId") String userId) {
        return ResponseEntity.ok(userService.getFriends(userId));
    }

    @PostMapping("user/address_book/{userId}")
    public ResponseEntity<User> addFriend(@PathVariable(value = "userId") String userId, @RequestBody Friend friend) {
        return ResponseEntity.ok(userService.addNewFriend(userId, friend));
    }

    @PostMapping("user/address_book/unique/{userId}")
    public ResponseEntity<Set<Friend>> getUniqueFriends(@PathVariable(value = "userId") String userId, @RequestBody Set<Friend> setOfFriends) {
        return ResponseEntity.ok(userService.getUniqueFriends(userId, setOfFriends));
    }

}
