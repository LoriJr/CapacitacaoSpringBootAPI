package com.icaro.capacitacao.service;

import org.springframework.stereotype.Service;

@Service
public class UserReportService {

    private final UserService service;

    public UserReportService(UserService service){
        this.service = service;
    }


}
