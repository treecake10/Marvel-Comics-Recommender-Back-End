package com.treecake10.request;

import lombok.Data;

@Data
public class ItemRequest {
    private String itemType;

    private String itemName;
    private Long itemId;

    public String getItemType() {
        return itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public Long getItemId() {
        return itemId;
    }
}