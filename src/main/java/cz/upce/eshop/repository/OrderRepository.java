package cz.upce.eshop.repository;


import cz.upce.eshop.entity.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = "orderHasProduct")
    Optional<Order> findById(Long id);
}
