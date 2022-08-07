package ru.multicarta.shopping.service.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.multicarta.shopping.entity.Item;
import ru.multicarta.shopping.entity.Purchase;
import ru.multicarta.shopping.repository.ItemRepository;
import ru.multicarta.shopping.service.ItemService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public List<Item> getAllItems() {
        log.info("Getting all items...");
        return itemRepository.findAll();
    }

    @Override
    public List<Item> getItemPage(Integer index, Integer offset) {
        log.info("Getting page with items. Index: {}, Offset: {}", index, offset);
        return itemRepository.findAll(PageRequest.of(index, offset, Sort.by("id"))).getContent();
    }

    @Override
    public Optional<Item> getItem(Long itemId) {
        return itemRepository.findById(itemId);
    }

    @Override
    public void saveUpdatedItem(Item item) {
        itemRepository.save(item);
    }

    @SneakyThrows
    @Override
    public Purchase getBestSellerItemStartingFromDate(LocalDate dateInPast) {
        // TODO: 06.08.2022 Correct this part before testing
        Object object = itemRepository.getBestSellerItemStartingFromDate(dateInPast);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("Result: " + mapper.writeValueAsString(object));
        return new Purchase();
    }
}
