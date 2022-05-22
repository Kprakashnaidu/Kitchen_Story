package com.kitchenstory.controller;

import com.kitchenstory.entity.UserEntity;
import com.kitchenstory.exceptions.UserNotFoundException;
import com.kitchenstory.model.UserRole;
import com.kitchenstory.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final HttpServletRequest request;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('READ')")
    public String user(@PathVariable String id, Model model) {

        final UserEntity user = userService.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with Id: " + id + " not found"));
        final String email = request.getUserPrincipal().getName();

        if (!email.equals(user.getEmail()))
            if (!request.isUserInRole("ROLE_ADMIN"))
                return "redirect:/index?user-unauthorized=true";

        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("all")
    @PreAuthorize("hasAuthority('READ')")
    public String allUsers(Model model) {
        final boolean is_admin = request.isUserInRole("ROLE_ADMIN");
        if (is_admin)
            model.addAttribute("users", userService.findAll());
        else {
            final String email = request.getUserPrincipal().getName();
            final UserEntity user = userService.findByEmail(email)
                    .orElseThrow(() -> new UserNotFoundException("User with Email Id: " + email + " not found."));
            model.addAttribute("user", user);
            return "user";
        }
        return "users";
    }

    @GetMapping("edit/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public String editUser(@PathVariable String id, Model model) {
        final UserEntity user = userService.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with Id: " + id + " not found"));
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("update/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public String updateUser(@Valid @ModelAttribute("user") UserEntity userEntity,
                             @PathVariable String id, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "update-user";
        final UserEntity user = userService.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with Id: " + id + " not found"));

        user.setEmail(userEntity.getEmail())
                .setName(userEntity.getName())
                .setGender(userEntity.getGender())
                .setAddress(userEntity.getAddress())
                .setCity(userEntity.getCity())
                .setState(userEntity.getState())
                .setZipcode(userEntity.getZipcode())
                .setTerms(userEntity.getTerms())
                .setAccountNonExpired(userEntity.getIsAccountNonExpired())
                .setCredentialsNonExpired(userEntity.getIsCredentialsNonExpired())
                .setAccountNonLocked(userEntity.getIsAccountNonLocked())
                .setEnabled(userEntity.getIsEnabled())
                .setPassword(userEntity.getPassword());

        userService.save(user);
        return "redirect:/user/all?update-user=true";
    }

    @GetMapping("sign-up")
    public String signUp(@ModelAttribute("userEntity") UserEntity userEntity, Model model) {
        String user = null;
        try {
            user = request.getUserPrincipal().getName();
        } catch (Exception e) {
            user = null;
        }
        if (user == null)
            return "sign-up";
        else
            return "redirect:/index?user-exists=true";
    }

    @PostMapping("sign-up")
    public String signUp(@Valid UserEntity userEntity, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors())
            return "sign-up";
        if (userService.findByEmail(userEntity.getEmail()).isPresent()) {
            attributes.addFlashAttribute("userEntity", userEntity);
            return "redirect:/user/sign-up?emailIdExists=true";
        }

        if (userEntity.getEmail().equals("prakashnaidupn7@gmail.com"))
            userEntity.setUserRole(UserRole.ROLE_ADMIN);
        else
            userEntity.setUserRole(UserRole.ROLE_USER);

        userEntity.setAccountNonExpired(true)
                .setAccountNonLocked(true)
                .setCredentialsNonExpired(true)
                .setEnabled(true)
                .setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userService.save(userEntity);
        return "redirect:index/?sign-up=true";
    }

    @PostMapping("/change-password")
    @PreAuthorize("hasAuthority('READ')")
    public String changePassword(@RequestParam String password, @RequestParam String confirmPassword) {
        System.out.println(password + " " + confirmPassword);
        if (!password.equals(confirmPassword))
            return "redirect:/index?password-not-matching=true";
        final String email = request.getUserPrincipal().getName();
        final UserEntity user = userService.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with Email Id: " + email + " not found."));
        user.setPassword(passwordEncoder.encode(password));
        userService.save(user);
        return "redirect:/index?change-password=true";
    }

    @GetMapping("forgot-password")
    public String forgotPassword() {
        String user = null;
        try {
            user = request.getUserPrincipal().getName();
        } catch (Exception e) {
            user = null;
        }
        if (user == null)
            return "forgot-password";
        else
            return "redirect:/index?user-exists=true";
    }

    @PostMapping("forgot-password")
    public String forgotPassword(@RequestParam String email, @RequestParam String password) {
        final Optional<UserEntity> user = userService.findByEmail(email);
        if (!user.isPresent())
            return "redirect:/user/forgot-password?emailNotMatching=true";
        user.get().setPassword(passwordEncoder.encode(password));
        userService.save(user.get());
        return "redirect:/?password-reset=true";
    }
}
