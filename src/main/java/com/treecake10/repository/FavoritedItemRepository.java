package com.treecake10.repository;

import com.treecake10.model.FavoritedItem;
import com.treecake10.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritedItemRepository extends JpaRepository<FavoritedItem, Long> {
    List<FavoritedItem> findByUser(User user);
    FavoritedItem findByUserAndItemIdAndItemType(User user, Long itemId, String itemType);
}
