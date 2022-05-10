package com.example.bugtracker;

import org.testcontainers.containers.PostgreSQLContainer;

public class BugtrackerPostgresqlContainer extends PostgreSQLContainer<BugtrackerPostgresqlContainer> {
    private static final String IMAGE_VERSION = "postgres:12.8";
    private static BugtrackerPostgresqlContainer container;

    private BugtrackerPostgresqlContainer() {
        super(IMAGE_VERSION);
    }

    public static BugtrackerPostgresqlContainer getInstance() {
        if (container == null) {
            container = new BugtrackerPostgresqlContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("BUGTRACKER_DB_URL", container.getJdbcUrl());
        System.setProperty("BUGTRACKER_DB_USERNAME", container.getUsername());
        System.setProperty("BUGTRACKER_DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
    }
}
