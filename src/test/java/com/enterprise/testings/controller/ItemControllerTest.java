package com.enterprise.testings.controller;

import com.enterprise.testings.entities.Item;
import com.enterprise.testings.service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author max.gun
 * @since 12/09/2024
 */
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllItemsTest() throws Exception {
        List<Item> mockItems =
                List.of(
                        new Item(1L, "Item1", 17),
                        new Item(2L, "Item2", 27));

        when(service.getAllItems()).thenReturn(mockItems);

        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Item1"))
                .andExpect(jsonPath("$[1].name").value("Item2"));
    }

    @Test
    public void createItemTest() throws Exception {
        Item newItem = new Item(null, "NewItemName", 17);
        Item savedItem = new Item(3L, "NewItemName", 17);

        when(service.saveItem(any(Item.class))).thenReturn(savedItem);

        String newItemJson = objectMapper.writeValueAsString(newItem);

        mockMvc.perform(post("/items")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(newItemJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.name").value("NewItemName"));
    }
}
