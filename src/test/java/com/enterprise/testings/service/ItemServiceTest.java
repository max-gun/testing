package com.enterprise.testings.service;

import com.enterprise.testings.entities.Item;
import com.enterprise.testings.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    @Mock
    private ItemRepository repository;

    @Mock
    private ItemService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        List<Item> items = Arrays.asList(new Item("Item1", 10), new Item("Item2", 20));
        when(service.getAllItems()).thenReturn(items);
    }

    @Test
    @Disabled
    void getAllItems() {
        Item item1 = new Item();
        item1.setName("Item1");
        item1.setPrice(10);

        Item item2 = new Item();
        item2.setName("Item2");
        item2.setPrice(20);

        when(repository.findAll()).thenReturn(Arrays.asList(item1, item2));

        List<Item> items = service.getAllItems();

        assertEquals(2, items.size());
        verify(repository, times(1)).findAll();
    }
}
