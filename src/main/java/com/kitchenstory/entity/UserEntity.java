package com.kitchenstory.entity;

import com.kitchenstory.model.UserRole;
import com.kitchenstory.validator.UniqueEmailId;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "users")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Column(length = 50)
    @Size(min = 4, max = 30, message = "{user.name.invalid}")
    @NotEmpty(message = "Please enter name")
    private String name;

    @Column(nullable = false, length = 100, unique = true, name = "email")
    @Email(message = "{user.email.invalid}")
    @NotEmpty(message = "Please enter email")
//    @UniqueEmailId(message = "User with email id already exists")
    private String email;

    @Column(length = 10)
    @NotEmpty(message = "Please select your Gender")
    private String gender;

    @Column(length = 100)
    @Size(min = 4, message = "Please create a strong password")
    @NotEmpty(message = "Please enter password")
    private String password;

    @Column(length = 50)
    @Size(min = 4, max = 50, message = "Please enter valid address")
    @NotEmpty(message = "Please enter address")
    private String address;

    @Column(length = 20)
    @Size(min = 4, max = 20, message = "Please provide a valid city")
    @NotEmpty(message = "Please enter city")
    private String city;

    @Column(length = 20)
    @Size(min = 4, max = 20, message = "Please select a valid state")
    @NotEmpty(message = "Please enter state")
    private String state;

    @Column(length = 10)
    @Size(min = 6, max = 6, message = "Please provide a valid zipcode")
    @NotEmpty(message = "Please enter zipcode")
    private String zipcode;

    @NotNull(message = "please accept the terms and conditions")
    @AssertTrue
    private Boolean terms;

    @Column(name = "is_account_non_expired")
    private Boolean isAccountNonExpired;

    @Column(name = "is_account_non_locked")
    private Boolean isAccountNonLocked;

    @Column(name = "is_credentials_non_expired")
    private Boolean isCredentialsNonExpired;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @Column(name = "user_role", length = 10)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @ToString.Exclude
    @OneToMany(targetEntity = OrderEntity.class, fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderEntity> orders;

    @ToString.Exclude
    @OneToOne(targetEntity = CartEntity.class, fetch = FetchType.LAZY, mappedBy = "user")
    private CartEntity cart;

    public UserEntity(String name, String email, String gender, String password, String address, String city,
                      String state, String zipcode, Boolean terms, Boolean isAccountNonExpired,
                      Boolean isAccountNonLocked, Boolean isCredentialsNonExpired, Boolean isEnabled,
                      UserRole userRole, List<OrderEntity> orders, CartEntity cart) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.password = password;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.terms = terms;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        this.userRole = userRole;
        this.orders = orders;
        this.cart = cart;
    }

    public UserEntity(String name, String email, String gender, String password, String address, String city,
                      String state, String zipcode, Boolean terms, Boolean isAccountNonExpired,
                      Boolean isAccountNonLocked, Boolean isCredentialsNonExpired, Boolean isEnabled,
                      UserRole userRole) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.password = password;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.terms = terms;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        this.userRole = userRole;
    }

    public UserEntity setId(String id) {
        this.id = id;
        return this;
    }

    public UserEntity setName(String name) {
        this.name = name;
        return this;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserEntity setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserEntity setAddress(String address) {
        this.address = address;
        return this;
    }

    public UserEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public UserEntity setState(String state) {
        this.state = state;
        return this;
    }

    public UserEntity setZipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public UserEntity setTerms(Boolean terms) {
        this.terms = terms;
        return this;
    }

    public UserEntity setAccountNonExpired(Boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
        return this;
    }

    public UserEntity setAccountNonLocked(Boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
        return this;
    }

    public UserEntity setCredentialsNonExpired(Boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
        return this;
    }

    public UserEntity setEnabled(Boolean enabled) {
        isEnabled = enabled;
        return this;
    }

    public UserEntity setUserRole(UserRole userRole) {
        this.userRole = userRole;
        return this;
    }

    public UserEntity setOrders(List<OrderEntity> orders) {
        this.orders = orders;
        return this;
    }

    public UserEntity setCart(CartEntity cart) {
        this.cart = cart;
        return this;
    }
}
