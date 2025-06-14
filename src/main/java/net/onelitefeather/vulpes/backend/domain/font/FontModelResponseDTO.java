package net.onelitefeather.vulpes.backend.domain.font;

import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.media.Schema;
import net.onelitefeather.vulpes.api.model.FontEntity;
import net.onelitefeather.vulpes.backend.domain.error.ErrorResponse;

import java.util.List;
import java.util.UUID;

@Serdeable
public sealed interface FontModelResponseDTO {

    @Schema(description = "Font model data")
    @Serdeable
    record FontModelDTO(
            @Schema(description = "ID of the mode", requiredMode = Schema.RequiredMode.NOT_REQUIRED) UUID id,
            @Schema(description = "Model Name for the ui", requiredMode = Schema.RequiredMode.REQUIRED) String uiName,
            @Schema(description = "Name in the UI", requiredMode = Schema.RequiredMode.REQUIRED) String variableName,
            @Schema(description = "Example comment", requiredMode = Schema.RequiredMode.REQUIRED) String provider,
            @Schema(description = "Example comment", requiredMode = Schema.RequiredMode.REQUIRED) String mapper,
            @Schema(description = "Example comment", requiredMode = Schema.RequiredMode.REQUIRED) String texturePath,
            @Schema(description = "Example comment", requiredMode = Schema.RequiredMode.REQUIRED) String comment,
            @Schema(description = "Example comment", requiredMode = Schema.RequiredMode.REQUIRED) int ascent,
            @Schema(description = "Example comment", requiredMode = Schema.RequiredMode.REQUIRED) int height,
            @Schema(description = "Example comment", requiredMode = Schema.RequiredMode.NOT_REQUIRED) List<String> chars
    ) implements FontModelResponseDTO {
        public static FontModelDTO createDTO(FontEntity fontModel) {
            return new FontModelDTO(
                    fontModel.getId(),
                    fontModel.getUiName(),
                    fontModel.getVariableName(),
                    fontModel.getProvider(),
                    fontModel.getMapper(),
                    fontModel.getTexturePath(),
                    fontModel.getComment(),
                    fontModel.getAscent(),
                    fontModel.getHeight(),
                    fontModel.getChars()
            );
        }
    }

    @Schema(description = "Error message")
    @Serdeable
    record FontModelErrorDTO(
            @Schema(description = "Error message") String errorMessage) implements FontModelResponseDTO, ErrorResponse {
    }
}
