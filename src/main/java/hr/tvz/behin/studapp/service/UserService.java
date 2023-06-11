package hr.tvz.behin.studapp.service;

import hr.tvz.behin.studapp.dto.UserDTO;

import java.util.Optional;

public interface UserService {

    Optional<UserDTO> findByUsername(String username);

}
