package cz.upce.eshop.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column
    private String pathToImage;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
