package com.kitchenstory.controller;

import com.kitchenstory.entity.CartEntity;
import com.kitchenstory.entity.DishEntity;
import com.kitchenstory.entity.OrderEntity;
import com.kitchenstory.entity.UserEntity;
import com.kitchenstory.exceptions.UserNotFoundException;
import com.kitchenstory.service.CartService;
import com.kitchenstory.service.OrderService;
import com.kitchenstory.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private final CartService cartService;
    private final UserService userService;
    private final OrderService orderService;
    private final HttpServletRequest request;

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('READ')")
    public String one(@PathVariable String id, Model model) {
        final OrderEntity order = orderService.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Order with id: " + id + " not found."));
        model.addAttribute("order", order);
        return "order";
    }

    @GetMapping("cart")
    @PreAuthorize("hasAuthority('READ')")
    public String cart(OrderEntity orderEntity) {
        return "payment";
    }

    @GetMapping("all")
    @PreAuthorize("hasAuthority('READ')")
    public String all(Model model) {
        List<OrderEntity> orders = new ArrayList<>();
        final boolean is_admin = request.isUserInRole("ROLE_ADMIN");
        if (is_admin)
            orders.addAll(orderService.findAll());
        else {
            final String email = request.getUserPrincipal().getName();
            final UserEntity user = userService.findByEmail(email)
                    .orElseThrow(() -> new UserNotFoundException("User with Email Id: " + email + " not found."));

            orders.addAll(user.getOrders());
        }
        model.addAttribute("orders", orders);
        return "orders";
    }

    @PostMapping("add")
    @PreAuthorize("hasAuthority('READ')")
    public String add(@Valid OrderEntity orderEntity, BindingResult result, Model model) {

        //        Return the payment page if there are any errors
        if (result.hasErrors())
            return "payment";

        final String email = request.getUserPrincipal().getName();
        final UserEntity user = userService.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with Email Id: " + email + " not found."));

        final CartEntity cart = user.getCart();

        final List<DishEntity> dishes = cart.getDishes();

        //        Get the bill amount of all the dishes user is trying to order
        final Double billAmount = dishes.stream()
                .map(dish -> dish.getPrice())
                .mapToDouble(Double::doubleValue)
                .sum();

        //        Save the order in database
        final OrderEntity order = new OrderEntity(orderEntity.getType(), orderEntity.getNameOnCard(),
                orderEntity.getNumber(), orderEntity.getMonth(), orderEntity.getYear(), orderEntity.getCvv(), billAmount,
                dishes.size(), new Date(), dishes.stream().collect(Collectors.toList()), user);

//        Save order in database
        orderService.save(order);

        //        Delete the cart
        cartService.deleteById(cart.getId());

        model.addAttribute("order", order);

        return "receipt";
    }
}
