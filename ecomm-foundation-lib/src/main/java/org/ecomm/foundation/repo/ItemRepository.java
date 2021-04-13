package org.ecomm.foundation.repo;

import org.ecomm.foundation.model.Item;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {

    public Optional<Item> findFirstByCode(String code);

    public default List<Item> findAllByCode(Iterable<String> codes) {
        List<Item> items = new ArrayList<>();
        codes.forEach(code ->{
            items.add(findFirstByCode(code).get());
        });
        return items;
    }
}