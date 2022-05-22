package com.kitchenstory.validator;

import com.kitchenstory.config.BeanUtil;
import com.kitchenstory.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailIdValidator implements ConstraintValidator<UniqueEmailId, String> {

    private UserService userService;
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueEmailId constraintAnnotation) {
        userService = BeanUtil.getBean(UserService.class);
        entityManager = BeanUtil.getBean(EntityManager.class);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isValid = false;
        try {
            entityManager.setFlushMode(FlushModeType.COMMIT);
            //your code
            isValid = userService.findByEmail(value).isPresent() ? false : true;
        } finally {
            entityManager.setFlushMode(FlushModeType.AUTO);
        }
        return isValid;
    }
}
