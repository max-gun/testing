package com.enterprise.testings.service;

import com.enterprise.testings.entities.Item;

import java.util.List;

/**
 * @author max.gun
 * @since 22/08/2024
 */
public interface ItemService {
    List<Item> getAllItems();

    Item saveItem(Item item);
}
