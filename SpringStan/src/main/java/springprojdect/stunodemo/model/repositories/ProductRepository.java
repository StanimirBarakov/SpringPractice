package springprojdect.stunodemo.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springprojdect.stunodemo.model.pojos.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByName(String name);

}
