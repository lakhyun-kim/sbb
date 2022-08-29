package com.mysite.sbb.user;

import lombok.Getter;

@Getter
// 열거 자료형(enum)
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}
