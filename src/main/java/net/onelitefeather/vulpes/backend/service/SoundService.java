package net.onelitefeather.vulpes.backend.service;

import net.onelitefeather.vulpes.api.model.sound.SoundEventEntity;
import net.onelitefeather.vulpes.backend.domain.sound.SoundEventDTO;
import net.onelitefeather.vulpes.backend.domain.sound.SoundResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for managing sound events.
 */
public interface SoundService {

    /**
     * Creates a new sound event.
     *
     * @param soundEventDTO the sound event data to create
     * @return the created sound event response
     */
    SoundResponseDTO.SoundModelDTO createSoundEvent(SoundEventDTO soundEventDTO);

    /**
     * Updates an existing sound event.
     *
     * @param soundEventDTO the sound event data to update
     * @return the updated sound event response or an error response if the sound event doesn't exist
     */
    SoundResponseDTO updateSoundEvent(SoundEventDTO soundEventDTO);

    /**
     * Deletes a sound event by its ID.
     *
     * @param id the ID of the sound event to delete
     * @return the deleted sound event response or an error response if the sound event doesn't exist
     */
    SoundResponseDTO deleteSoundEvent(UUID id);

    /**
     * Deletes all sound events.
     *
     * @return an empty list
     */
    List<SoundResponseDTO> deleteAllSoundEvents();

    /**
     * Gets all sound events.
     *
     * @return a list of all sound events
     */
    List<SoundResponseDTO> getAllSoundEvents();

    /**
     * Finds a sound event by its ID.
     *
     * @param id the ID of the sound event to find
     * @return an optional containing the sound event if found, or empty if not found
     */
    Optional<SoundEventEntity> findSoundEventById(UUID id);

    /**
     * Gets all sound file sources by an ID.
     *
     * @param id the ID of the sound event
     * @return the sound event response with sources
     */
    SoundResponseDTO getSoundSourcesById(UUID id);
}