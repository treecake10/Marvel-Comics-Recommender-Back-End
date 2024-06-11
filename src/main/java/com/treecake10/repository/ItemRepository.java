package com.treecake10.repository;

import com.treecake10.model.Item;
import com.treecake10.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByUser(User user);
    Item findByUserAndItemIdAndItemType(User user, Long itemId, String itemType);
}
