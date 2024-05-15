package com.app.HidIt.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_CLIENT,
    ROLE_TRAINER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
