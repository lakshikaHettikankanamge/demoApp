package com.example.demo8.mapper;

import com.example.demo8.dto.OrganizationDto;
import com.example.demo8.model.Organization;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {
    OrganizationDto mapOrganizationToDto(Organization organization);

   
    @InheritInverseConfiguration
    Organization mapDtoToOrganization(OrganizationDto organization);
}
