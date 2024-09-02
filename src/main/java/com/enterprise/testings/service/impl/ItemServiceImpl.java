package com.enterprise.testings.service.impl;


import com.enterprise.testings.entities.Item;
import com.enterprise.testings.repository.ItemRepository;
import com.enterprise.testings.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository repository;

    @Override
    public List<Item> getAllItems() {
        return repository.findAll();
    }

    @Override
    public Item saveItem(Item item) {
        return repository.save(item);
    }
}
