package com.ljh.pma.validators;

import com.ljh.pma.entities.Employee;
import com.ljh.pma.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {

   @Autowired
   EmployeeRepository employeeRepo;

   @Override
   public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
      Employee employee = employeeRepo.findByEmail(s);

      if (employee != null)
         return false;
      else
         return true;
   }

   @Override
   public void initialize(UniqueValue constraintAnnotation) {

   }
}
