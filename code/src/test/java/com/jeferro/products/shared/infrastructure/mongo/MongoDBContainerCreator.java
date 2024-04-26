package com.jeferro.products.shared.infrastructure.mongo;

import org.testcontainers.containers.MongoDBContainer;

public class MongoDBContainerCreator {

    public static MongoDBContainer create() {
        return new MongoDBContainer("mongo:7.0");
    }
}
