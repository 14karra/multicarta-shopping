package ru.multicarta.shopping.service.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.multicarta.shopping.entity.Item;
import ru.multicarta.shopping.repository.ItemRepository;
import ru.multicarta.shopping.service.ItemService;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public List<Item> getItemPage(Integer index, Integer offset) {
        log.info("Getting page with items. Index: {}, Offset: {}", index, offset);
        return itemRepository.findAll(PageRequest.of(index, offset, Sort.by("id"))).getContent();
    }
}
