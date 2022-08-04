package com.url.service.counter;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class IncrementingInMemoryValue implements UniqueValue {

    private final AtomicReference<BigInteger> valueHolder;

    public IncrementingInMemoryValue() {
        this(BigInteger.valueOf(-1));
    }

    public IncrementingInMemoryValue(BigInteger initialValue) {
        this.valueHolder = new AtomicReference<>(initialValue);
    }

    @Override
    public BigInteger next() {
        while (true) {
            BigInteger current = valueHolder.get();
            BigInteger next = current.add(BigInteger.ONE);
            if (valueHolder.compareAndSet(current, next)) {
                return next;
            }
        }
    }
}
