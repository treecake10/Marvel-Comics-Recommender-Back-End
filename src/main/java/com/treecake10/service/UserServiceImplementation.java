package com.treecake10.service;

import com.treecake10.config.JwtProvider;
import com.treecake10.model.Item;
import com.treecake10.model.User;
import com.treecake10.repository.ItemRepository;
import com.treecake10.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private ItemRepository itemRepository;

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

    public void addLikedItem(String jwt, Long itemId, String itemType, String itemName) throws Exception {
        User user = findUserByJwtToken(jwt);
        Item item = new Item();
        item.setItemType(itemType);
        item.setItemId(itemId);
        item.setItemName(itemName);
        item.setUser(user);
        itemRepository.save(item);
    }

    public boolean itemIsLiked(String jwt, Long itemId, String itemType) throws Exception {
        User user = findUserByJwtToken(jwt);
        Item item = itemRepository.findByUserAndItemIdAndItemType(user, itemId, itemType);
        return item != null;
    }

    public void removeLikedItem(String jwt, Long itemId, String itemType) throws Exception {
        User user = findUserByJwtToken(jwt);
        Item item = itemRepository.findByUserAndItemIdAndItemType(user, itemId, itemType);

        if(item != null) {
            itemRepository.delete(item);
        } else {
            throw new Exception("Item not found");
        }
    }

}
