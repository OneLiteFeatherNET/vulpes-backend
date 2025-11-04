package net.onelitefeather.vulpes.backend.domain.sound;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import net.onelitefeather.vulpes.api.model.sound.SoundFileSource;

import java.util.UUID;

@Schema(
        requiredProperties = {
        }
)
@Introspected
@Serdeable
public record SoundFileSourceDTO(
        UUID id,
        String name,
        float volume,
        float pitch,
        int weight,
        boolean stream,
        int attenuationDistance,
        boolean preload,
        String type
) {

    /**
     * Converts the DTO to an entity
     *
     * @return the converted entity
     */
    public @NotNull SoundFileSource toEntity() {
        return new SoundFileSource(id, name, volume, pitch, weight, stream, attenuationDistance, preload, type);
    }
}
