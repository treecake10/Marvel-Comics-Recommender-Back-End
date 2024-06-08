package com.treecake10.service;

import com.treecake10.model.User;
import com.treecake10.request.LikedItemRequest;

public interface UserService {

    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;

    void addLikedItem(String jwt, Long itemId, String itemType) throws Exception;
}
