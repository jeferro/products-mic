package com.jeferro.products;

import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

public class BffSimulation extends Simulation {

    public BffSimulation() {
        var injections = OpenInjectionStep.atOnceUsers(20);
        var population = configureScenario().injectOpen(injections);

        setUp(population)
                .protocols(configureHttpProtocol());
    }

    private static ScenarioBuilder configureScenario() {
        return CoreDsl.scenario("Load Test Get Devices")
                .exec(findDevices());
    }

    private static HttpProtocolBuilder configureHttpProtocol() {
        return HttpDsl.http.baseUrl("https://api.restful-api.dev")
                .acceptHeader("application/json")
                .maxConnectionsPerHost(10);
    }

    private static HttpRequestActionBuilder findDevices() {
        return http("get-devices")
                .get("/objects")
                .check(status().is(200));
    }
}
