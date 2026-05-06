package com.faculdade.techweek.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faculdade.techweek.web.Admin.dto.DashboardDTO;
import com.faculdade.techweek.web.Admin.mappers.DashboardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final DashboardMapper dashboardMapper;

    @Transactional(readOnly = true)
    public DashboardDTO montarDashboard() {
        
        return dashboardMapper.montarDashboard();
    }
}
