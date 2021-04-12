package org.ecomm.ecommitemservice.service;

import org.ecomm.foundation.model.Item;
import org.ecomm.foundation.repo.ItemRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class CreateItemService {
//    @Inject
    private ItemRepository itemRepository;

    public Item saveItem(Item item){
        return itemRepository.save(item);
    }

    public Item findItemByItemCode(String code){
        return itemRepository.findItemByCode(code).orElse(null);
    }
}
