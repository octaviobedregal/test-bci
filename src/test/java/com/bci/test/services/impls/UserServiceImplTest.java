package com.bci.test.services.impls;

import com.bci.test.models.UserRequest;
import com.bci.test.models.UserResponse;
import com.bci.test.models.entity.UserEntity;
import com.bci.test.repository.PhoneRepository;
import com.bci.test.repository.UserRepository;
import com.bci.test.services.AuthService;
import com.bci.test.utils.Utils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private UserRequest request;

    @Mock
    private UserRepository repository;

    @Mock
    private PhoneRepository phoneRepository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private UserServiceImpl service;

    @BeforeAll
    void setup(){
        request = UserRequest.builder()
                .name("")
                .email("")
                .phones(new ArrayList<>())
                .build();
    }

    @Test
    @DisplayName("Return entity user when send request user")
    void returnEntityUserWhenSendRequestUser() {

        UserEntity responseExpected = UserEntity.builder()
                .created(new Date(System.currentTimeMillis()))
                .modified(new Date(System.currentTimeMillis()))
                .build();

        when(repository.save(any(UserEntity.class)))
                .thenReturn(responseExpected);

        UserResponse response = service.createUser(request);

        Assertions.assertEquals(response.getCreated(),
                Utils.DATE_FORMAT.format(responseExpected.getCreated()));
    }

    @Test
    @DisplayName("Return error when send email repeat")
    void returnErrorWhenSendEmailRepeat() {

        when(repository.findByEmail(anyString()))
                .thenReturn(Optional.of(new UserEntity()));

        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            service.createUser(request);
        });

        Assertions.assertEquals(thrown.getMessage(), Utils.MESSAGE_ERROR_EMAIL);

    }
}