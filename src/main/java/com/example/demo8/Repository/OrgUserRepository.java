package com.example.demo8.Repository;

import com.example.demo8.model.OrgUser;
import com.example.demo8.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrgUserRepository extends JpaRepository<OrgUser, Long> {
    Optional<OrgUser> findByOrgusername(String orgusername);
    List<OrgUser> findByOrganization(Organization organization);
}
