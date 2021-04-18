package org.ecomm.ecommitemservice.service;

import org.ecomm.ecommitemservice.dto.ItemPayload;
import org.ecomm.foundation.model.Item;
import org.ecomm.foundation.repo.ItemRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

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

    @Transactional(Transactional.TxType.SUPPORTS)
    public void updateItemQuantity(Item item) {
        itemRepository.findFirstByCode(item.getCode()).map(element -> {
            element.setAvailQty(element.getAvailQty().subtract(item.getAvailQty()));
            itemRepository.save(element);
            return true;
        }).orElse(false);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void bulkUpdateItems(List<Item> items, Item.PROP prop) throws Exception {

        for (Item item : items) {
            updateItemProp(item, prop);
        }

    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public Item updateItemProp(Item item, Item.PROP props) throws Exception {
        switch (props) {
            case NAME:
                return updateName(item);
            case QUANTITY:
                return updateQuantity(item);
            default:
                return item;
        }
    }

    private Item updateName(Item item) {
        return item;
    }

    private Item updateQuantity(Item item) throws Exception {
        if (item.getAvailQty().intValue() == 0)
            throw new Exception("invalid update Quantity");
        Item dbItem = itemRepository.findFirstByCode(item.getCode())
                .orElseThrow(() -> new Exception("ItemCode Not Found: " + item.getCode()));
        if(dbItem.getAvailQty().add(item.getAvailQty()).intValue() < 0)
            throw new Exception("Invalid update requested for " + item.getCode() +
                    ": current = " + dbItem.getAvailQty() + ", requested: " + item.getAvailQty());
        else{
            dbItem.setAvailQty(dbItem.getAvailQty().add(item.getAvailQty()));
            return itemRepository.save(dbItem);
        }
    }
}
