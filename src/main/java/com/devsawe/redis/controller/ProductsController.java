package com.devsawe.redis.controller;

import com.devsawe.redis.entity.Product;
import com.devsawe.redis.repository.ProductDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductsController {

    @Autowired
    private ProductDao dao;

    @PostMapping
    public Product save(@RequestBody Product product){
        return dao.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts (){
        return dao.findAll();
    }

    @GetMapping("/{id}")
    public Product findProduct(@PathVariable int id){
        return dao.findProductById(id);
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable int id){
        return dao.deleteProduct(id);
    }
}
