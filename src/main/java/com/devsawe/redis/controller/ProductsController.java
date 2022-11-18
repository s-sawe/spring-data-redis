package com.devsawe.redis.controller;

import com.devsawe.redis.entity.Product;
import com.devsawe.redis.repository.ProductDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
@EnableCaching
public class ProductsController {

    @Autowired
    private ProductDao dao;

    @PostMapping
    @CacheEvict(value = "Product", allEntries = true)
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
    @Cacheable(key = "#id", value = "Product")
    public String remove(@PathVariable int id){
        return dao.deleteProduct(id);
    }
}
