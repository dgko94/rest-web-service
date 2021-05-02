package com.example.restfulwebservice.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService){
        this.userDaoService=userDaoService;
    }

    //사용자 전체조회
    @GetMapping("users")
    public List<User> retrieveAllUsers(){
        return  userDaoService.findAll();
    }

    //사용자 한명조회
    @GetMapping("users/{id}")
    public User retrieveUser(@PathVariable int id){
        User user = userDaoService.findOne(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        return user;
    }

    @PostMapping("users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User saveUser = userDaoService.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveUser.getId())
                .toUri();
        return  ResponseEntity.created(location).build();
    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = userDaoService.deleteById(id);

        //삭제요청한 데이터가 없다는뜻
        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
    }

}
