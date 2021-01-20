package com.example.demo8.Service;

import com.example.demo8.Exception.OrganizationNotFoundException;
import com.example.demo8.Repository.OrganizationRepository;
import com.example.demo8.Repository.UserRepository;
import com.example.demo8.dto.OrgUserResponse;
import com.example.demo8.dto.OrganizationDto;
import com.example.demo8.model.Organization;
import com.example.demo8.mapper.OrganizationMapper;
import com.example.demo8.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.Instant.now;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@AllArgsConstructor
public class OrganizationService {
    @Autowired
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final OrganizationMapper OrganizationMapper;

    @Transactional(readOnly = true)
    public List<OrganizationDto> getAll(){
        return organizationRepository.findAll().stream().
                map(this::mapToDto).collect(toList());
    }

    @Transactional
    public OrganizationDto save(OrganizationDto OrganizationDto){
        Organization Organization = organizationRepository
                .save(mapToOrganization(OrganizationDto));
        OrganizationDto.setId(Organization.getOrgId());
        return OrganizationDto;
    }

    @Transactional(readOnly = true)
    public OrganizationDto getOrganization(Long orgid){
        Organization organization = organizationRepository.findById(orgid)
                .orElseThrow(()-> new OrganizationNotFoundException
                        ("Organization not found with id - "+orgid));
        return mapToDto(organization);
    }

    @Transactional(readOnly = true)
    public List<OrganizationDto> getOrganizationByUsername() {
        User user =   authService.getCurrentUser();
//                userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException(username));
        return organizationRepository.findByUser(user)
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public OrganizationDto getOrganizationById(Long orgid) {
        Organization organization = organizationRepository.findById(orgid)
                .orElseThrow(()-> new OrganizationNotFoundException
                        ("Organization not found with id - "+orgid));
        return mapToDto(organization);
    }

    private OrganizationDto mapToDto(Organization Organization){
        return
                OrganizationDto.builder().name(Organization.getOrgName())
                        .id(Organization.getOrgId())
                        .description(Organization.getDescription())
                        .build();
    }

    private Organization mapToOrganization(OrganizationDto OrganizationDto){
        return Organization.builder().orgName(OrganizationDto.getName())
                .description(OrganizationDto.getDescription())
                .user(authService.getCurrentUser())
                .createdDate(now()).build();
    }

    
    
}
