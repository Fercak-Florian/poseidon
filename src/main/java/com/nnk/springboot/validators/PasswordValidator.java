package com.nnk.springboot.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {
	
	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[*.@#$%^&+=()!])"
                + "(?=\\S+$).{8,70}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

}
