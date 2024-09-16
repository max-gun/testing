package com.enterprise.testings.service.impl;


import com.enterprise.testings.entities.Item;
import com.enterprise.testings.exceptions.ItemNullException;
import com.enterprise.testings.repository.ItemRepository;
import com.enterprise.testings.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

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

    @Override
    public Item getItem(String name) {
        if (isNull(name) || name.isEmpty() || name.isBlank()) {
            throw new ItemNullException("Input cannot be null");
        }
        return repository.findByItemName(name).orElse(null);
    }
}
