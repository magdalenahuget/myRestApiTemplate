package com.company.user.service;

import com.company.role.type.RoleType;
import com.company.user.dto.*;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    List<GetUserResponseDto> getUsers(RoleType roleType);

    CreateUserResponseDto createUser(CreateUserRequestDto userDto, RoleType roleType);

    CreateUserResponseDto updateUser(Long userId, CreateUserRequestDto userDto);

    GetUserResponseDto getUserById(Long userId);

    GetAboutMeResponseDto getAboutMeByUserId(Long userId);

    CreateAboutMeResponseDto updateUserByAboutMe(Long userId, CreateAboutMeRequestDto aboutMe);

    void deleteUser(Long userId);


    CreateAvatarResponseDto createAvatar(MultipartFile file, CreateAvatarRequestDto request) throws FileUploadException;

    Authentication getAuthentication(SignInRequestDto user);

    String getSecurityContextAndJwt(SignInRequestDto loginRequest, Authentication authentication);

    org.springframework.security.core.userdetails.User getUserDetails(Authentication authentication);

    List<String> getRoles(org.springframework.security.core.userdetails.User userDetails);

    SignInResponseDto createJwtResponse(String jwt, String username, List<String> roles);

}
