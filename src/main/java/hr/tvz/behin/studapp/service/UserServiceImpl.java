package hr.tvz.behin.studapp.service;

import hr.tvz.behin.studapp.domain.Authority;
import hr.tvz.behin.studapp.domain.User;
import hr.tvz.behin.studapp.dto.UserDTO;
import hr.tvz.behin.studapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public Optional<UserDTO> findByUsername(String username) {
        return repository.findOneByUsername(username)
                .map(this::mapUserToDTO);

    }

    public UserDTO mapUserToDTO(User user){
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getFirst_name(),
                user.getLast_name(),
                user.getAuthorities().stream()
                        .map(Authority::getName)
                        .collect(Collectors.toList())
        );
    }
}
