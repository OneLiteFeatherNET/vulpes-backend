package net.onelitefeather.vulpes.backend.domain.font;

import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import net.onelitefeather.vulpes.api.model.FontEntity;

import java.util.List;
import java.util.UUID;

@Schema
@Serdeable
public record FontModelDTO(
        @Schema(description = "ID of the mode", requiredMode = Schema.RequiredMode.NOT_REQUIRED) UUID id,
        @Schema(description = "Model Name for the ui", requiredMode = Schema.RequiredMode.REQUIRED) String uiName,
        @Schema(description = "Name in the UI", requiredMode = Schema.RequiredMode.REQUIRED) String variableName,
        @Schema(description = "Which provider should be used", requiredMode = Schema.RequiredMode.REQUIRED) String provider,
        @Schema(description = "Internal mapper variable", requiredMode = Schema.RequiredMode.REQUIRED) String mapper,
        @Schema(description = "The path to the texture", requiredMode = Schema.RequiredMode.REQUIRED) String texturePath,
        @Schema(description = "The comment", requiredMode = Schema.RequiredMode.REQUIRED) String comment,
        @Schema(description = "The ascent property", requiredMode = Schema.RequiredMode.REQUIRED) int ascent,
        @Schema(description = "The height property", requiredMode = Schema.RequiredMode.REQUIRED) int height,
        @Schema(description = "The chars which are overwritten", requiredMode = Schema.RequiredMode.NOT_REQUIRED) List<String> chars
) {

    /**
     * Converts a {@link FontModelDTO} to a {@link FontEntity}.
     *
     * @return the converted entity
     */
    public @NotNull FontEntity toFontModel() {
        return new FontEntity(
                id,
                uiName,
                variableName,
                provider,
                texturePath,
                comment,
                height,
                ascent,
                chars
        );
    }
}
