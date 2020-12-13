package com.application.addressbook.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Set;

@Document
public class User {

    @Id
    private String userId;

    @NotNull(message = "User name cannot be null")
    private String name;

    private Set<Friend> addressBook = Collections.emptySet() ;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Friend> getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(Set<Friend> addressBook) {
        this.addressBook = addressBook;
    }


}
