package com.uta.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GenericBuilder<T> {
    private final Supplier<T> instantiate;
    private final List<Consumer<T>> instanceModifiers = new ArrayList<>();

    public GenericBuilder(Supplier<T> instantiator) {
        this.instantiate = instantiator;
    }

    public static <T> GenericBuilder<T> builder(Supplier<T> instantiate) {
        return new GenericBuilder<>(instantiate);
    }

    public <U> GenericBuilder<T> with(BiConsumer<T, U> setter, U value) {
        Consumer<T> modifier = instance -> setter.accept(instance, value);
        this.instanceModifiers.add(modifier);
        return this;
    }

    public T build() {
        T value = instantiate.get();
        instanceModifiers.forEach(modifier -> modifier.accept(value));
        instanceModifiers.clear();
        return value;
    }
}
