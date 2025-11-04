package net.onelitefeather.vulpes.backend.domain.attribute;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import net.onelitefeather.vulpes.api.model.AttributeEntity;

import java.util.UUID;

@Schema
@Serdeable
@Introspected
public record AttributeModelDTO(
        @Schema(description = "ID of the attribute", requiredMode = Schema.RequiredMode.NOT_REQUIRED) UUID id,
        @Schema(description = "The name for the ui", requiredMode = Schema.RequiredMode.REQUIRED) @NotBlank String uiName,
        @Schema(description = "The name which represents the variable after the generation", requiredMode = Schema.RequiredMode.REQUIRED) @NotBlank String variableName,
        @Schema(description = "Default value of the attribute", requiredMode = Schema.RequiredMode.REQUIRED) @PositiveOrZero double defaultValue,
        @Schema(description = "Maximum value of the attribute", requiredMode = Schema.RequiredMode.REQUIRED) @Positive double maximumValue
) {
    public AttributeEntity toAttributeModel() {
        return new AttributeEntity(id, uiName, variableName, defaultValue, maximumValue);
    }
}
