package net.onelitefeather.vulpes.backend.domain.notification;

import java.util.UUID;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import net.onelitefeather.vulpes.api.model.NotificationEntity;

@Schema(requiredProperties = {
        "uiName",
        "variableName",
        "description",
        "material",
        "frameType",
        "title"
})
@Introspected
@Serdeable
public record NotificationModelDTO(
        @Schema(description = "ID of the notification", requiredMode = Schema.RequiredMode.NOT_REQUIRED) UUID id,
        @Schema(description = "Model variableName for the UI", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull @NotBlank @NotEmpty String uiName,
        @Schema(description = "Name in the UI", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull @NotBlank @NotEmpty String variableName,
        @Schema(description = "Description of the notification", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull @NotBlank @NotEmpty String description,
        @Schema(description = "Material identifier", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull @NotBlank @NotEmpty String material,
        @Schema(description = "Type of frame", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull @NotBlank @NotEmpty String frameType,
        @Schema(description = "Title of the notification", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull @NotBlank @NotEmpty String title
) {

    /**
     * Converts this DTO to a {@link NotificationEntity}.
     * @return a new {@link NotificationEntity} instance with the data from this DTO
     */
    public @NotNull NotificationEntity toNotificationModel() {
        return new NotificationEntity(
                this.id,
                uiName,
                variableName,
                description,
                material,
                frameType,
                title
        );
    }
}