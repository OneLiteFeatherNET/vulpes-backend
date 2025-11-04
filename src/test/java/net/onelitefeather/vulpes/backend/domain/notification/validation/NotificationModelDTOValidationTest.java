package net.onelitefeather.vulpes.backend.domain.notification.validation;

import net.onelitefeather.vulpes.backend.domain.ValidationTestBase;
import net.onelitefeather.vulpes.backend.domain.notification.NotificationModelDTO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class NotificationModelDTOValidationTest  extends ValidationTestBase<NotificationModelDTO> {

    @Test
    void testBlankUiNameValidationFail() {
        NotificationModelDTO dto = new NotificationModelDTO(
                UUID.randomUUID(),
                "", // invalid
                "variableName",
                "Some comment",
                "minecraft:dirt",
                "Task",
                "Notification Title"
        );

        assertViolation(dto, "uiName");
    }

    @Test
    void testBlankVariableNameValidationFail() {
        NotificationModelDTO dto = new NotificationModelDTO(
                UUID.randomUUID(),
                "UI Name",
                "", // invalid
                "Some comment",
                "minecraft:stone",
                "FrameType",
                "Notification Title"
        );

        assertViolation(dto, "variableName");
    }

    @Test
    void testBlankMaterialValidationFail() {
        NotificationModelDTO dto = new NotificationModelDTO(
                UUID.randomUUID(),
                "UI Name",
                "variableName",
                "Some comment",
                "", // invalid
                "FrameTypeA",
                "Notification Title"
        );

        assertViolation(dto, "material");
    }

    @Test
    void testBlankFrameTypeValidationFail() {
        NotificationModelDTO dto = new NotificationModelDTO(
                UUID.randomUUID(),
                "UI Name",
                "variableName",
                "Some comment",
                "Material1",
                "", // invalid
                "Notification Title"
        );

        assertViolation(dto, "frameType");
    }

    @Test
    void testBlankTitleValidationFail() {
        NotificationModelDTO dto = new NotificationModelDTO(
                UUID.randomUUID(),
                "UI Name",
                "variableName",
                "Some comment",
                "Material1",
                "FrameTypeA",
                "" // invalid
        );

        assertViolation(dto, "title");
    }
}
