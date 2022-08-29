package com.mysite.sbb.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class SiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // unique = true는 유일한 값만 저장할 수 있음을 의미
    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

}
