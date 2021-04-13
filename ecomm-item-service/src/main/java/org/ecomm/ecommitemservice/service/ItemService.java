package org.ecomm.ecommitemservice.service;

import org.ecomm.ecommitemservice.dto.ItemPayload;
import org.ecomm.foundation.model.Item;
import org.ecomm.foundation.repo.ItemRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Date;

@Named
public class ItemService {

    @Inject
    private ItemRepository itemRepository;

    public Item saveItem(ItemPayload item) {
        return itemRepository.save(getItem(item));
    }

    public Item findItemByItemCode(String code) {
        return itemRepository.findFirstByCode(code).orElse(new Item());
    }

    private Item getItem(ItemPayload payload) {
        Item item = new Item();
        item.setCode(payload.getCode())
                .setDescription(payload.getDescription())
                .setAvailQty(payload.getAvailQty())
                .setTags(payload.getTags())
                .setName(payload.getName())
                .setStartDate(new Date(System.currentTimeMillis()))
                .setEndDate(new Date(System.currentTimeMillis()))
                .setUnit(payload.getUnit())
                .setUnitPrice(payload.getUnitPrice());
        return item;
    }

    public ItemRepository getItemRepository() {
        return itemRepository;
    }
}
