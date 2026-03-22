package com.fullhouse.server.services;

import com.fullhouse.DTOs.BusinessGetListResponse;
import com.fullhouse.DTOs.BusinessInListDTO;
import com.fullhouse.server.domain.Business;
import com.fullhouse.server.domain.Survey;
import com.fullhouse.server.repositories.BusinessRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomePageServiceImpl implements HomePageService {

	private final BusinessRepository businessRepository;

	public HomePageServiceImpl(BusinessRepository businessRepository) {
		this.businessRepository = businessRepository;
	}

	@Override
	public BusinessGetListResponse getBusinessList() {
		List<BusinessInListDTO> businesses = businessRepository.findAll()
			.stream()
			.map(this::mapBusinessToListDto)
			.collect(Collectors.toList());

		return new BusinessGetListResponse(businesses);
	}

	private BusinessInListDTO mapBusinessToListDto(Business business) {
		String phoneNumber = business.getOwner().getPhoneNumber();

		String address = business.getAddress();

		float averageScore = calculateAverageScore(business.getSurveys());

		return new BusinessInListDTO(business.getName(), address, phoneNumber, averageScore);
	}

	private float calculateAverageScore(List<Survey> surveys) {;
		if (surveys.isEmpty()) {
			return 0f;
		}

		float sum = 0f;
		for (Survey survey : surveys) {
			sum += survey.getOverallScore();
		}
		return sum / surveys.size();
	}
}