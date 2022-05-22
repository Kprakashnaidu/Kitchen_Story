package com.kitchenstory.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@DynamicUpdate
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "orders")
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @NotBlank(message = "Select your card")
    @Column(length = 20)
    private String type;

    @NotBlank(message = "Name on card cannot be null")
    @Column(length = 50)
    private String nameOnCard;

    @Size(min = 12, max = 12, message = "Card Number must be 12 digit number")
    @Column(length = 12)
    private String number;

    @Column(length = 10)
    private String month;

    @Column(length = 4)
    private Integer year;

    @Size(min = 3, max = 3, message = "CVV must be 3 digit number")
    @Column(length = 3)
    private String cvv;

    @Column(name = "bill_amount")
    private Double billAmount;

    @Column(length = 5)
    private Integer quantity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ToString.Exclude
    @ManyToMany(targetEntity = DishEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "order_dishes",
            joinColumns = @JoinColumn(name = "order_id", unique = false),
            inverseJoinColumns = @JoinColumn(name = "dish_id", unique = false))
    private List<DishEntity> dishes;

    @ToString.Exclude
    @ManyToOne(targetEntity = UserEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public OrderEntity(Double billAmount, Integer quantity, Date date, List<DishEntity> dishes, UserEntity user) {
        this.billAmount = billAmount;
        this.quantity = quantity;
        this.date = date;
        this.dishes = dishes;
        this.user = user;
    }

    public OrderEntity(String type, String nameOnCard, String number, String month, Integer year, String cvv,
                       Double billAmount, Integer quantity, Date date, List<DishEntity> dishes, UserEntity user) {
        this.type = type;
        this.nameOnCard = nameOnCard;
        this.number = number;
        this.month = month;
        this.year = year;
        this.cvv = cvv;
        this.billAmount = billAmount;
        this.quantity = quantity;
        this.date = date;
        this.dishes = dishes;
        this.user = user;
    }
}