package net.onelitefeather.vulpes.backend.service;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import net.onelitefeather.vulpes.api.model.AttributeEntity;
import net.onelitefeather.vulpes.backend.domain.attribute.AttributeModelDTO;
import net.onelitefeather.vulpes.backend.domain.attribute.AttributeModelResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for managing attributes.
 */
public interface AttributeService {

    /**
     * Creates a new attribute.
     *
     * @param attributeModelDTO the attribute data to create
     * @return the created attribute response
     */
    AttributeModelResponseDTO.AttributeModelDTO createAttribute(AttributeModelDTO attributeModelDTO);

    /**
     * Updates an existing attribute.
     *
     * @param attributeModelDTO the attribute data to update
     * @return the updated attribute response or an error response if the attribute doesn't exist
     */
    AttributeModelResponseDTO updateAttribute(AttributeModelDTO attributeModelDTO);

    /**
     * Deletes an attribute by its ID.
     *
     * @param id the ID of the attribute to delete
     * @return the deleted attribute response or an error response if the attribute doesn't exist
     */
    AttributeModelResponseDTO deleteAttribute(UUID id);

    /**
     * Deletes all attributes.
     *
     * @return an empty list
     */
    List<AttributeModelResponseDTO> deleteAllAttributes();

    /**
     * Gets all attributes.
     *
     * @return a list of all attributes
     */
    Page<AttributeModelResponseDTO.AttributeModelDTO> getAllAttributes(Pageable pageable);

    /**
     * Finds an attribute by its ID.
     *
     * @param id the ID of the attribute to find
     * @return an optional containing the attribute if found, or empty if not found
     */
    Optional<AttributeEntity> findAttributeById(UUID id);
}