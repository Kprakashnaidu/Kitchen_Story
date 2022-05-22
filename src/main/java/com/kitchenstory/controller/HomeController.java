package com.kitchenstory.controller;

import com.kitchenstory.entity.DishEntity;
import com.kitchenstory.service.DishService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {

    private final DishService dishService;

    @GetMapping({"/", "index"})
    public String index(Model model) {
        final List<DishEntity> dishes = new ArrayList<>();
        final List<DishEntity> randomDishes = dishService.findTenRandomDishes();
        final List<DishEntity> fourRandomDishes = randomDishes.subList(1, 5);

        model.addAttribute("dishes", dishes);
        model.addAttribute("randomDishes", randomDishes);
        model.addAttribute("fourRandomDishes", fourRandomDishes);

        return "index";
    }

    @GetMapping("/home")
    public String index(@RequestParam("dish") String dish, Model model) {

        final List<DishEntity> dishes = new ArrayList<>();
        final List<DishEntity> randomDishes = dishService.findTenRandomDishes();
        final List<DishEntity> fourRandomDishes = randomDishes.subList(1, 5);

        if (dish != null && dish.length() > 2)
            dishes.addAll(dishService.findByNameContainingIgnoreCase(dish));
        else
            return "redirect:/index?dish-name=false";
        model.addAttribute("dishes", dishes);
        model.addAttribute("randomDishes", randomDishes);
        model.addAttribute("fourRandomDishes", fourRandomDishes);

        return "index";
    }

    @GetMapping("menu")
    public String menu(Model model) {
        final List<DishEntity> dishEntities = dishService.findAll();

        final Map<String, Integer> typeCount = dishEntities.stream()
                .collect(Collectors.groupingBy(DishEntity::getType, Collectors.summingInt(dish -> 1)));

        model.addAttribute("dishes", dishEntities);
        model.addAttribute("typeCount", typeCount);

        return "menu";
    }

    @GetMapping("login")
    public String login(Principal principal) {
        String user = null;
        try {
            user = principal.getName();
        } catch (Exception e) {
            user = null;
        }
        if (user == null)
            return "sign-in";
        else
            return "redirect:/index?user-exists=true";
    }

}
