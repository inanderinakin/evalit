package com.fullhouse.server.mappers;

import com.fullhouse.DTOs.BusinessDTOs.BusinessInListDTO;
import com.fullhouse.server.domain.Business;

/**
 * The type Business to business ın list dto mapper.
 */
public class BusinessToBusinessInListDTOMapper {

    /**
     * Business to business ın list dto business ın list dto.
     *
     * @param business the business
     * @return the business ın list dto
     */
    public static BusinessInListDTO businessToBusinessInListDTO(Business business) {
        return new BusinessInListDTO(business.getId(), business.getName(), business.getAddress(), business.getPhoneNumber(), business.getImageURL(), business.getAverageScore(), business.getCity());
    }
}
