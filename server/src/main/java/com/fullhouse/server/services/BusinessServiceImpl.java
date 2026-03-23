package com.fullhouse.server.services;

import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByCityCategoryRequest;
import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByCityCategoryResponse;
import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByNameRequest;
import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByNameResponse;
import com.fullhouse.DTOs.BusinessDTOs.BusinessInListDTO;
import com.fullhouse.server.domain.Business;
import com.fullhouse.server.domain.Survey;
import com.fullhouse.server.mappers.BusinessToBusinessInListDTOMapper;
import com.fullhouse.server.repositories.BusinessRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessServiceImpl implements BusinessService {

    private final BusinessRepository businessRepository;

    public BusinessServiceImpl(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    @Override
    public BusinessGetListByNameResponse getBusinessesByName(BusinessGetListByNameRequest request) {
        String name = request.getName() == null ? "" : request.getName();

        List<Business> businesses = businessRepository.findByNameContainingIgnoreCase(name);

        List<BusinessInListDTO> businessDtos = new ArrayList<>();
        for (Business business : businesses) {
            businessDtos.add(new BusinessInListDTO(business.getName(), business.getAddress(), business.getPhoneNumber(), computeAverageScore(business)));
        }

        return new BusinessGetListByNameResponse(businessDtos);
    }

    @Override
    public BusinessGetListByCityCategoryResponse getBusinessesByCategoryAndCity(BusinessGetListByCityCategoryRequest request) {
        List<Business> businesses = businessRepository.findByCityAndSurveysParentSurveyCategory(request.getCity(), request.getCategory());
        List<BusinessInListDTO> businessInListDTOList = new ArrayList<>();

        for( Business b : businesses ) {
            b.setAverageScore(computeAverageScore(b));
            businessInListDTOList.add(BusinessToBusinessInListDTOMapper.businessToBusinessInListDTO(b));
        }

        return new BusinessGetListByCityCategoryResponse(businessInListDTOList);
    }

     private float computeAverageScore(Business business) {
        List<Survey> surveys = business.getSurveys();

        if (surveys == null || surveys.isEmpty()) {
            return 0;
        }

        double total = 0;
        int count = 0;

        for (Survey survey : surveys) {
            total += survey.getOverallScore();
            count++;
        }

        return (float) (total / count);
    }
}