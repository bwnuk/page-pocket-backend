package com.cracow.service;

import com.cracow.dto.input.UserNewDto;
import com.cracow.entity.UserEntity;
import com.cracow.error.common.ConflictProblem;
import com.cracow.error.common.NotFoundProblem;
import com.cracow.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void registerNewUser(UserNewDto userNewDto) {
        String email = userNewDto.getEmail();
        String password = userNewDto.getPassword();

        throw409IfUserAlreadyExist(email);

        UserEntity userEntity = new UserEntity(userNewDto, bCryptPasswordEncoder.encode(password));
        userRepository.save(userEntity);
    }

    public void saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    private void throw409IfUserAlreadyExist(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new ConflictProblem("user", "email", email);
        }
    }

    public UserEntity findByEmailOrThrow404(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundProblem("user", "email", email));
    }
}