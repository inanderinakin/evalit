package com.fullhouse.server.services;

import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessStartRequest;
import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessStartResponse;
import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessVerifyRequest;
import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessVerifyResponse;
import com.fullhouse.server.domain.Business;
import com.fullhouse.server.domain.User;
import com.fullhouse.server.repositories.BusinessRepository;
import com.fullhouse.server.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class ClaimBusinessServiceImpl implements ClaimBusinessService {

    private final UserRepository userRepository;
    private final BusinessRepository businessRepository;
    private final BusinessClaimVerificationStore verificationStore;
    private final EmailService emailService;

    public ClaimBusinessServiceImpl(UserRepository userRepository,
                                    BusinessRepository businessRepository,
                                    BusinessClaimVerificationStore verificationStore,
                                    EmailService emailService) {
        this.userRepository = userRepository;
        this.businessRepository = businessRepository;
        this.verificationStore = verificationStore;
        this.emailService = emailService;
    }

    @Override
    public ClaimBusinessStartResponse startClaim(ClaimBusinessStartRequest request) {
        User user = userRepository.findByGoogleSub(request.getGoogleSub()).orElse(null);
        if (user == null) {
            return new ClaimBusinessStartResponse(false, "User not found.");
        }

        String code = generateCode();

        PendingBusinessClaim claim = new PendingBusinessClaim(
                request.getGoogleSub(),
                request.getBusinessName(),
                request.getBusinessEmail(),
                request.getAddress(),
                request.getPhoneNumber(),
                request.getCity(),
                request.getImageURL(),
                code
        );

        verificationStore.save(claim);
        emailService.sendVerificationEmail(request.getBusinessEmail(), code);

        return new ClaimBusinessStartResponse(true, "Verification code sent to business email.");
    }

    @Override
    public ClaimBusinessVerifyResponse verifyClaim(ClaimBusinessVerifyRequest request) {
        PendingBusinessClaim claim = verificationStore.findByEmail(request.getBusinessEmail());
        if (claim == null) {
            return new ClaimBusinessVerifyResponse(false, "No pending claim found.");
        }

        if (!claim.getGoogleSub().equals(request.getGoogleSub())) {
            return new ClaimBusinessVerifyResponse(false, "User mismatch.");
        }

        if (!claim.getVerificationCode().equals(request.getVerificationCode())) {
            return new ClaimBusinessVerifyResponse(false, "Invalid verification code.");
        }

        User user = userRepository.findByGoogleSub(request.getGoogleSub()).orElse(null);
        if (user == null) {
            return new ClaimBusinessVerifyResponse(false, "User not found.");
        }

        Business business = new Business();
        business.setName(claim.getBusinessName());
        business.setEmail(claim.getBusinessEmail());
        business.setImageURL(claim.getImageURL());
        business.setAddress(claim.getAddress());
        business.setPhoneNumber(claim.getPhoneNumber());
        business.setCity(claim.getCity());
        business.setOwner(user);
        business.setSurveys(new ArrayList<>());

        businessRepository.save(business);

        user.setBusinessOwner(true);
        user.getBusinesses().add(business);
        userRepository.save(user);

        verificationStore.remove(request.getBusinessEmail());

        return new ClaimBusinessVerifyResponse(true, "Business claimed successfully.", business.getId());
    }

    private String generateCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}