package com.fullhouse.server.mappers;

import com.fullhouse.DTOs.BusinessInListDTO;
import com.fullhouse.server.domain.Business;

public class BusinessToBusinessInListDTOMapper {

    public static BusinessInListDTO businessToBusinessInListDTO(Business business) {
        return new BusinessInListDTO(business.getName(), business.getAddress(), business.getPhoneNumber(), business.getAverageScore());
    }
}
