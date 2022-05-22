package com.kitchenstory.config;

import com.kitchenstory.entity.CartEntity;
import com.kitchenstory.entity.DishEntity;
import com.kitchenstory.entity.UserEntity;
import com.kitchenstory.exceptions.UserNotFoundException;
import com.kitchenstory.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ControllerAdvice
@AllArgsConstructor
public class ModelData {

    private final UserService userService;
    private final HttpServletRequest request;

    @ModelAttribute("gender")
    public List<String> genders() {
        return Arrays.asList("Male", "Female");
    }

    @ModelAttribute("statements")
    public List<Boolean> statements() {
        return Arrays.asList(true, false);
    }

    @ModelAttribute("roles")
    public List<String> roles() {
        return Arrays.asList("ROLE_USER", "ROLE_ADMIN", "ROLE_MANAGER");
    }

    @ModelAttribute("states")
    public List<String> states() {
        return Arrays.asList("AP", "Karnataka", "TamilNadu", "Kerala");
    }

    @ModelAttribute("dishTypes")
    public List<String> dishType() {
        return Arrays.asList("Veg-Starter", "Egg-Starter", "Chicken-Starter",
                "Mutton-Starter", "Platter-Starter", "Executive-Thali",
                "Veg-Soup", "Chicken-Soup", "Veg-Main-Course", "Paneer-Main-Course",
                "Egg-Main-Course", "Chicken-Main-Course", "Mutton-Main-Course",
                "Dal-Main-Course", "Biriyani-Main-Course", "Rice-Main-Course",
                "Fried-Rice", "Dessert");
    }

    @ModelAttribute("spicy")
    public List<String> spicy() {
        return Arrays.asList("Normal", "Spicy", "Extra Chilly");
    }

    @ModelAttribute("special")
    public List<String> special() {
        return Arrays.asList("Normal", "Special", "Chef Special");
    }

    @ModelAttribute("count")
    public Integer countOfDishesInCart() {
        return getDishes().isPresent() ? getDishes().get().size() : 0;
    }

    @ModelAttribute("dishes")
    public List<DishEntity> listOfDishes() {
        return getDishes().isPresent() ? getDishes().get() : null;
    }

    @ModelAttribute("total")
    public Double totalAmountOfAllDishes() {
        return getDishes().isPresent() ? getDishes().get()
                .stream()
                .map(dish -> dish.getPrice())
                .mapToDouble(Double::doubleValue)
                .sum() : 0.0;
    }

    @ModelAttribute("cards")
    public List<String> cards() {
        return Arrays.asList("Credit", "Debit");
    }

    private Optional<List<DishEntity>> getDishes() {

        final String uri = request.getRequestURI();
        if (uri.contains("/order/cart") || uri.contains("/order/add")) {
            final List<DishEntity> dishes = new ArrayList<>();

            try {
                final String email = request.getUserPrincipal().getName();
                final UserEntity user = userService.findByEmail(email)
                        .orElseThrow(() -> new UserNotFoundException("User with Email Id: " + email + " not found."));

                final Optional<CartEntity> cart = Optional.of(user.getCart());

                if (cart.isPresent())
                    dishes.addAll(cart.get().getDishes());

                return Optional.of(dishes);
            } catch (Exception exception) {
                return Optional.of(new ArrayList<>());
            }
        }
        return Optional.of(new ArrayList<>());
    }
}
