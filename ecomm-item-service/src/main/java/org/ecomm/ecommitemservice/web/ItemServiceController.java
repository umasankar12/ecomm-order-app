package org.ecomm.ecommitemservice.web;

import org.ecomm.ecommitemservice.service.CreateItemService;
import org.ecomm.foundation.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemServiceController {
    Logger logger = LoggerFactory.getLogger(ItemServiceController.class);

    @Autowired
    private CreateItemService createItemService;

    @GetMapping("/item/{itemId}")
    public Item getItem(@PathVariable("itemId") String itemId){
        logger.info("Item id is "+ itemId);
        return createItemService.findItemByItemCode(itemId);
    }

    @PostMapping("/addItem")
    public Item addItem(@RequestBody Item item){
        return createItemService.saveItem(item);
    }
}
