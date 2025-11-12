package net.onelitefeather.vulpes.backend.domain.font;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.media.Schema;
import net.onelitefeather.vulpes.api.model.font.FontStringEntity;

import java.util.UUID;

@Schema(
        requiredProperties = {
                "id",
                "line",
                "orderIndex"
        }
)
@Introspected
@Serdeable
public record FontStringDTO(
        UUID id,
        String line,
        int orderIndex
) {

    public FontStringEntity toEntity() {
        FontStringEntity entity = new FontStringEntity();
        entity.setId(this.id);
        entity.setLine(this.line);
        entity.setOrderIndex(this.orderIndex);
        return entity;
    }

}
