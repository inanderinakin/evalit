package com.fullhouse.server.mappers;

import com.fullhouse.DTOs.BusinessInListDTO;
import com.fullhouse.server.domain.Business;

public class BusinessToBusinessInListMapper {

    public static BusinessInListDTO businessToBusinessInListDTO(Business business) {
        Long ownerId = business.getOwner() != null ? business.getOwner().getId() : null;

        return new BusinessInListDTO(
                business.getId(),
                business.getName(),
                ownerId
        );
    }
}