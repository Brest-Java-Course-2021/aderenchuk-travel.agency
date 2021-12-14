package com.aderenchuk.brest.model;

public enum Permission {
    TOURS_READ("tours:read"),
    TOURS_WRITE("tours:write"),
    CLIENTS_READ("clients:read"),
    CLIENTS_WRITE("clients:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
