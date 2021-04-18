package org.ecomm.ecommitemservice.web;

import org.ecomm.ecommitemservice.dto.ItemPayload;
import org.ecomm.ecommitemservice.service.ItemService;
import org.ecomm.foundation.api.AppLogger;
import org.ecomm.foundation.api.AppRequest;
import org.ecomm.foundation.model.Item;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemServiceController {

    @AppLogger
    Logger logger;

    private ItemService itemService;

    @Inject
    public ItemServiceController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/item/{itemCode}")
    public @ResponseBody
    ResponseEntity<Item> getItem(@PathVariable("itemCode") String itemCode) {
        AppRequest<Item> itemRequest = new AppRequest<>(new Item());
        try {
            assert (itemCode != null);
            logger.info("Going to find items for {}", itemCode);
            Item item = itemService.findItemByItemCode(itemCode);
            ResponseEntity<Item> responseEntity =
                    new ResponseEntity<>(item, HttpStatus.OK);
            return responseEntity;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<Item>(new Item(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addItem")
    public ResponseEntity<Item> addItem(@RequestBody ItemPayload itemPayload) {

        AppRequest<Item> addItemRequest = new AppRequest<>(new Item());
        try {
            logger.info("adding a new Item orders ");
            Item item = itemService.saveItem(itemPayload);
            ResponseEntity<Item> responseEntity =
                    new ResponseEntity<>(item, HttpStatus.OK);
            return responseEntity;
        } catch (Exception ex) {
            return new ResponseEntity<Item>(new Item(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/allItems")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Item>> findAllItemsById() {
        try {
            logger.info("retrieving items for set of item codes ");
            List<Item> items = itemService.getItemRepository().findAll();
            ResponseEntity<List<Item>> responseEntity =
                    new ResponseEntity<>(items, HttpStatus.OK);
            return responseEntity;
        } catch (Exception ex) {
            return new ResponseEntity<List<Item>>
                    (new ArrayList<Item>(), HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/allItemsByCode")
    public ResponseEntity<List<Item>> findAllItemsByCode(@RequestBody List<String> codes) {
        try {
            logger.info("retrieving items for set of item codes ");
            List<Item> items = itemService.getItemRepository().findAllByCode(codes);
            ResponseEntity<List<Item>> responseEntity =
                    new ResponseEntity<>(items, HttpStatus.OK);
            return responseEntity;
        } catch (Exception ex) {
            return new ResponseEntity<List<Item>>
                    (new ArrayList<Item>(), HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/updateItemQuantity")
    public ResponseEntity<String> updateItemQuantity(@RequestBody List<Item> items) {
        try {
            itemService.bulkUpdateItems(items, Item.PROP.QUANTITY);
            return new ResponseEntity<String>("Success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getStackTrace().toString(), HttpStatus.BAD_REQUEST);
        }
    }

}
