package com.url.service.counter;

import java.math.BigInteger;

public interface UniqueValue {

    /**
     * Each call returns a unique value.
     */
    BigInteger next();
}
