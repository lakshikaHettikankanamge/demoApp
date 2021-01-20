package com.example.demo8.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Organization   {
        @Id
        @GeneratedValue(strategy = SEQUENCE)
        private Long orgId;
        @NotBlank(message = " Name cannot be empty or Null")
        private String orgName;
        @Nullable
        @Lob
        private String description;
        @ManyToOne(fetch = LAZY)
        @JoinColumn(name = "userId", referencedColumnName = "userId")
        private User user;
        private Instant createdDate;
//        @OneToMany(fetch = LAZY)
//        private List<OrgUser> orgUsers;
        
}
