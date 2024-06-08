package com.treecake10.request;

import lombok.Data;

@Data
public class LikedItemRequest {
    private String itemType;
    private Long itemId;

    public String getItemType() {
        return itemType;
    }

    public Long getItemId() {
        return itemId;
    }
}