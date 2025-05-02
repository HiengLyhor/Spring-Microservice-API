package com.jwt.token.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@Table(name = "JwtUsers")
public class UserEntity {

    @Id
    @Column(name = "USERNAME")
    String username;

    @Column(name = "PASSWORD")
    String password;

    @Column(name = "ROLE")
    String role;

    @Column(name = "CREATE_DATE")
    Timestamp createDate;

    @Column(name = "EXPIRE_DATE")
    Timestamp expDate;

    @Column(name = "ACC_LOCK")
    String accLock;

    @Column(name = "REQUESTER")
    String requester;

    @PrePersist
    public void beforeSave() {
        this.createDate  = new Timestamp(System.currentTimeMillis());
        this.expDate = new Timestamp(System.currentTimeMillis() + 604800000); // 7 days
        this.accLock = "N";
        this.role = "USER";
    }

}
