package net.onelitefeather.vulpes.backend.domain.sound.validation;

import net.onelitefeather.vulpes.backend.domain.ValidationTestBase;
import net.onelitefeather.vulpes.backend.domain.sound.SoundEventDTO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class SoundEventDTOValidationTest extends ValidationTestBase<SoundEventDTO> {

    @Test
    void testBlankUiNameValidationFail() {
        SoundEventDTO dto = new SoundEventDTO(
                UUID.randomUUID(),
                "", // invalid
                "variableName",
                "keyName",
                "SubTitle"
        );

        assertViolation(dto, "uiName");
    }

    @Test
    void testBlankVariableNameValidationFail() {
        SoundEventDTO dto = new SoundEventDTO(
                UUID.randomUUID(),
                "UI Name",
                "", // invalid
                "keyName",
                "SubTitle"
        );

        assertViolation(dto, "variableName");
    }

    @Test
    void testBlankKeyNameValidationFail() {
        SoundEventDTO dto = new SoundEventDTO(
                UUID.randomUUID(),
                "UI Name",
                "variableName",
                "", // invalid
                "subTitle"
        );
        assertViolation(dto, "keyName");
    }

    @Test
    void testBlankSubTitleValidationFail() {
        SoundEventDTO dto = new SoundEventDTO(
                UUID.randomUUID(),
                "UI Name",
                "variableName",
                "keyName",
                "" // invalid
        );

        assertViolation(dto, "subTitle");
    }
}
