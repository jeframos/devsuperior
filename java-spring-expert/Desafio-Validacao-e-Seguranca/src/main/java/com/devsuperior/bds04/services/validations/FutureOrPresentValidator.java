package com.devsuperior.bds04.services.validations;

import com.devsuperior.bds04.dto.EventInsertDTO;
import com.devsuperior.bds04.resources.exceptions.FieldMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FutureOrPresentValidator implements ConstraintValidator<FutureOrPresentValid, EventInsertDTO> {

    @Autowired
    private HttpServletRequest request;

    @Override
    public void initialize(FutureOrPresentValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(EventInsertDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();
        LocalDate date = dto.getDate();

        if (date.isBefore(LocalDate.now())) {
            list.add(new FieldMessage("date", "A data do evento não pode ser passada"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }
}
