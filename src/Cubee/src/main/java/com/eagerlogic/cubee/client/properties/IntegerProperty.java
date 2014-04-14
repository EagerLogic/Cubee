/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.properties;

import com.eagerlogic.cubee.shared.utils.AValidator;

/**
 *
 * @author dipacs
 */
public final class IntegerProperty extends Property<Integer> {

    public IntegerProperty() {
    }

    public IntegerProperty(Integer defaultValue) {
        super(defaultValue);
    }

    public IntegerProperty(Integer defaultValue, boolean nullable) {
        super(defaultValue, nullable);
    }

    public IntegerProperty(Integer defaultValue, AValidator<Integer> validator) {
        super(defaultValue, validator);
    }

    public IntegerProperty(Integer defaultValue, boolean nullable, boolean readonly) {
        super(defaultValue, nullable, readonly);
    }

    public IntegerProperty(Integer defaultValue, boolean nullable, boolean readonly, AValidator<Integer> validator) {
        super(defaultValue, nullable, readonly, validator);
    }

    @Override
    public Integer animate(double pos, Integer startValue, Integer endValue) {
        if (startValue == null) {
            startValue = 0;
        }

        if (endValue == null) {
            endValue = 0;
        }

        return (int) (startValue + ((endValue - startValue) * pos));
    }
    
    public IntegerProperty add(final int value) {
        IntegerProperty res = new IntegerProperty(0, false, false);
        res.bind(new AExpression<Integer>() {
            
            {
                bind(IntegerProperty.this);
            }

            @Override
            public Integer calculate() {
                if (IntegerProperty.this.get() == null) {
                    return value;
                }
                return IntegerProperty.this.get() + value;
            }
        });
        return res;
    }
    
    public IntegerProperty add(final IntegerProperty property) {
        if (property == null) {
            throw new NullPointerException("The property parameter can not be null.");
        }
        IntegerProperty res = new IntegerProperty(0, false, false);
        res.bind(new AExpression<Integer>() {
            
            {
                bind(IntegerProperty.this, property);
            }

            @Override
            public Integer calculate() {
                if (IntegerProperty.this.get() == null && property.get() == null) {
                    return 0;
                }
                if (IntegerProperty.this.get() == null) {
                    return property.get();
                }
                if (property.get() == null) {
                    return IntegerProperty.this.get();
                }
                return IntegerProperty.this.get() + property.get();
            }
        });
        return res;
    }
    
    public IntegerProperty subtract(final int value) {
        IntegerProperty res = new IntegerProperty(0, false, false);
        res.bind(new AExpression<Integer>() {
            
            {
                bind(IntegerProperty.this);
            }

            @Override
            public Integer calculate() {
                if (IntegerProperty.this.get() == null) {
                    return 0 - value;
                }
                return IntegerProperty.this.get() - value;
            }
        });
        return res;
    }
    
    public IntegerProperty subtract(final IntegerProperty property) {
        if (property == null) {
            throw new NullPointerException("The property parameter can not be null.");
        }
        IntegerProperty res = new IntegerProperty(0, false, false);
        res.bind(new AExpression<Integer>() {
            
            {
                bind(IntegerProperty.this, property);
            }

            @Override
            public Integer calculate() {
                if (IntegerProperty.this.get() == null && property.get() == null) {
                    return 0;
                }
                if (IntegerProperty.this.get() == null) {
                    return 0 - property.get();
                }
                if (property.get() == null) {
                    return 0 - IntegerProperty.this.get();
                }
                return IntegerProperty.this.get() - property.get();
            }
        });
        return res;
    }
    
    public IntegerProperty divide(final int value) {
        if (value == 0) {
            throw new IllegalArgumentException("Division by zero.");
        }
        IntegerProperty res = new IntegerProperty(0, false, false);
        res.bind(new AExpression<Integer>() {
            
            {
                bind(IntegerProperty.this);
            }

            @Override
            public Integer calculate() {
                if (IntegerProperty.this.get() == null) {
                    return 0;
                }
                return IntegerProperty.this.get() / value;
            }
        });
        return res;
    }
    
    public IntegerProperty divide(final IntegerProperty property) {
        if (property == null) {
            throw new NullPointerException("The property parameter can not be null.");
        }
        IntegerProperty res = new IntegerProperty(0, false, false);
        res.bind(new AExpression<Integer>() {
            
            {
                bind(IntegerProperty.this, property);
            }

            @Override
            public Integer calculate() {
                if (IntegerProperty.this.get() == null && property.get() == null) {
                    return 1;
                }
                if (IntegerProperty.this.get() == null) {
                    return 0;
                }
                if (property.get() == null) {
                    return 0;
                }
                return IntegerProperty.this.get() / property.get();
            }
        });
        return res;
    }
    
    public IntegerProperty multiply(final int value) {
        IntegerProperty res = new IntegerProperty(0, false, false);
        res.bind(new AExpression<Integer>() {
            
            {
                bind(IntegerProperty.this);
            }

            @Override
            public Integer calculate() {
                if (IntegerProperty.this.get() == null) {
                    return 0;
                }
                return IntegerProperty.this.get() * value;
            }
        });
        return res;
    }
    
    public IntegerProperty multiply(final IntegerProperty property) {
        if (property == null) {
            throw new NullPointerException("The property parameter can not be null.");
        }
        IntegerProperty res = new IntegerProperty(0, false, false);
        res.bind(new AExpression<Integer>() {
            
            {
                bind(IntegerProperty.this, property);
            }

            @Override
            public Integer calculate() {
                if (IntegerProperty.this.get() == null && property.get() == null) {
                    return 0;
                }
                if (IntegerProperty.this.get() == null) {
                    return 0;
                }
                if (property.get() == null) {
                    return 0;
                }
                return IntegerProperty.this.get() * property.get();
            }
        });
        return res;
    }
    
    public IntegerProperty mod(final int value) {
        IntegerProperty res = new IntegerProperty(0, false, false);
        res.bind(new AExpression<Integer>() {
            
            {
                bind(IntegerProperty.this);
            }

            @Override
            public Integer calculate() {
                if (IntegerProperty.this.get() == null) {
                    return value;
                }
                return IntegerProperty.this.get() % value;
            }
        });
        return res;
    }
    
    public IntegerProperty mod(final IntegerProperty property) {
        if (property == null) {
            throw new NullPointerException("The property parameter can not be null.");
        }
        IntegerProperty res = new IntegerProperty(0, false, false);
        res.bind(new AExpression<Integer>() {
            
            {
                bind(IntegerProperty.this, property);
            }

            @Override
            public Integer calculate() {
                if (IntegerProperty.this.get() == null && property.get() == null) {
                    return 0;
                }
                if (IntegerProperty.this.get() == null) {
                    return property.get();
                }
                if (property.get() == null) {
                    return 0;
                }
                return IntegerProperty.this.get() % property.get();
            }
        });
        return res;
    }
    
    public DoubleProperty convertToDouble() {
        DoubleProperty res = new DoubleProperty(0.0, false, false);
        res.bind(new AExpression<Double>() {
            
            {
                bind(IntegerProperty.this);
            }

            @Override
            public Double calculate() {
                if (IntegerProperty.this.get() == null) {
                    return 0.0;
                }
                return (double)IntegerProperty.this.get();
            }
        });
        return res;
    }

}
