package net.theevilreaper.vulpes.backend.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.annotation.Controller;
import net.theevilreaper.vulpes.api.model.ItemModel;
import net.theevilreaper.vulpes.api.repository.ItemRepository;
import jakarta.inject.Inject;

import java.util.List;

/**
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller("/item")
public class ItemController {

    private final ItemRepository itemRepository;

    @Inject
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Post
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<ItemModel> add(@Body ItemModel item) {
        return itemRepository.add(item);
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<ItemModel> getById(@PathVariable String id) {
        return itemRepository.getByID(id);
    }

    @Delete("/remove/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<ItemModel> remove(@PathVariable String id) {
        return itemRepository.delete(id);
    }

    @Get("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<List<ItemModel>> getAll() {
        return itemRepository.getAll();
    }

    @Delete("/deleteAll")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<List<ItemModel>> deleteAll() {
        return itemRepository.deleteAll();
    }

    @Post("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<ItemModel> update(@Body ItemModel model) {
        return itemRepository.update(model);
    }
}