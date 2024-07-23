package com.trend.calculator.mapper;

import com.trend.calculator.model.User;
import com.trend.calculator.rest.dto.UserDto;

public interface UserMapper {

    UserDto toUserDto(User user);
}