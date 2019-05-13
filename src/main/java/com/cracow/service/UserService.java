package com.cracow.service;

import com.cracow.dto.output.UserNewDto;
import com.cracow.entity.UserEntity;
import com.cracow.error.common.ConflictProblem;
import com.cracow.error.common.NotFoundProblem;
import com.cracow.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
    }

    public void registerNewUser(UserNewDto userNewDto) {
        String email = userNewDto.getEmail();
        String password = userNewDto.getPassword();

        if (userRepository.findByEmail(email).isPresent()) {
            throw new ConflictProblem("user", "email", email);
        }

        UserEntity userEntity = dtoToEntity(userNewDto);
        userEntity.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(userEntity);
    }

    public UserEntity findByEmailOrThrow404(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundProblem("user", "email", email));
    }

    private UserEntity dtoToEntity(UserNewDto userNewDto) {
        UserEntity userEntity = modelMapper.map(userNewDto, UserEntity.class);
        return userEntity;
    }

}