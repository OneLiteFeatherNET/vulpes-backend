package net.onelitefeather.vulpes.backend.domain.item;

import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.media.Schema;
import net.onelitefeather.vulpes.api.model.ItemEntity;
import net.onelitefeather.vulpes.backend.domain.error.ErrorResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Schema(description = "Response DTO for Item Model")
@Serdeable
public sealed interface ItemModelResponseDTO {

    @Schema(description = "Item Model Data")
    @Serdeable
    record ItemModelDTO(
            @Schema(description = "UUID of the Item Model") UUID uuid,
            @Schema(description = "Model Name for the UI") String modelName,
            @Schema(description = "Name of the item in the UI") String name,
            @Schema(description = "Description of the item") String comment,
            @Schema(description = "Display variableName of the item shown to users") String displayName,
            @Schema(description = "Material type of the item") String material,
            @Schema(description = "Group category variableName for the item") String groupName,
            @Schema(description = "Custom model data value for resource packs") int customModelData,
            @Schema(description = "Quantity of the item") int amount,
            @Schema(description = "Map of enchantment names and their levels") Map<String, Short> enchantments,
            @Schema(description = "List of text lines displayed in the item tooltip") List<String> lore,
            @Schema(description = "List of item flags that modify item behavior") List<String> flags
    ) implements ItemModelResponseDTO {
        public static ItemModelDTO createDTO(ItemEntity itemEntity) {
            return new ItemModelDTO(
                    itemEntity.getId(),
                    itemEntity.getUiName(),
                    itemEntity.getVariableName(),
                    itemEntity.getComment(),
                    itemEntity.getDisplayName(),
                    itemEntity.getMaterial(),
                    itemEntity.getGroupName(),
                    itemEntity.getCustomModelData(),
                    itemEntity.getAmount(),
                    itemEntity.getEnchantments(),
                    itemEntity.getLore(),
                    itemEntity.getFlags()
            );
        }
    }

    @Schema(description = "Error message for Item Model")
    @Serdeable
    record ItemModelErrorDTO(
            @Schema(description = "Error message") String errorMessage
    ) implements ItemModelResponseDTO, ErrorResponse {
    }
}