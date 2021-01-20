package com.example.demo8.controller;

import com.example.demo8.dto.OrgUserRequest;
import com.example.demo8.dto.OrgUserResponse;
import com.example.demo8.Service.OrgUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/")
@AllArgsConstructor
public class OrgUserController {
    private final OrgUserService OrgUserService;

//    @PostMapping
//    public ResponseEntity<Void> createOrguser(@RequestBody OrgUserRequest orgUserRequest) {
//        OrgUserService.save(orgUserRequest);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<OrgUserResponse>> getAllOrgUsers() {
//        return status(HttpStatus.OK).body(OrgUserService.getAllOrgUser());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<OrgUserResponse> getOrgUser(@PathVariable Long id) {
//        return status(HttpStatus.OK).body(OrgUserService.getOrgUser(id));
//    }
//
//    @GetMapping("by-subreddit/{id}")
//    public ResponseEntity<List<OrgUserResponse>> getOrgUserByOrganization(Long id) {
//        return status(HttpStatus.OK).body(OrgUserService.getOrgUsersByOrganization(id));
//    }
//
//    @GetMapping("by-user/{name}")
//    public ResponseEntity<List<OrgUserResponse>> getOrgUserByUsername(String username) {
//        return status(HttpStatus.OK).body(OrgUserService.getOrgUsersByUsername(username));
//    }
}
