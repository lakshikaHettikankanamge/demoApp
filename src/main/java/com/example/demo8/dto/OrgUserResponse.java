package com.example.demo8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrgUserResponse {
    private Long id;
    private String email;
    private String url;
    private String description;
    private String userName;
    private String organizationName;
}
