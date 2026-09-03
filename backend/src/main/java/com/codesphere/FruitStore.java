package com.codesphere;

import java.util.List;
import java.util.Optional;

public interface FruitStore {

    List<Fruit> list();

    Optional<Fruit> find(String id);

    Fruit create(Fruit fruit);

    boolean delete(String id);
}
