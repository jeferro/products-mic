package com.jeferro.products.parametrics.domain.services;

import java.util.Optional;

import com.jeferro.products.parametrics.domain.exceptions.ParametricNotFoundException;
import com.jeferro.products.parametrics.domain.models.Parametric;
import com.jeferro.products.parametrics.domain.models.ParametricId;

public interface ParametricFinder {

  Optional<Parametric> findById(ParametricId parametricId);

  default Parametric findByIdOrError(ParametricId parametricId) {
    return findById(parametricId)
        .orElseThrow(() -> ParametricNotFoundException.createOf(parametricId));
  }
}
