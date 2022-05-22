package com.kitchenstory.validator;

import com.kitchenstory.config.BeanUtil;
import com.kitchenstory.service.DishService;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UniqueDishNameValidator implements ConstraintValidator<UniqueDishName, String> {

    private DishService dishService;
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueDishName constraintAnnotation) {
        dishService = BeanUtil.getBean(DishService.class);
        entityManager = BeanUtil.getBean(EntityManager.class);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isValid = false;
        try {
            entityManager.setFlushMode(FlushModeType.COMMIT);
            //your code
            isValid = dishService.findByName(value).isPresent() ? false : true;
        } finally {
            entityManager.setFlushMode(FlushModeType.AUTO);
        }
        return isValid;
    }
}
