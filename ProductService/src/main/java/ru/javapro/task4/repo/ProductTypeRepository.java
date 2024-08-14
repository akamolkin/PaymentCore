package ru.javapro.task4.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javapro.task4.entity.ProductType;

import java.util.Optional;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
    Optional<ProductType> findByName(String name);
}
