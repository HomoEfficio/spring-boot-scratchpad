package homo.efficio.springboot.scratchpad.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author homo.efficio@gmail.com
 * created on 2018-04-04
 */
public class PeriodValidator implements ConstraintValidator<LimitSearchPeriod, ValidPeriod> {

    @Override
    public void initialize(LimitSearchPeriod constraintAnnotation) {

    }

    @Override
    public boolean isValid(ValidPeriod validPeriod, ConstraintValidatorContext context) {
        return validPeriod.isValidPeriod();
    }
}
