package com.gobuy.apisprintbootgobuy.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=50)
    private String name;

    @NotNull
    @Size(max = 50)
    private String lastName;

    @NotNull
    @Column(unique = true)
    private  String email;

    @NotNull
    private String password;

    @NotNull
    @Lob
    @Size(max = 245)
    private String description;

    @NotNull
    private String profilePhoto;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "role_id",nullable = false)
    @JsonIgnore
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "purchase_id",nullable = false)
    @JsonIgnore
    private Purchase purchase;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "comment_id",nullable = false)
    @JsonIgnore
    private Comment comment;

    public User() {
    }

    public User(@NotNull String name,@NotNull String lastName,@NotNull String email,@NotNull String password,@NotNull String description,@NotNull String profilePhoto) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.description = description;
        this.profilePhoto = profilePhoto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
}
