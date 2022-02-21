package com.ex1mcs.microser.web;

import com.ex1mcs.microser.entities.User;
import com.ex1mcs.microser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping(path = "/users",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseEntity saveUsers(@RequestParam(value = "files") MultipartFile[] files) throws Exception {
        for (MultipartFile file:files) {
            userService.saveUser(file);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    @GetMapping(path="/users",produces = "application/json")
    public CompletableFuture<ResponseEntity> findAllUsers(){
       return userService.findAllusers().thenApply(ResponseEntity::ok);
    }
    @GetMapping(path="/usersbythread",produces = "application/json")
    public ResponseEntity getUsersbythreds(){
        CompletableFuture<List<User>> users1=userService.findAllusers();
        CompletableFuture<List<User>> users2=userService.findAllusers();
        CompletableFuture<List<User>> users3=userService.findAllusers();
        CompletableFuture.allOf(users1,users2,users3).join();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

