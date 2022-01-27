package com.example.polls.model;

public interface IdLong {
    Long getId();
    void setId(Long id);

    default void idZeroToNull() {
        if (getId() != null && getId() == 0L) setId(null);
    }

}
