package springprojdect.stunodemo.controller;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springprojdect.stunodemo.StunodemoApplication;
import springprojdect.stunodemo.model.pojos.Product;
import springprojdect.stunodemo.model.pojos.User;
import springprojdect.stunodemo.model.repositories.ProductRepository;
import springprojdect.stunodemo.util.exceptions.BaseException;
import springprojdect.stunodemo.util.exceptions.ProductNotFoundException;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


@RestController
public class ProductController extends BaseController {


    @Autowired
    private ProductRepository productRepository;


    @GetMapping(value = "/products")
    public List<Product> getAll(HttpSession session) throws BaseException {
        validateLoginAdmin(session);
        logger.error("hi");
        return productRepository.findAll();
    }

    @GetMapping(value = "/products/{id}")
    public Product getById(@PathVariable("id") long id, HttpSession session) throws BaseException {
        validateLogin(session);
        Optional<Product> obj = productRepository.findById(id);

        if (obj.isPresent()) {
            return obj.get();
        } else {
            throw new ProductNotFoundException("FU");
        }
    }

    @PostMapping(value = "/products")
    public Product save(@RequestBody Product product) {
        productRepository.save(product);
        return product;
    }

    @GetMapping(value = "products/filter")
    public List<Product> save(@RequestParam("name") String name) {
        return productRepository.findAllByName(name);


    }

}
