package com.kitchenstory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserPermission {
    READ,
    WRITE,
    UPDATE,
    DELETE;
}
