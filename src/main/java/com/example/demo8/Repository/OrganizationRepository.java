package com.example.demo8.Repository;

import com.example.demo8.model.Organization;
import com.example.demo8.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findByOrgName(String name);
    List<Organization> findByUser(User user);
}
