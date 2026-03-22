package com.fullhouse.DTOs;

public class BusinessInListDTO {
    private long id;
    private String name;
    private Long ownerId;

    public BusinessInListDTO(long id, String name, Long ownerId) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getOwnerId() {
        return ownerId;
    }
}