package com.jeferro.products.users.users.infrastructure.rest.dtos;

import java.util.List;

public record AuthRestDTO(
        String username,
        List<String> roles
) {

}
