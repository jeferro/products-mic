package com.jeferro.products.shared.infrastructure.adapters.utils;

import org.approvaltests.Approvals;

import java.util.Arrays;

public abstract class ApprovalUtils {

    public static void verifyAll(Object... params) {
        var array = Arrays.stream(params)
                .map(Object::toString)
                .toArray();

        Approvals.verifyAll("data", array);
    }
}
