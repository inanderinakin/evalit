package com.fullhouse.server.services;

import com.fullhouse.DTOs.BusinessDTOs.*;
import com.fullhouse.server.domain.Business;
import com.fullhouse.server.domain.Survey;
import com.fullhouse.server.mappers.BusinessToBusinessInListDTOMapper;
import com.fullhouse.server.repositories.BusinessRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessServiceImpl implements BusinessService {

    private final BusinessRepository businessRepository;

    public BusinessServiceImpl(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    @Override
    public BusinessGetListByNameResponse getBusinessesByName(BusinessGetListByNameRequest request) {
        String name = request.getName() == null ? "" : request.getName();
        List<Business> businesses = businessRepository.findByNameContainingIgnoreCaseOrderByAverageScoreDesc(name);
        List<BusinessInListDTO> businessDtos = new ArrayList<>();
        for (Business business : businesses) {
            businessDtos.add(new BusinessInListDTO(business.getId(), business.getName(), business.getAddress(), business.getPhoneNumber(), business.getImageURL(), business.getAverageScore(), business.getCity()));
        }

        return new BusinessGetListByNameResponse(businessDtos);
    }

    @Override
    public BusinessGetListByCityCategoryResponse getBusinessesByCategoryAndCity(BusinessGetListByCityCategoryRequest request) {
        List<Business> businesses = businessRepository.findByCityAndDynamicCategoryCheck(request.getCity(), request.getCategory());
        List<BusinessInListDTO> businessInListDTOList = new ArrayList<>();
        for( Business b : businesses ) {
            businessInListDTOList.add(new BusinessInListDTO(b.getId(), b.getName(), b.getAddress(), b.getPhoneNumber(), b.getImageURL(), b.getAverageScore(), b.getCity()));
        }

        return new BusinessGetListByCityCategoryResponse(businessInListDTOList);
    }

    // TODO: Implement this method.
    // TODO: -Koray-
    @Override
    public BusinessGetListBySurveyResponse getBusinessesBySurvey(BusinessGetListBySurveyRequest request) {
        return null;

    }

    @Override
    public void saveLogo(Long businessId, byte[] logoBytes) {
        Optional<Business> businessOptional = businessRepository.findById(businessId);
        if (businessOptional.isEmpty()) {
            return;
        }

        Business business = businessOptional.get();
        String fileName = businessId + ".png";

        try {
            Path uploadPath = Paths.get("uploads/logos/").toAbsolutePath();
            Files.createDirectories(uploadPath);

            Path filePath = uploadPath.resolve(fileName);
            Files.write(filePath, logoBytes);

            business.setImageURL("/logos/" + fileName);
            businessRepository.save(business);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private float computeAverageScore(Business business) {
    public void updateAverageScoreBasedOnTheResponse(String formId) {
        Business business = businessRepository.findByFormId(formId);
        List<Survey> surveys = business.getSurveys();

        if(surveys == null || surveys.isEmpty()) {
            business.setAverageScore(0.0f);
        }

        double total = 0;
        int count = 0;

        for (Survey survey : surveys) {
            total += survey.getOverallScore();
            count++;
        }

        business.setAverageScore((float) (total / count));
        businessRepository.save(business);
    }
}