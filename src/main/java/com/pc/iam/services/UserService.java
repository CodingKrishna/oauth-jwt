package com.pc.iam.services;

import com.pc.iam.dtos.UserRequest;
import com.pc.iam.dtos.UserResponse;

import java.util.List;


public interface UserService {

    UserResponse saveUser(UserRequest userRequest);

    UserResponse getUser();

    List<UserResponse> getAllUser();


}
