package com.bci.test.services;

import com.bci.test.models.UserRequest;
import com.bci.test.models.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest user);

}
