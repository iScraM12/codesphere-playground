package com.iscram.codesphere.application.port.out;

import java.util.List;
import java.util.Optional;

import com.iscram.codesphere.domain.entity.Fruit;

public interface FruitRepositoryPort {

    List<Fruit> list();

    Optional<Fruit> find(String id);

    Fruit create(Fruit fruit);

    boolean delete(String id);
}
