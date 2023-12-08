package com.bci.test.services;

import com.bci.test.models.api.AuthRequest;
import com.bci.test.models.api.AuthResponse;

public interface AuthService {

    AuthResponse login(AuthRequest auth);

}
