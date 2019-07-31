package com.mainacad.dao;

import com.mainacad.model.Item;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemDAOTest {


    private static List<Item> items = new ArrayList<>();
    @BeforeAll
    static void setUp() {
        Item item = new Item( "test_itemCode", "test_name",23);
        items.add(item);
    }

    @AfterAll
    static void tearDown() {
        for (Item item : items) {
            if (item.getId() != null)
                ItemDAO.delete(item.getId());
        }
    }

    @Test
    void createAndFindAndDelete() {
        assertNull(items.get(0).getId());
        Item itemInDB = ItemDAO.create(items.get(0));

        assertNotNull(itemInDB);
        assertNotNull(itemInDB.getId());

        Item checkedItemInDB = ItemDAO.findById(itemInDB.getId());
        assertNotNull(checkedItemInDB);

        Item checkedItemInDBByLogin = ItemDAO.findByItemCode(itemInDB.getItemCode());
        assertNotNull(checkedItemInDBByLogin);

        Item checkedItemInDBByAll = ItemDAO.findAll();
        assertNotNull(checkedItemInDBByAll);

        ItemDAO.delete(checkedItemInDB.getId());
        Item deletedItem = ItemDAO.findById(itemInDB.getId());
        assertNull(deletedItem);
    }
}
