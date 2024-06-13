package com.treecake10.service;

import com.treecake10.config.JwtProvider;
import com.treecake10.model.FavoritedItem;
import com.treecake10.model.LikedItem;
import com.treecake10.model.User;
import com.treecake10.repository.FavoritedItemRepository;
import com.treecake10.repository.LikedItemRepository;
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
    private LikedItemRepository likedItemRepository;

    @Autowired
    private FavoritedItemRepository favoritedItemRepository;

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
    @Override
    public void addLikedItem(String jwt, Long itemId, String itemType, String itemName) throws Exception {
        User user = findUserByJwtToken(jwt);
        LikedItem likedItem = new LikedItem();
        likedItem.setItemType(itemType);
        likedItem.setItemId(itemId);
        likedItem.setItemName(itemName);
        likedItem.setUser(user);
        likedItemRepository.save(likedItem);
    }
    @Override
    public boolean itemIsLiked(String jwt, Long itemId, String itemType) throws Exception {
        User user = findUserByJwtToken(jwt);
        LikedItem likedItem = likedItemRepository.findByUserAndItemIdAndItemType(user, itemId, itemType);
        return likedItem != null;
    }

    @Override
    public void removeLikedItem(String jwt, Long itemId, String itemType) throws Exception {
        User user = findUserByJwtToken(jwt);
        LikedItem likedItem = likedItemRepository.findByUserAndItemIdAndItemType(user, itemId, itemType);

        if(likedItem != null) {
            likedItemRepository.delete(likedItem);
        } else {
            throw new Exception("Item not found");
        }
    }

    @Override
    public void addFavoritedItem(String jwt, Long itemId, String itemType, String itemName) throws Exception {
        User user = findUserByJwtToken(jwt);
        FavoritedItem item = new FavoritedItem();
        item.setItemType(itemType);
        item.setItemId(itemId);
        item.setItemName(itemName);
        item.setUser(user);
        favoritedItemRepository.save(item);
    }

    @Override
    public boolean itemIsFavorited(String jwt, Long itemId, String itemType) throws Exception {
        User user = findUserByJwtToken(jwt);
        FavoritedItem item = favoritedItemRepository.findByUserAndItemIdAndItemType(user, itemId, itemType);
        return item != null;
    }

    @Override
    public void removeFavoritedItem(String jwt, Long itemId, String itemType) throws Exception {
        User user = findUserByJwtToken(jwt);
        FavoritedItem item = favoritedItemRepository.findByUserAndItemIdAndItemType(user, itemId, itemType);
        if (item != null) {
            favoritedItemRepository.delete(item);
        } else {
            throw new Exception("Item not found");
        }
    }

}
