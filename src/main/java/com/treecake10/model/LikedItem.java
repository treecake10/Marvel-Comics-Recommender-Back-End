package com.treecake10.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "liked_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_type")
    private String itemType;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_name")
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}

