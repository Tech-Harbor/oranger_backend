package com.example.backend.User;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    private static final String CREATE_USER = "/user";
    private static final String USERs = "/users";
    private static final String ID_USER_UPDATE_GET = "/user/{id}";
    private static final String DELETE_USER_ID = "/user/delete/{id}";


    @PostMapping(CREATE_USER)
    private UserDTO createUser(@RequestBody UserEntity user){
        return userService.createUser(user);
    }

    @GetMapping(USERs)
    public List<UserDTO> getAllUsers(){
        return userService.getByAllUser();
    }

    @PutMapping(ID_USER_UPDATE_GET)
    public UserDTO updateByIdUser(@PathVariable Long id, @RequestBody UserEntity user){
        return userService.updateByIdUser(id, user);
    }

    @GetMapping(ID_USER_UPDATE_GET)
    public UserDTO getById(@PathVariable Long id){
        return userService.getByIdUser(id);
    }

    @DeleteMapping(DELETE_USER_ID)
    public String deleteId(@PathVariable Long id){
        userService.deleteByIdUser(id);
        return "Видалений користувач" + id;
    }
}