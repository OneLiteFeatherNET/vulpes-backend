package net.theevilreaper.vulpes.backend.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.annotation.Controller;
import net.theevilreaper.vulpes.api.model.NotificationModel;
import net.theevilreaper.vulpes.api.repository.NotificationRepository;
import net.theevilreaper.vulpes.backend.dao.DatabaseAccessObject;
import jakarta.inject.Inject;

import java.util.List;

/**
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller("/notification")
public class NotificationController {

    private final NotificationRepository notificationRepository;

    @Inject
    public NotificationController(DatabaseAccessObject<NotificationModel> notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Post
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<NotificationModel> add(@Body NotificationModel model) {
        return notificationRepository.add(model);
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<NotificationModel> getById(@PathVariable String id) {
        return notificationRepository.getByID(id);
    }

    @Delete("/remove/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<NotificationModel> remove(@PathVariable String id) {
        return notificationRepository.delete(id);
    }

    @Get("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<List<NotificationModel>> getAll() {
        return notificationRepository.getAll();
    }

    @Delete("/deleteAll")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<List<NotificationModel>> deleteAll() {
        return notificationRepository.deleteAll();
    }

    @Post("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<NotificationModel> update(@Body NotificationModel model) {
        return notificationRepository.update(model);
    }
}