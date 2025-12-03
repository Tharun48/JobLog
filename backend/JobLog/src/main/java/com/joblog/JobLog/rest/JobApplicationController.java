package com.joblog.JobLog.rest;

import com.joblog.JobLog.mapper.ApplicationMapper;
import com.joblog.JobLog.model.JobApplicationDTO;
import com.joblog.JobLog.model.JobApplicationResponseDTO;
import com.joblog.JobLog.model.JobApplications;
import com.joblog.JobLog.service.ApplicationDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobApplicationController {

    ApplicationDetailsService applicationDetailsService;
    ApplicationMapper applicationMapper;

    @Autowired
    JobApplicationController(ApplicationDetailsService applicationDetailsService, ApplicationMapper applicationMapper) {
        this.applicationDetailsService = applicationDetailsService;
        this.applicationMapper = applicationMapper;
    }

    @PostMapping("/application")
    public ResponseEntity<JobApplicationResponseDTO> createApplication(@RequestBody JobApplicationDTO jobApplicationDTO) {
        JobApplications jobApplications = applicationMapper.toEntity(jobApplicationDTO);
        JobApplicationResponseDTO jobApplicationResponseDTO = applicationDetailsService.createApplication(jobApplicationDTO.username(), jobApplications);
        return new ResponseEntity<>(jobApplicationResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/application")
    public ResponseEntity<JobApplicationResponseDTO> modifyApplication(@RequestBody JobApplicationDTO jobApplicationDTO) {
        JobApplications jobApplications = applicationMapper.toEntity(jobApplicationDTO);
        JobApplicationResponseDTO jobApplicationResponseDTO = applicationDetailsService.modifyApplication(jobApplicationDTO.username(), jobApplications);
        return new ResponseEntity<>(jobApplicationResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/application")
    public ResponseEntity<List<JobApplicationDTO>> fetchApplications(@RequestParam(required = false) String userName, @RequestParam(required = false) String jobId, @RequestParam(required = false) String companyName) {
        List<JobApplications> applications = applicationDetailsService.getUserApplications(userName, jobId, companyName);
        List<JobApplicationDTO> applicationDTO = applicationMapper.toDto(applications);
        return new ResponseEntity<>(applicationDTO, HttpStatus.OK);
    }
}