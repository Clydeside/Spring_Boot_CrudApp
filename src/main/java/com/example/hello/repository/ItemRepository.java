package com.example.hello.repository;

import com.example.hello.domain.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findByName(String name);

    void deleteItemById(Long id);
}
