package com.restaurant.management.domain.ecommerce.dto;

import java.util.Set;

public final class AccountUserDto {
    private Long createdAt;
    private Long updatedAt;
    private String createdByUserId;
    private String updatedByUserId;
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String emailVerificationToken;
    private Boolean isActive;
    private Set<RoleDto> roles;

    public AccountUserDto(Long createdAt, Long updatedAt, String createdByUserId, String updatedByUserId,
                          Long id, String name, String lastName,
                          String email, String phone,
                          String emailVerificationToken,
                          Boolean isActive, Set<RoleDto> roles) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdByUserId = createdByUserId;
        this.updatedByUserId = updatedByUserId;
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.emailVerificationToken = emailVerificationToken;
        this.isActive = isActive;
        this.roles = roles;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedByUserId() {
        return createdByUserId;
    }

    public String getUpdatedByUserId() {
        return updatedByUserId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmailVerificationToken() {
        return emailVerificationToken;
    }

    public Boolean isActive() {
        return isActive;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }
}