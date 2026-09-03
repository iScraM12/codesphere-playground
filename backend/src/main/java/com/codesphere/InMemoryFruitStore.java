package com.codesphere;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import jakarta.enterprise.context.ApplicationScoped;

import org.bson.types.ObjectId;

import io.quarkus.arc.properties.IfBuildProperty;

@ApplicationScoped
@IfBuildProperty(name = "app.storage", stringValue = "memory", enableIfMissing = true)
public class InMemoryFruitStore implements FruitStore {

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
        fruit.id = new ObjectId();
        fruits.put(fruit.id.toString(), fruit);
        return fruit;
    }

    @Override
    public boolean delete(String id) {
        return fruits.remove(id) != null;
    }
}
