package com.shareskills.api.service;

//import com.cloudinary.utils.ObjectUtils;
import com.shareskills.api.exception.BadRequestException;
import com.shareskills.api.mapper.UserMapper;
import com.shareskills.api.model.User;
import com.shareskills.api.model.dto.UserDTO;
import com.shareskills.api.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper = new UserMapper();

    //@Autowired
    //private FileService fileService;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> getAdminColumns() {
        return userMapper.getAdminColumns();
    }

    public List<String> getColumns() {
        return userMapper.getColumns();
    }

    public User getUserConnected() {
        UserDetails userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByEmail(userDetails.getUsername());
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new BadRequestException("User not found");
        }
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new BadRequestException("User not found");
        }
        return user.get();
    }

    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new BadRequestException("User not found");
        }
        return user.get();
    }

    public User createUser(UserDTO userDTO) {
        return userRepository.save(userMapper.toEntity(userDTO));
    }

    public User updateUser(UserDTO userDTO) {
        return userRepository.save(userMapper.toEntity(userDTO));
    }

    public void deleteUser(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new BadRequestException("User not found");
        }
        userRepository.delete(optionalUser.get());
    }
}
