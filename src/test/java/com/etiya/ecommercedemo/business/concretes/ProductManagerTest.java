package com.etiya.ecommercedemo.business.concretes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    // her testten önce çalışan kod parçacığı oluşturma
    @BeforeEach
    void setUp(){
         // Her test öncesi çalışacak kod.
    }

    @Test
    void getAll() {
        // 3A Prensipi
        // Arrange,Act,Assert

        // Arrange => Testini yapacağım senaryonun bağımlılıklarını çözüyorum..
        // ProductManager,ProductRepository
        // List<Product> expectedProducts = new ArrayList<Product>();

        // Act => İlgili kodların çağırıldığı ve dönüş değerlerinin beklendiği kısım..
        // List<Product> products = productManager.getAll();

        // Assert => Expected (beklenen olgu) ile act kısmında elde edilen çıktının karşılaştırıldığı kısım..
        // TEST CASE'i BAĞLADIĞIM CONDITION'i TANIMLADIĞIM KISIM

        // products.size() === 5
        // expectedProducts elemanları products elemanları ile birebir aynı mıdır?
    }

    @Test
    void add(){

    }
}