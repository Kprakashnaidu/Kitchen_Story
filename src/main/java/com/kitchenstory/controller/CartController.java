package com.kitchenstory.controller;

import com.kitchenstory.entity.CartEntity;
import com.kitchenstory.entity.DishEntity;
import com.kitchenstory.entity.UserEntity;
import com.kitchenstory.exceptions.DishNotFoundException;
import com.kitchenstory.exceptions.UserNotFoundException;
import com.kitchenstory.service.CartService;
import com.kitchenstory.service.DishService;
import com.kitchenstory.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final DishService dishService;
    private final UserService userService;
    private final HttpServletRequest request;

    @GetMapping("add/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public String addDish(@PathVariable final String id) {

        final String email = request.getUserPrincipal().getName();

        final UserEntity user = userService.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with Email Id: " + email + " not found."));

        final DishEntity dish = dishService.findById(id)
                .orElseThrow(() -> new DishNotFoundException("Dish with id: " + id + " not found."));

        CartEntity cart = user.getCart();
        final List<DishEntity> dishes = new ArrayList<>();
        dishes.add(dish);
        if (cart == null)
            cart = new CartEntity(new ArrayList<>(dishes), user);
        else
            dishes.addAll(cart.getDishes());

        cart.setDishes(new ArrayList<>(dishes));
        cartService.save(cart);

        return "redirect:/menu?add-to-cart=true";
    }

    @GetMapping("delete/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public String deleteDish(@PathVariable final String id) {

        final String email = request.getUserPrincipal().getName();

        final UserEntity user = userService.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with Email Id: " + email + " not found."));

        final CartEntity cart = user.getCart();

        final List<DishEntity> dishes = cart.getDishes();

        final DishEntity dish = dishService.findById(id)
                .orElseThrow(() -> new DishNotFoundException("Dish with id: " + id + " not found."));

        dishes.remove(dish);

        cart.setDishes(dishes);
        cartService.save(cart);

        return "redirect:/menu?dish-removed=true";
    }

}
