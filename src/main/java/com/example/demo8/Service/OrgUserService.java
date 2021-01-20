package com.example.demo8.Service;

import com.example.demo8.Exception.OrganizationNotFoundException;
import com.example.demo8.Exception.OrganizationUserNotFoundException;
import com.example.demo8.Repository.OrgUserRepository;
import com.example.demo8.Repository.OrganizationRepository;
import com.example.demo8.Repository.UserRepository;
import com.example.demo8.dto.OrgUserDto;
import com.example.demo8.dto.OrganizationDto;
import com.example.demo8.mapper.OrganizationMapper;
import com.example.demo8.model.NotificationEmail;
import com.example.demo8.model.OrgUser;
import com.example.demo8.model.Organization;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.demo8.model.OrgUser.*;
import static com.example.demo8.util.Constants.ACTIVATION_EMAIL;
import static com.example.demo8.util.Constants.DENY_EMAIL;
import static java.time.Instant.now;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@AllArgsConstructor
public class OrgUserService {
    @Autowired
    private final OrganizationRepository organizationRepository;
    private final OrgUserRepository orgUserRepository ;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;
    private final AuthService authService;

    @Transactional(readOnly = true)
    public List<OrgUserDto> getOrganizationUsers(Long orgid){
        Organization organization = organizationRepository.findById(orgid)
                .orElseThrow(()-> new OrganizationNotFoundException
                        ("Organization not found with id - "+orgid));
        return orgUserRepository.findByOrganization(organization)
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    @Transactional
    public OrgUserDto createOrganizationUser(Long orgid,OrgUserDto OrgUserDto){
//        OrgUser OrgUser =   new OrgUser();
//        OrgUser.setEmail(OrgUserDto.getEmail());
        Organization organization=organizationRepository.findById(orgid)
                .orElseThrow(()->new OrganizationNotFoundException
                        ("Organization not found with id - "+orgid));
//        OrgUser.setOrganization(organization);
//        OrgUser.setStatus("pending");
//        OrgUser.setCreated(now());
        OrgUser orgUser = orgUserRepository.save(mapToOrgUser(OrgUserDto,organization));
//        orgUserRepository.save(OrgUser);
//                orgUserRepository
//                .save(mapToOrgUser(OrgUserDto));
        OrgUserDto.setId(orgUser.getOrguserId());
        String message = mailContentBuilder.build("Thank you for signing up , please click on the below url to activate your account : "
                + ACTIVATION_EMAIL + "/" + orgUser.getOrguserId()+"/n To deny invitation click following"+DENY_EMAIL+orgUser.getOrguserId());

        mailService.sendMail(new NotificationEmail("Please Activate your account",OrgUserDto.getEmail(), message));
        return OrgUserDto ;
    }

    private OrgUserDto mapToDto(OrgUser OrgUser){
        return
                OrgUserDto.builder().email(OrgUser.getEmail())
                        .id(OrgUser.getOrguserId())
                        .status(OrgUser.getStatus())
                        .build();
    }

    private OrgUser mapToOrgUser(OrgUserDto OrgUserDto,Organization organization){
        return OrgUser.builder().email(OrgUserDto.getEmail())
                .status("pending")
                .user(authService.getCurrentUser())
                .organization(organization)
                .created(now()).build();
    }

    @Transactional
      public OrgUser activeOrganizationUser(Long orgUserid){
        OrgUser orgUser=  orgUserRepository.findById(orgUserid).get();
//                .orElseThrow(()-> new OrganizationUserNotFoundException
//                ("Organization user not found with id - "+orgUserid));
        orgUser.setStatus("active");
        orgUserRepository.save(orgUser) ;
        
        return orgUser;
        
      }
    @Transactional
    public OrgUser denyOrganizationUser(Long orgUserid){
        OrgUser orgUser=  orgUserRepository.findById(orgUserid) .get();
//                .orElseThrow(()-> new OrganizationUserNotFoundException
//                        ("Organization user not found with id - "+orgUserid));
        orgUser.setStatus("deny");
        orgUserRepository.save(orgUser) ;
        return orgUser;

    }

    
}
