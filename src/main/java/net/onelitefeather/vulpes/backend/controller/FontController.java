package net.onelitefeather.vulpes.backend.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import net.onelitefeather.vulpes.api.model.FontEntity;
import net.onelitefeather.vulpes.api.repository.FontRepository;
import net.onelitefeather.vulpes.backend.domain.font.FontModelDTO;
import net.onelitefeather.vulpes.backend.domain.font.FontModelResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static net.onelitefeather.vulpes.backend.domain.font.FontModelResponseDTO.*;

@Controller("/font")
public class FontController {

    private final FontRepository fontRepository;

    @Inject
    public FontController(FontRepository fontRepository) {
        this.fontRepository = fontRepository;
    }

    @Operation(
            summary = "Add a new font",
            description = "Adds a new font to the database. The font is created with the given properties.",
            tags = {"Font"}
    )
    @ApiResponse(
            responseCode = "200",
            description = "The font was successfully added to the database.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = FontModelResponseDTO.FontModelDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "500",
            description = "The font could not be added to the database.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = FontModelErrorDTO.class)
            )
    )
    @Post
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<FontModelResponseDTO> add(
            @Valid @Body FontModelDTO item
    ) {
        FontEntity fontModel = item.toFontModel();
        fontModel = fontRepository.save(fontModel);
        return HttpResponse.ok(FontModelResponseDTO.FontModelDTO.createDTOWithChars(fontModel));
    }

    @Operation(
            summary = "Get a font by ID",
            description = "Gets a font by ID from the database.",
            tags = {"Font"}
    )
    @ApiResponse(
            responseCode = "200",
            description = "The font was successfully retrieved from the database.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = FontModelResponseDTO.FontModelDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "The font was not found in the database.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = FontModelErrorDTO.class)
            )
    )
    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<FontModelResponseDTO> getById(@PathVariable UUID id) {
        Optional<FontEntity> model = fontRepository.findById(id);
        if (model.isPresent()) {
            FontEntity fontModel = model.get();
            FontModelResponseDTO.FontModelDTO dto = FontModelResponseDTO.FontModelDTO.createDTO(fontModel);
            return HttpResponse.ok(dto);
        }
        return HttpResponse.notFound(new FontModelErrorDTO("Font not found"));
    }


    @Operation(
            summary = "Get characters by font ID",
            description = "Gets the characters of a font by its ID from the database.",
            tags = {"Font"}
    )
    @ApiResponse(
            responseCode = "200",
            description = "The characters of the font were successfully retrieved from the database.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = FontModelCharsResponseDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "The font was not found in the database.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = FontModelErrorDTO.class)
            )
    )
    @Get("/chars/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<FontModelResponseDTO> getCharsById(@PathVariable UUID id) {
        List<String> model = fontRepository.findCharsByFontId(id);
        if (!model.isEmpty()) {
            FontModelCharsResponseDTO dto = FontModelCharsResponseDTO.createDTO(id, model);
            return HttpResponse.ok(dto);
        }
        return HttpResponse.notFound(new FontModelErrorDTO("Font not found"));
    }

    @Operation(
            summary = "Remove a font by ID",
            description = "Removes a font by ID from the database.",
            tags = {"Font"}
    )
    @ApiResponse(
            responseCode = "200",
            description = "The font was successfully removed from the database.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = FontModelResponseDTO.FontModelDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "The font was not found in the database.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = FontModelErrorDTO.class)
            )
    )
    @Delete("/remove/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<FontModelResponseDTO> remove(@PathVariable UUID id) {
        Optional<FontEntity> model = fontRepository.findById(id);
        if (model.isPresent()) {
            fontRepository.deleteById(id);
            FontEntity fontModel = model.get();
            FontModelResponseDTO.FontModelDTO dto = FontModelResponseDTO.FontModelDTO.createDTO(fontModel);
            return HttpResponse.ok(dto);
        }
        return HttpResponse.notFound(new FontModelErrorDTO("Font not found"));
    }

    @Operation(
            summary = "Get all fonts",
            description = "Gets all fonts from the database.",
            tags = {"Font"}
    )
    @ApiResponse(
            responseCode = "200",
            description = "The fonts were successfully retrieved from the database.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = FontModelResponseDTO.FontModelDTO.class)
            )
    )
    @Get("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<List<FontModelResponseDTO>> getAll() {
        List<FontModelResponseDTO> models = fontRepository.findAll().stream().map(FontModelResponseDTO.FontModelDTO::createDTO).collect(Collectors.toList());
        return HttpResponse.ok(models);
    }

    @Operation(
            summary = "Delete all fonts",
            description = "Deletes all fonts from the database.",
            tags = {"Font"}
    )
    @ApiResponse(
            responseCode = "200",
            description = "All fonts were successfully deleted from the database.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = FontModelResponseDTO.FontModelDTO.class)
            )
    )
    @Delete("/deleteAll")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<List<FontModelResponseDTO>> deleteAll() {
        fontRepository.deleteAll();
        return HttpResponse.ok(List.of());
    }

    @Operation(
            summary = "Update a font",
            description = "Updates a font in the database.",
            tags = {"Font"}
    )
    @ApiResponse(
            responseCode = "200",
            description = "The font was successfully updated in the database.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = FontModelResponseDTO.FontModelDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "The font was not found in the database.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = FontModelErrorDTO.class)
            )
    )
    @Post("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<FontModelResponseDTO> update(
            @Valid @Body FontModelDTO model
    ) {
        Optional<FontEntity> modelOptional = fontRepository.findById(model.getId());
        if (modelOptional.isEmpty()) {
            return HttpResponse.notFound(new FontModelErrorDTO("Font not found"));
        }
        var updatedFontModel = fontRepository.update(model.toFontModel());
        return HttpResponse.ok(FontModelResponseDTO.FontModelDTO.createDTOWithChars(updatedFontModel));
    }
}
