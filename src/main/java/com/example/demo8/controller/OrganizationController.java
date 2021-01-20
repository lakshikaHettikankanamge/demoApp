package com.example.demo8.controller;

import com.example.demo8.Service.OrgUserService;
import com.example.demo8.Service.OrganizationService;
import com.example.demo8.dto.OrgUserDto;
import com.example.demo8.dto.OrganizationDto;
import com.example.demo8.model.OrgUser;
import com.example.demo8.model.Organization;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class OrganizationController {
    private final OrganizationService organizationService;
    private final OrgUserService orgUserService;

    //Get all organization
    @GetMapping ("/organizations")
    public List<OrganizationDto> getAllOrganizations() {
        return
                organizationService.getAll();
    }
    
//    //get organization
//    @GetMapping("/myorganizations/{orgid}")
//    public OrganizationDto getOrganizationById(@PathVariable Long orgid) {
//        return organizationService.getOrganizationById(orgid);
//    }

    //get  organizations of a user
    @GetMapping("/myorganizations")
    public List<OrganizationDto> getOrganizationByUsername() {

        return organizationService.getOrganizationByUsername();
    }

    //create organization
    @PostMapping("/myorganizations")
    public OrganizationDto create(
            @RequestBody @Valid OrganizationDto organizationDto) {
        return organizationService.save(organizationDto);
    }

    //get organization users
    @GetMapping("/myorganizations/{orgid}")
    public List<OrgUserDto> getOrganizationUsers(@PathVariable Long orgid) {
        return orgUserService.getOrganizationUsers(orgid);
    }

    //create organization user
    @PostMapping("/myorganizations/{orgid}")
    public OrgUserDto createOrganizationUser(@PathVariable Long orgid,
                                          @RequestBody @Valid OrgUserDto orgUserDto) {
        return orgUserService.createOrganizationUser(orgid,orgUserDto);
    }

    //active organization user
    @GetMapping("/accountVerification/{orguserid}")
    public OrgUser activeOrganizationUser(@PathVariable Long orguserid) {
        return orgUserService.activeOrganizationUser(orguserid);
    }

//    //deny organization user
    @GetMapping("/accountDeny/{orguserid}")
    public OrgUser denyOrganizationUser(@PathVariable Long orguserid) {
        return orgUserService.denyOrganizationUser(orguserid);
    }
}
