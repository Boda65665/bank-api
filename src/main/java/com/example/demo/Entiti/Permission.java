package com.example.demo.Entiti;

public enum Permission {
    USER("user"),
    BOT("bot"),
    ADMIN("admin");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}