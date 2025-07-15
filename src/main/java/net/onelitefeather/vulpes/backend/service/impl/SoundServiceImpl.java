package net.onelitefeather.vulpes.backend.service.impl;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import net.onelitefeather.vulpes.api.model.sound.SoundEventEntity;
import net.onelitefeather.vulpes.api.repository.SoundRepository;
import net.onelitefeather.vulpes.backend.domain.sound.SoundEventDTO;
import net.onelitefeather.vulpes.backend.domain.sound.SoundResponseDTO;
import net.onelitefeather.vulpes.backend.service.SoundService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static net.onelitefeather.vulpes.backend.domain.sound.SoundResponseDTO.*;

/**
 * Implementation of the SoundService interface.
 */
@Singleton
public class SoundServiceImpl implements SoundService {

    private static final String GENERIC_ERROR = "Sound event not found";
    private final SoundRepository soundRepository;

    /**
     * Constructs a new SoundServiceImpl with the specified SoundRepository.
     *
     * @param soundRepository the repository to manage sound events
     */
    @Inject
    public SoundServiceImpl(SoundRepository soundRepository) {
        this.soundRepository = soundRepository;
    }

    @Override
    public SoundModelDTO createSoundEvent(SoundEventDTO soundEventDTO) {
        SoundEventEntity event = soundEventDTO.toEntity();
        event = soundRepository.save(event);
        return SoundModelDTO.createDTO(event);
    }

    @Override
    public SoundResponseDTO updateSoundEvent(SoundEventDTO soundEventDTO) {
        Optional<SoundEventEntity> existingModel = soundRepository.findById(soundEventDTO.getId());
        if (existingModel.isEmpty()) {
            return new SoundErrorDTO(GENERIC_ERROR);
        }
        SoundEventEntity soundModel = soundEventDTO.toEntity();
        soundModel = soundRepository.update(soundModel);
        return SoundModelDTO.createDTO(soundModel);
    }

    @Override
    public SoundResponseDTO deleteSoundEvent(UUID id) {
        Optional<SoundEventEntity> model = soundRepository.findById(id);
        if (model.isPresent()) {
            soundRepository.deleteById(id);
            return SoundModelDTO.createDTO(model.get());
        }
        return new SoundErrorDTO(GENERIC_ERROR);
    }

    @Override
    public List<SoundResponseDTO> deleteAllSoundEvents() {
        soundRepository.deleteAll();
        return List.of();
    }

    @Override
    public List<SoundResponseDTO> getAllSoundEvents() {
        List<SoundEventEntity> list = soundRepository.findAll();

        if (list.isEmpty()) {
            return Collections.emptyList();
        }

        return list.stream()
                .map(SoundModelDTO::createDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SoundEventEntity> findSoundEventById(UUID id) {
        return soundRepository.findById(id);
    }

    @Override
    public SoundResponseDTO getSoundSourcesById(UUID id) {
        // This method is not fully implemented in the controller
        // Implement according to requirements
        return null;
    }
}