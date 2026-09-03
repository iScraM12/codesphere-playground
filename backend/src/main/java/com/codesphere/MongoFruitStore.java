package com.codesphere;

import java.util.List;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;

import org.bson.types.ObjectId;

import io.quarkus.arc.properties.IfBuildProperty;

@ApplicationScoped
@IfBuildProperty(name = "app.storage", stringValue = "mongodb")
public class MongoFruitStore implements FruitStore {

    @Override
    public List<Fruit> list() {
        return Fruit.listAll();
    }

    @Override
    public Optional<Fruit> find(String id) {
        if (!ObjectId.isValid(id)) {
            return Optional.empty();
        }
        return Optional.ofNullable(Fruit.findById(new ObjectId(id)));
    }

    @Override
    public Fruit create(Fruit fruit) {
        fruit.persist();
        return fruit;
    }

    @Override
    public boolean delete(String id) {
        return ObjectId.isValid(id) && Fruit.deleteById(new ObjectId(id));
    }
}
