package com.iscram.codesphere.adapter.out.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import jakarta.enterprise.context.ApplicationScoped;

import com.iscram.codesphere.application.port.out.FruitRepositoryPort;
import com.iscram.codesphere.domain.entity.Fruit;

@ApplicationScoped
public class InMemoryFruitRepository implements FruitRepositoryPort {

    private final ConcurrentMap<String, Fruit> fruits = new ConcurrentHashMap<>();

    @Override
    public List<Fruit> list() {
        return new ArrayList<>(fruits.values());
    }

    @Override
    public Optional<Fruit> find(String id) {
        return Optional.ofNullable(fruits.get(id));
    }

    @Override
    public Fruit create(Fruit fruit) {
        fruit.id = UUID.randomUUID().toString();
        fruits.put(fruit.id.toString(), fruit);
        return fruit;
    }

    @Override
    public boolean delete(String id) {
        return fruits.remove(id) != null;
    }
}
