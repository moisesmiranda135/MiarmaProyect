package com.salesianos.triana.dam.MiarmaProyect.validations.simple.validations;

import com.salesianos.triana.dam.MiarmaProyect.users.repos.UsuarioRepository;
import com.salesianos.triana.dam.MiarmaProyect.validations.simple.anotations.UniqueUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserValidator implements ConstraintValidator<UniqueUser, String> {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public void initialize(UniqueUser constraintAnnotation) { }

    @Override
    public boolean isValid(String nickname, ConstraintValidatorContext context) {
        return StringUtils.hasText(nickname) && !repository.existsByNickName(nickname);
    }
}
