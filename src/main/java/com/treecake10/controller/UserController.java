package com.treecake10.controller;

import com.treecake10.model.User;
import com.treecake10.request.ItemRequest;
import com.treecake10.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/profile")
    public ResponseEntity<User> findUserByJwtToken(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity<String> likeCharacter(@RequestHeader("Authorization") String jwt, @RequestBody ItemRequest itemRequest) throws Exception {
        logger.debug("Received likeCharacter request. JWT: {}, itemId: {}, itemType: {}", jwt, itemRequest.getItemId(), itemRequest.getItemType(), itemRequest.getItemName());
        userService.addLikedItem(jwt, itemRequest.getItemId(), itemRequest.getItemType(), itemRequest.getItemName());
        return new ResponseEntity<>("Character liked successfully", HttpStatus.OK);
    }

    @GetMapping("/itemIsLiked")
    public ResponseEntity<Boolean> itemIsLiked(
            @RequestHeader("Authorization") String jwt,
            @RequestParam Long itemId,
            @RequestParam String itemType
    ) throws Exception {
        logger.debug("Received itemIsLiked request. JWT: {}, itemId: {}, itemType: {}", jwt, itemId, itemType);
        boolean isLiked = userService.itemIsLiked(jwt, itemId, itemType);
        return new ResponseEntity<>(isLiked, HttpStatus.OK);
    }

    @DeleteMapping("/unlike")
    public ResponseEntity<String> unlikeCharacter(@RequestHeader("Authorization") String jwt, @RequestBody ItemRequest itemRequest) throws Exception {
        logger.debug("Received unlikeCharacter request. JWT: {}, itemId: {}, itemType: {}", jwt, itemRequest.getItemId(), itemRequest.getItemType());
        userService.removeLikedItem(jwt, itemRequest.getItemId(), itemRequest.getItemType());
        return new ResponseEntity<>("Character unliked successfully", HttpStatus.OK);
    }

    @PostMapping("/favorite")
    public ResponseEntity<String> favoriteCharacter(@RequestHeader("Authorization") String jwt, @RequestBody ItemRequest itemRequest) throws Exception {
        logger.debug("Received favoriteCharacter request. JWT: {}, itemId: {}, itemType: {}, itemName: {}", jwt, itemRequest.getItemId(), itemRequest.getItemType(), itemRequest.getItemName());
        userService.addFavoritedItem(jwt, itemRequest.getItemId(), itemRequest.getItemType(), itemRequest.getItemName());
        return new ResponseEntity<>("Character favorited successfully", HttpStatus.OK);
    }

    @GetMapping("/itemIsFavorited")
    public ResponseEntity<Boolean> itemIsFavorited(@RequestHeader("Authorization") String jwt, @RequestParam Long itemId, @RequestParam String itemType) throws Exception {
        logger.debug("Received itemIsFavorited request. JWT: {}, itemId: {}, itemType: {}", jwt, itemId, itemType);
        boolean isFavorited = userService.itemIsFavorited(jwt, itemId, itemType);
        return new ResponseEntity<>(isFavorited, HttpStatus.OK);
    }

    @DeleteMapping("/unfavorite")
    public ResponseEntity<String> unfavoriteCharacter(@RequestHeader("Authorization") String jwt, @RequestBody ItemRequest itemRequest) throws Exception {
        logger.debug("Received unfavoriteCharacter request. JWT: {}, itemId: {}, itemType: {}", jwt, itemRequest.getItemId(), itemRequest.getItemType());
        userService.removeFavoritedItem(jwt, itemRequest.getItemId(), itemRequest.getItemType());
        return new ResponseEntity<>("Character unfavorited successfully", HttpStatus.OK);
    }

}
