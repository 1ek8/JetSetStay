package com.byaniket.jetsetstay.dto;

import com.byaniket.jetsetstay.entity.User;
import com.byaniket.jetsetstay.enums.Gender;
import jakarta.persistence.*;

public record GuestDTO(

        Long Id,

        User user,

        String name,

        Gender gender,

        Integer age
) {
}
