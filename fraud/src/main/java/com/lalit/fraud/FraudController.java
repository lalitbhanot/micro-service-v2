package com.lalit.fraud;


import com.lalit.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
@Slf4j

public class FraudController {
    @Autowired
    FraudCheckService fraudCheckService ;

    @GetMapping("{customerId}")
    public FraudCheckResponse isFraudester (@PathVariable("customerId") Integer customerId){
        boolean isFraudCustomer = fraudCheckService.isFraudlentCustomer(customerId) ;
        return new FraudCheckResponse(isFraudCustomer);

    }
}
