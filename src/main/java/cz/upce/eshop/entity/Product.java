package cz.upce.eshop.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String name;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private Set<OrderHasProduct> productInOrders;

    public Set<OrderHasProduct> getProductInOrders() {
        return productInOrders;
    }

    public void setProductInOrders(Set<OrderHasProduct> productInOrders) {
        this.productInOrders = productInOrders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
