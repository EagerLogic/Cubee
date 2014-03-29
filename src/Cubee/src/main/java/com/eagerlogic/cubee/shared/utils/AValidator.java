package com.eagerlogic.cubee.shared.utils;

/**
 *
 * @author dipacs
 */
public abstract class AValidator<T> {
    
    /**
     * Validates the given value and returns the corrected value, if correction is needed, or returns the same value 
     * if no correction is needed. Throws an exception if the given input data is invalid.
     * 
     * @param value The value to validate.
     * @return The input value if the given input is valid, or a corrected value if the given input data can be 
     * automagically corrected.
     * @throws ValidationException If the given input data is invalid. The message of the exception explains the 
     * error which occured while validating the input.
     */
    public abstract T validate(T value) throws ValidationException;
    
}
