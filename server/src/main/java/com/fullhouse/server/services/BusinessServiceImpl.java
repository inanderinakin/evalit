package com.fullhouse.server.services;

import com.fullhouse.DTOs.BusinessInListDTO;
import com.fullhouse.DTOs.BusinessListRequest;
import com.fullhouse.DTOs.BusinessListResponse;
import com.fullhouse.server.domain.Business;
import com.fullhouse.server.mappers.BusinessToBusinessInListMapper;
import com.fullhouse.server.repositories.BusinessRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {

    private final BusinessRepository businessRepository;

    public BusinessServiceImpl(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    @Override
    public BusinessListResponse getBusinesses(BusinessListRequest request) {
        String name = request.getName() == null ? "" : request.getName();

        List<Business> businesses = businessRepository.findByNameContainingIgnoreCase(name);

        List<BusinessInListDTO> businessDtos = new ArrayList<>();
        for (Business business : businesses) {
            businessDtos.add(BusinessToBusinessInListMapper.businessToBusinessInListDTO(business));
        }

        return new BusinessListResponse(businessDtos);
    }
}