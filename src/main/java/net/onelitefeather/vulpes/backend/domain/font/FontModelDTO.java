package net.onelitefeather.vulpes.backend.domain.font;

import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.media.Schema;
import net.onelitefeather.vulpes.api.model.FontEntity;

import java.util.List;
import java.util.UUID;

@Schema()
@Serdeable
public final class FontModelDTO {

    private final UUID id;
    private final String uiName;
    private final String variableName;
    private final String provider;
    private final String mapper;
    private final String texturePath;
    private final String comment;
    private final int ascent;
    private final int height;
    private final List<String> chars;

    public FontModelDTO(
            @Schema(description = "ID of the mode", requiredMode = Schema.RequiredMode.NOT_REQUIRED) UUID id,
            @Schema(description = "Model Name for the ui", requiredMode = Schema.RequiredMode.REQUIRED) String uiName,
            @Schema(description = "Name in the UI", requiredMode = Schema.RequiredMode.REQUIRED) String variableName,
            @Schema(description = "Which provider should be used", requiredMode = Schema.RequiredMode.REQUIRED) String provider,
            @Schema(description = "Internal mapper variable", requiredMode = Schema.RequiredMode.REQUIRED) String mapper,
            @Schema(description = "The path to the texture", requiredMode = Schema.RequiredMode.REQUIRED) String texturePath,
            @Schema(description = "The comment", requiredMode = Schema.RequiredMode.REQUIRED) String comment,
            @Schema(description = "The ascent property", requiredMode = Schema.RequiredMode.REQUIRED) int ascent,
            @Schema(description = "The height property", requiredMode = Schema.RequiredMode.REQUIRED) int height,
            @Schema(description = "The chars which are overwritten", requiredMode = Schema.RequiredMode.NOT_REQUIRED) List<String> chars) {
        this.id = id;
        this.uiName = uiName;
        this.variableName = variableName;
        this.provider = provider;
        this.mapper = mapper;
        this.texturePath = texturePath;
        this.comment = comment;
        this.ascent = ascent;
        this.height = height;
        this.chars = chars;
    }

    public UUID getId() {
        return id;
    }

    public String getUiName() {
        return uiName;
    }

    public String getVariableName() {
        return variableName;
    }

    public String getProvider() {
        return provider;
    }

    public String getMapper() {
        return mapper;
    }

    public String getTexturePath() {
        return texturePath;
    }

    public String getComment() {
        return comment;
    }

    public int getAscent() {
        return ascent;
    }

    public int getHeight() {
        return height;
    }

    public List<String> getChars() {
        return chars;
    }

    public FontEntity toFontModel() {
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
