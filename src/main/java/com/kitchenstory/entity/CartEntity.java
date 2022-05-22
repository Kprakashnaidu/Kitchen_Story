package com.kitchenstory.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@DynamicUpdate
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "cart")
public class CartEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ToString.Exclude
    @ManyToMany(targetEntity = DishEntity.class, cascade = CascadeType.ALL)
    @JoinTable(name = "cart_dishes",
            joinColumns = @JoinColumn(name = "cart_id", unique = false),
            inverseJoinColumns = @JoinColumn(name = "dish_id", unique = false))
    private List<DishEntity> dishes;

    @ToString.Exclude
    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public CartEntity(List<DishEntity> dishes, UserEntity user) {
        this.dishes = dishes;
        this.user = user;
    }
}
