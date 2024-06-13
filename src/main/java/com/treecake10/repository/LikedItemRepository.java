package com.treecake10.repository;

import com.treecake10.model.LikedItem;
import com.treecake10.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikedItemRepository extends JpaRepository<LikedItem, Long> {
    List<LikedItem> findByUser(User user);
    LikedItem findByUserAndItemIdAndItemType(User user, Long itemId, String itemType);
}
