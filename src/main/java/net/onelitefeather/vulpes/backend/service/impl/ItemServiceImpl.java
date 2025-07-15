package net.onelitefeather.vulpes.backend.service.impl;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import net.onelitefeather.vulpes.api.model.ItemEntity;
import net.onelitefeather.vulpes.api.repository.ItemRepository;
import net.onelitefeather.vulpes.backend.domain.item.ItemModelDTO;
import net.onelitefeather.vulpes.backend.domain.item.ItemModelResponseDTO;
import net.onelitefeather.vulpes.backend.service.ItemService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of the ItemService interface.
 */
@Singleton
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Inject
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemModelResponseDTO.ItemModelDTO createItem(ItemModelDTO itemModelDTO) {
        ItemEntity itemModel = itemModelDTO.toItemEntity();
        ItemEntity savedItemModel = itemRepository.save(itemModel);
        return ItemModelResponseDTO.ItemModelDTO.createDTO(savedItemModel);
    }

    @Override
    public ItemModelResponseDTO updateItem(ItemModelDTO itemModelDTO) {
        Optional<ItemEntity> existingItem = itemRepository.findById(itemModelDTO.getId());
        if (existingItem.isEmpty()) {
            return new ItemModelResponseDTO.ItemModelErrorDTO("Item not found");
        }
        ItemEntity itemModel = itemModelDTO.toItemEntity();
        ItemEntity updatedItemModel = itemRepository.update(itemModel);
        return ItemModelResponseDTO.ItemModelDTO.createDTO(updatedItemModel);
    }

    @Override
    public ItemModelResponseDTO deleteItem(UUID id) {
        Optional<ItemEntity> model = itemRepository.findById(id);
        if (model.isPresent()) {
            itemRepository.deleteById(id);
            return ItemModelResponseDTO.ItemModelDTO.createDTO(model.get());
        }
        return new ItemModelResponseDTO.ItemModelErrorDTO("Item not found");
    }

    @Override
    public List<ItemModelResponseDTO> deleteAllItems() {
        itemRepository.deleteAll();
        return List.of();
    }

    @Override
    public Page<ItemModelResponseDTO.ItemModelDTO> getAllItems(Pageable pageable) {
        return itemRepository.findAll(pageable).map(ItemModelResponseDTO.ItemModelDTO::createDTO);
    }

    @Override
    public Optional<ItemEntity> findItemById(UUID id) {
        return itemRepository.findById(id);
    }

    @Override
    public Map<String, Short> findEnchantmentsById(UUID id) {
        return itemRepository.findEnchantmentsById(id);
    }

    @Override
    public List<String> findFlagsById(UUID id) {
        return itemRepository.findFlagsById(id);
    }

    @Override
    public List<String> findLoreById(UUID id) {
        return itemRepository.findLoreById(id);
    }
}