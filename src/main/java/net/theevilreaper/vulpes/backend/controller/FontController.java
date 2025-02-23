package net.theevilreaper.vulpes.backend.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import jakarta.inject.Inject;
import net.theevilreaper.vulpes.api.model.FontModel;
import net.theevilreaper.vulpes.api.repository.FontRepository;

import java.util.List;

@Controller("/font")
public class FontController {

    private final FontRepository fontDatabaseHandler;

    @Inject
    public FontController(FontRepository fontDatabaseHandler) {
        this.fontDatabaseHandler = fontDatabaseHandler;
    }

    @Post
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<FontModel> add(@Body FontModel item) {
        return fontDatabaseHandler.save(item);
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<FontModel> getById(@PathVariable String id) {
        return fontDatabaseHandler.getByID(id);
    }

    @Delete("/remove/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<FontModel> remove(@PathVariable String id) {
        return fontDatabaseHandler.delete(id);
    }

    @Get("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<List<FontModel>> getAll() {
        return fontDatabaseHandler.getAll();
    }

    @Delete("/deleteAll")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<List<FontModel>> deleteAll() {
        return fontDatabaseHandler.deleteAll();
    }

    @Post("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<FontModel> update(@Body FontModel model) {
        return fontDatabaseHandler.update(model);
    }

}
