package com.jeferro.products.parametrics.infrastructure.parametrics_rest;

import com.jeferro.products.parametrics.domain.models.Parametric;
import com.jeferro.products.parametrics.domain.models.ParametricId;
import com.jeferro.products.parametrics.domain.services.ParametricFinder;
import com.jeferro.products.parametrics.infrastructure.parametrics_rest.mappers.ParametricRestMapper;
import com.jeferro.products.shared.infrastructure.config.parametrics.ParametricMockRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ParametricRestFinder implements ParametricFinder {

    private final ParametricRestMapper parametricRestMapper = ParametricRestMapper.INSTANCE;

    private final ParametricMockRestClient parametricMockRestClient;

    @Override
    public Optional<Parametric> findById(ParametricId parametricId) {
        var parametricIdDTO = parametricRestMapper.toDTO(parametricId);

        var parametricDTO = parametricMockRestClient.findById(parametricIdDTO);

        return Optional.ofNullable(parametricDTO)
                .map(parametricRestMapper::toDomain);
    }

}
