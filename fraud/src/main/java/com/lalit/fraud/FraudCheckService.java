package com.lalit.fraud;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FraudCheckService {
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {
        this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
    }

    public boolean isFraudCustomer(Integer customerId){
        return false ;
    }

    public boolean isFraudlentCustomer(Integer customerId)
    {
        fraudCheckHistoryRepository.save(
                        FraudCheckHistory.builder()
                                .customerId(customerId)
                                .isFraud(false)
                                .createdAt(LocalDateTime.now()).build()
                );
        return false ;
    }
}
