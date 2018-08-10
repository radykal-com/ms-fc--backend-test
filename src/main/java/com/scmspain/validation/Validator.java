package com.scmspain.validation;

import javax.validation.ValidationException;

public interface Validator<T> {

  void validate(T object) throws ValidationException;

}
