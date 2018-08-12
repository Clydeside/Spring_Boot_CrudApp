package com.example.hello;

import com.example.hello.domain.Item;
import com.example.hello.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ItemsController {
    @Autowired
    private ItemRepository itemsRepository;

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Item> items = itemsRepository.findAll();
        model.put("items", items);
        return "main";
    }

    @PostMapping
    public String add(@RequestParam String name, @RequestParam String category, @RequestParam String description, Map<String, Object> model) {
        Item item = new Item(name, category, description);
        itemsRepository.save(item);
        Iterable<Item> items = itemsRepository.findAll();
        model.put("items", items);
        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String name, Map<String, Object> model) {
        Iterable<Item> items;
        if (name != null && !name.isEmpty()) {
            items = itemsRepository.findByName(name);
        } else {
            items = itemsRepository.findAll();
        }
        model.put("items", items);
        return "main";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id, Map<String, Object> model) {
        itemsRepository.deleteById(id);
        model.put("items", itemsRepository.findAll());
        return "redirect:/";
    }
}
