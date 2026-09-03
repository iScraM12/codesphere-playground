package com.codesphere;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

/**
 * Sample MongoDB entity used to demonstrate persistence via Quarkus MongoDB Panache.
 */
public class Fruit extends PanacheMongoEntity {

    public String name;

    public String description;

    public Fruit() {
    }

    public Fruit(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
