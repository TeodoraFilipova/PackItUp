package com.example.vision_pc3.packitup.repositories.base.base;

import java.util.List;
import java.util.function.Consumer;

public interface Repository<T> {

    void getAll(Consumer<List<T>> action);

    void add(T item, Consumer<T> action);
}
