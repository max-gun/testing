package com.enterprise.testings.service;

import com.enterprise.testings.entities.Item;
import com.enterprise.testings.exceptions.ItemNullException;
import com.enterprise.testings.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author max.gun
 * @since 12/09/2024
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class ItemServiceTest {

    @MockBean
    private ItemRepository repository;

    @Autowired
    private ItemService service;

    @Test
    public void testFindItemByName() {
        String itemName = "test-name";
        Item mockItem = new Item(1L, "test-name", 17);

        when(repository.findByItemName(itemName)).thenReturn(Optional.of(mockItem));

        Item result = service.getItem(itemName);

        assertNotNull(result);
        assertEquals(itemName, result.getName());
        verify(repository, times(1)).findByItemName(itemName);
    }

    @Test
    public void whenInputNull_thenThrowsItemNullException_approach_1() {
        String name = null;
        try {
            service.getItem(name);
            fail("Excpected ItemNullException to be thrown");
        } catch (ItemNullException ex) {
            assertEquals("Input cannot be null" , ex.getMessage());
        }
    }

    @Test
    public void whenInputNull_thenThrowsItemNullException_approach_2() {
        String name = null;

        ItemNullException exception =
                assertThrows(ItemNullException.class, () -> service.getItem(name));

        assertEquals("Input cannot be null" , exception.getMessage());
    }

    @Test
    public void whenInputNull_thenThrowsItemNullException_approach_3() {
        assertThrows(ItemNullException.class, () -> service.getItem(null));
    }
}
