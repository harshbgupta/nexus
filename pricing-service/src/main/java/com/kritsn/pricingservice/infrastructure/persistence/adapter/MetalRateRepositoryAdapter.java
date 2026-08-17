package com.kritsn.pricingservice.infrastructure.persistence.adapter;

import com.kritsn.pricingservice.domain.model.MetalRate;
import com.kritsn.pricingservice.domain.port.out.MetalRateRepositoryPort;
import com.kritsn.pricingservice.infrastructure.persistence.entity.MetalRateJpaEntity;
import com.kritsn.pricingservice.infrastructure.persistence.repository.MetalRateJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class MetalRateRepositoryAdapter implements MetalRateRepositoryPort {

    private final MetalRateJpaRepository jpaRepository;

    public MetalRateRepositoryAdapter(MetalRateJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public MetalRate save(MetalRate rate) {
        return toDomain(jpaRepository.save(toEntity(rate)));
    }

    @Override
    public Optional<MetalRate> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<MetalRate> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    private MetalRateJpaEntity toEntity(MetalRate rate) {
        return new MetalRateJpaEntity(rate.getId(), rate.getMetal(), rate.getPurity(), rate.getRatePerGram(),
                rate.getEffectiveAt(), rate.getCreatedAt());
    }

    private MetalRate toDomain(MetalRateJpaEntity entity) {
        return new MetalRate(entity.getId(), entity.getMetal(), entity.getPurity(), entity.getRatePerGram(),
                entity.getEffectiveAt(), entity.getCreatedAt());
    }
}
