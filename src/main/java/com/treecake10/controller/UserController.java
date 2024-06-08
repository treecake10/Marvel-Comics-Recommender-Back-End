package com.treecake10.controller;

import com.treecake10.model.User;
import com.treecake10.request.LikedItemRequest;
import com.treecake10.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> findUserByJwtToken(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity<String> likeCharacter(@RequestHeader("Authorization") String jwt, @RequestBody LikedItemRequest likedItemRequest) throws Exception {
        userService.addLikedItem(jwt, likedItemRequest.getItemId(), likedItemRequest.getItemType());
        return new ResponseEntity<>("Character liked successfully", HttpStatus.OK);
    }
}
