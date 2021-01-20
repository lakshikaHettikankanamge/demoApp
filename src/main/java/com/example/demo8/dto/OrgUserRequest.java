package com.example.demo8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrgUserRequest {
    private Long orgUserId;
    private String organizationName;
    private String orgUserName;
}
