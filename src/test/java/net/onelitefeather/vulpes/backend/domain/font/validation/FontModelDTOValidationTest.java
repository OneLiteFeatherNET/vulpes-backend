package net.onelitefeather.vulpes.backend.domain.font.validation;

import net.onelitefeather.vulpes.backend.domain.ValidationTestBase;
import net.onelitefeather.vulpes.backend.domain.font.FontModelDTO;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

class FontModelDTOValidationTest extends ValidationTestBase<FontModelDTO> {

    @Test
    void testBlankUiNameValidationFail() {
        FontModelDTO dto = new FontModelDTO(
                UUID.randomUUID(),
                "", // invalid
                "variableName",
                "provider",
                "mapper",
                "texturePath",
                "Some comment",
                10,
                20,
                List.of("a", "b")
        );

        assertViolation(dto, "uiName");
    }

    @Test
    void testBlankVariableNameValidationFail() {
        FontModelDTO dto = new FontModelDTO(
                UUID.randomUUID(),
                "UI Name",
                "", // invalid
                "provider",
                "mapper",
                "texturePath",
                "Some comment",
                10,
                20,
                List.of("a", "b")
        );

        assertViolation(dto, "variableName");
    }

    @Test
    void testBlankProviderValidationFail() {
        FontModelDTO dto = new FontModelDTO(
                UUID.randomUUID(),
                "UI Name",
                "variableName",
                "", // invalid
                "mapper",
                "texturePath",
                "Some comment",
                10,
                20,
                List.of("a", "b")
        );

        assertViolation(dto, "provider");
    }

    @Test
    void testBlankMapperValidationFail() {
        FontModelDTO dto = new FontModelDTO(
                UUID.randomUUID(),
                "UI Name",
                "variableName",
                "provider",
                "", // invalid
                "texturePath",
                "Some comment",
                10,
                20,
                List.of("a", "b")
        );

        assertViolation(dto, "mapper");
    }

    @Test
    void testBlankTexturePathValidationFail() {
        FontModelDTO dto = new FontModelDTO(
                UUID.randomUUID(),
                "UI Name",
                "variableName",
                "provider",
                "mapper",
                "", // invalid
                "Some comment",
                10,
                20,
                List.of("a", "b")
        );

        assertViolation(dto, "texturePath");
    }

    @Test
    void testNegativeAscentValidationFail() {
        FontModelDTO dto = new FontModelDTO(
                UUID.randomUUID(),
                "UI Name",
                "variableName",
                "provider",
                "mapper",
                "texturePath",
                "Some comment",
                -1, // invalid
                20,
                List.of("a", "b")
        );

        assertViolation(dto, "ascent");
    }

    @Test
    void testNegativeHeightValidationFail() {
        FontModelDTO dto = new FontModelDTO(
                UUID.randomUUID(),
                "UI Name",
                "variableName",
                "provider",
                "mapper",
                "texturePath",
                "Some comment",
                10,
                -5, // invalid
                List.of("a", "b")
        );

        assertViolation(dto, "height");
    }
}