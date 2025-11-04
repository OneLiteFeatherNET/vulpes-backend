package net.onelitefeather.vulpes.backend.domain.sound;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import net.onelitefeather.vulpes.api.model.sound.SoundFileSource;

import java.util.UUID;

@Schema(
        requiredProperties = {
                "name",
                "volume",
                "pitch",
                "weight",
                "stream",
                "attenuationDistance",
                "preload",
                "type"
        }
)
@Introspected
@Serdeable
public record SoundFileSourceDTO(
        UUID id,
        @NotBlank String name,
        @PositiveOrZero float volume,
        @PositiveOrZero float pitch,
        @Positive int weight,
        boolean stream,
        @Positive int attenuationDistance,
        boolean preload,
        @NotBlank String type
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
