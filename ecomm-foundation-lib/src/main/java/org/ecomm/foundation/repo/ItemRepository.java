package org.ecomm.foundation.repo;

import org.ecomm.foundation.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Integer> {

    public Optional<Item> findItemByCode(String code);
}