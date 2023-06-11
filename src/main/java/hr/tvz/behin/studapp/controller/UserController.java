package hr.tvz.behin.studapp.controller;

import hr.tvz.behin.studapp.dto.UserDTO;
import hr.tvz.behin.studapp.security.SecurityUtils;
import hr.tvz.behin.studapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class UserController {
    UserService service;

    @GetMapping("/current-user")
    public ResponseEntity<UserDTO> getCurrentUser(){

        return SecurityUtils.getCurrentUserUsername()
                .map(
                        username -> service.findByUsername(username)
                                .map(ResponseEntity::ok)
                                .orElseGet(
                                    () -> ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build()
                                )
                ).orElseGet(
                        () -> ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build()
                );
    }
}
