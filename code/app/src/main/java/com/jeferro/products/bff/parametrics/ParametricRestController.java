package com.jeferro.products.bff.parametrics;

import java.util.List;

import com.jeferro.products.parametrics.infrastructure.parametrics_rest.dtos.ParametricRestDTO;
import com.jeferro.products.shared.infrastructure.config.parametrics.ParametricMockRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/parametrics")
@RequiredArgsConstructor
public class ParametricRestController {

  private final ParametricMockRestClient parametricMockRestClient;

  @GetMapping
  public List<ParametricRestDTO> searchAll() {
	return parametricMockRestClient.findByDomain("products");
  }
}
