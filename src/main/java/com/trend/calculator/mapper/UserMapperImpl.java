package com.trend.calculator.mapper;

import com.trend.calculator.model.User;
import com.trend.calculator.rest.dto.UserDto;
import org.springframework.stereotype.Service;


@Service
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDto(user.getId(), user.getUsername(), user.getName(), user.getEmail(), user.getRole());
    }

}
