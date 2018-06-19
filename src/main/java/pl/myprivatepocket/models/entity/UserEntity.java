package pl.myprivatepocket.models.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String userMail;

    @Column(nullable = false, length = 100)
    private String password;
}
