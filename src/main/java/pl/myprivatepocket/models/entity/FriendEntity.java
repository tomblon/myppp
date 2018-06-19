package pl.myprivatepocket.models.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class FriendEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String email;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "user_id"))
    private UserEntity userEntity;
}
