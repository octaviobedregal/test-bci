package com.bci.test.services.impls;

import com.bci.test.models.UserRequest;
import com.bci.test.models.UserResponse;
import com.bci.test.models.api.AuthRequest;
import com.bci.test.models.api.AuthResponse;
import com.bci.test.models.entity.PhoneEntity;
import com.bci.test.models.entity.UserEntity;
import com.bci.test.repository.PhoneRepository;
import com.bci.test.repository.UserRepository;
import com.bci.test.services.AuthService;
import com.bci.test.services.UserService;
import com.bci.test.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Objects;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private AuthService authService;


    @SneakyThrows
    @Override
    public UserResponse createUser(UserRequest userRequest) {

        if(repository.findByEmail(userRequest.getEmail()).isPresent()){
            throw new Exception(Utils.MESSAGE_ERROR_EMAIL);
        }

        AuthResponse authResponse = authService.login(AuthRequest.builder()
                .email(userRequest.getEmail()).build());

        UserEntity userEntity = repository.save(UserEntity.builder()
                .created(new Date(System.currentTimeMillis()))
                .email(userRequest.getEmail())
                .isActive(true)
                .lastLogin(Objects.isNull(SecurityContextHolder.getContext().getAuthentication())? "" :
                        SecurityContextHolder.getContext().getAuthentication().getName())
                .modified(new Date(System.currentTimeMillis()))
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .token(Objects.isNull(authResponse)?"":authResponse.getAccessToken())
                .build());

        userRequest.getPhones().stream().forEach(p -> {
            phoneRepository.save(PhoneEntity.builder()
                    .citycode(p.getCitycode())
                    .contrycode(p.getContrycode())
                    .number(p.getNumber())
                    .idUsuario(userEntity.getId()).build());
        });


        return UserResponse.builder()
                .id(userEntity.getId())
                .created(Utils.DATE_FORMAT.format(userEntity.getCreated()))
                .modified(Utils.DATE_FORMAT.format(userEntity.getModified()))
                .lastLogin(userEntity.getLastLogin())
                .token(userEntity.getToken())
                .isactive(userEntity.getIsActive())
                .build();

    }

}
