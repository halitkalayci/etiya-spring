package com.etiya.ecommercedemo.repository.abstracts;

import com.etiya.ecommercedemo.entities.concretes.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    // Stock sayısına göre stock alanı gelen int değerden fazla olan productlar.

    // findAll-Products-By-Stock(int stock)
    // findAll-Products-By-Stock-GreaterThan(int stock)
    List<Product> findAllProductsByStockGreaterThanOrderByStockDesc(int stock);


    // default olarak native SQL DEĞİL!
    // JPQL
    // :parametreIsmi

    // find-get
    // By keywordü => sağına yazılan alana göre filtreleme
    // BY-PROPERTY-{CONDITION}
    // CONDITION yok ise => Otomatik olarak tam eşitlik arar
    List<Product> findByStockGreaterThan(int stock);

    List<Product> findByUnitPriceGreaterThan(double unitPrice);

    // Order By => Sağına gelen property'e göre sıralar. asc-desc
    // findAllBy
    // orderBy
    // property
    List<Product> findAllByOrderByStock();

    @Query("Select p from Product as p WHERE name=:name")
    Product findByName(String name);

    // nativeQuery = true ise JPQL değil SQL olarak yorumlanır.
    //@Query(value = "Select * from products where unit_price < stock and product_rating > 3",nativeQuery = true)
    @Query("Select p from Product as p where unitPrice < stock and product_rating > 3")
    List<Product> findByExample();

    @Query("Select p from Product p where category.id=:category_id")
    //@Query(value = "Select * from products where category_id=:category_id",nativeQuery = true)
    List<Product> findAllByCategory_Id(int category_id);


    //TODO: Select dto from Query as JPQL
    @Query("Select p from Product as p")
    Slice<Product> getAllWithSlice(Pageable pageable);
}
