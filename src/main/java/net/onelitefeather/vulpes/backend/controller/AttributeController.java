package net.onelitefeather.vulpes.backend.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import net.onelitefeather.vulpes.backend.domain.attribute.AttributeModelDTO;
import net.onelitefeather.vulpes.backend.domain.attribute.AttributeModelResponseDTO;
import net.onelitefeather.vulpes.backend.service.AttributeService;

import java.util.List;
import java.util.UUID;

@Controller("/attribute")
public class AttributeController {

    private final AttributeService attributeService;

    @Inject
    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @Operation(
            summary = "Add a new attribute",
            description = "Adds a new attribute to the database. The attribute is created with the given properties.",
            tags = {"Attribute"}
    )
    @ApiResponse(
            responseCode = "200",
            description = "The attribute was successfully added to the database.",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AttributeModelResponseDTO.AttributeModelDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "500",
            description = "The attribute could not be added to the database.",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AttributeModelResponseDTO.AttributeModelErrorDTO.class)
            )
    )
    @Post
    public HttpResponse<AttributeModelResponseDTO> add(@Valid @Body AttributeModelDTO model) {
        AttributeModelResponseDTO.AttributeModelDTO createdAttribute = attributeService.createAttribute(model);
        return HttpResponse.ok(createdAttribute);
    }

    @Operation(
            summary = "Get an attribute by ID",
            description = "Returns the attribute with the given ID.",
            tags = {"Attribute"}
    )
    @ApiResponse(
            responseCode = "200",
            description = "The attribute was successfully found.",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AttributeModelResponseDTO.AttributeModelDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "The attribute was not found.",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AttributeModelResponseDTO.AttributeModelErrorDTO.class)
            )
    )
    @Post("/update")
    public HttpResponse<AttributeModelResponseDTO> update(@Valid @Body AttributeModelDTO model) {
        AttributeModelResponseDTO result = attributeService.updateAttribute(model);
        if (result instanceof AttributeModelResponseDTO.AttributeModelErrorDTO) {
            return HttpResponse.notFound(result);
        }
        return HttpResponse.ok(result);
    }
    @Operation(
            summary = "Delete an attribute by ID",
            description = "Deletes the attribute with the given ID.",
            tags = {"Attribute"}
    )
    @ApiResponse(
            responseCode = "200",
            description = "The attribute was successfully deleted.",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AttributeModelResponseDTO.AttributeModelDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "The attribute was not found.",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AttributeModelResponseDTO.AttributeModelErrorDTO.class)
            )
    )
    @Delete("/delete/{id}")
    public HttpResponse<AttributeModelResponseDTO> delete(@PathVariable UUID id) {
        AttributeModelResponseDTO result = attributeService.deleteAttribute(id);
        if (result instanceof AttributeModelResponseDTO.AttributeModelErrorDTO) {
            return HttpResponse.notFound(result);
        }
        return HttpResponse.ok(result);
    }

    /**
     * Deletes all [AttributeModel] from the database.
     *
     * @return a list with all [AttributeModel] mapped in a [HttpResponse]
     */
    @Operation(
            summary = "Delete all attributes",
            description = "Deletes all attributes from the database.",
            tags = {"Attribute"}
    )
    @ApiResponse(
            responseCode = "200",
            description = "All attributes were successfully deleted.",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AttributeModelResponseDTO.AttributeModelDTO.class)
            )
    )
    @Delete("/deleteAll")
    public HttpResponse<List<AttributeModelResponseDTO>> deleteAll() {
        List<AttributeModelResponseDTO> result = attributeService.deleteAllAttributes();
        return HttpResponse.ok(result);
    }

    /**
     * Returns all [AttributeModel] which are currently persists in the database.
     *
     * @return a list with all [AttributeModel] mapped in a [HttpResponse]
     */
    @Operation(
            summary = "Get all attributes",
            description = "Gets all attributes from the database.",
            tags = {"Attribute"}
    )
    @ApiResponse(
            responseCode = "200",
            description = "The attributes were successfully retrieved.",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AttributeModelResponseDTO.AttributeModelDTO.class)
            )
    )
    @Get("/getAll")
    public HttpResponse<List<AttributeModelResponseDTO.AttributeModelDTO>> getAll() {
        List<AttributeModelResponseDTO.AttributeModelDTO> models = attributeService.getAllAttributes();
        return HttpResponse.ok(models);
    }
}
