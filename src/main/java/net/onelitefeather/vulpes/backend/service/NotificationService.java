package net.onelitefeather.vulpes.backend.service;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import net.onelitefeather.vulpes.api.model.NotificationEntity;
import net.onelitefeather.vulpes.backend.domain.notification.NotificationModelDTO;
import net.onelitefeather.vulpes.backend.domain.notification.NotificationModelResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for managing notifications.
 */
public interface NotificationService {

    /**
     * Creates a new notification.
     *
     * @param notificationModelDTO the notification data to create
     * @return the created notification response
     */
    NotificationModelResponseDTO.NotificationModelDTO createNotification(NotificationModelDTO notificationModelDTO);

    /**
     * Updates an existing notification.
     *
     * @param notificationModelDTO the notification data to update
     * @return the updated notification response or an error response if the notification doesn't exist
     */
    NotificationModelResponseDTO updateNotification(NotificationModelDTO notificationModelDTO);

    /**
     * Deletes a notification by its ID.
     *
     * @param id the ID of the notification to delete
     * @return the deleted notification response or an error response if the notification doesn't exist
     */
    NotificationModelResponseDTO deleteNotification(UUID id);

    /**
     * Deletes all notifications.
     *
     * @return an empty list
     */
    List<NotificationModelResponseDTO> deleteAllNotifications();

    /**
     * Gets all notifications with pagination.
     *
     * @param pageable pagination information
     * @return a page of notifications
     */
    Page<NotificationModelResponseDTO.NotificationModelDTO> getAllNotifications(Pageable pageable);

    /**
     * Finds a notification by its ID.
     *
     * @param id the ID of the notification to find
     * @return an optional containing the notification if found, or empty if not found
     */
    Optional<NotificationEntity> findNotificationById(UUID id);
}