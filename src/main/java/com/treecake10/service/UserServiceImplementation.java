package com.treecake10.service;

import com.treecake10.config.JwtProvider;
import com.treecake10.model.LikedItem;
import com.treecake10.model.User;
import com.treecake10.repository.LikedItemRepository;
import com.treecake10.repository.UserRepository;
import com.treecake10.request.LikedItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private LikedItemRepository likedItemRepository;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = findUserByEmail(email);
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);

        if(user == null) {
            throw new Exception("user not found");
        }
        return user;
    }

    public void addLikedItem(String jwt, Long itemId, String itemType) throws Exception {
        User user = findUserByJwtToken(jwt);
        LikedItem likedItem = new LikedItem();
        likedItem.setItemType(itemType);
        likedItem.setItemId(itemId);
        likedItem.setUser(user);
        likedItemRepository.save(likedItem);
    }
}
