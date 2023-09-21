package com.example.kakao.order;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class OrderJPARepositoryTest {

    @Autowired
    private OrderJPARepository orderJPARepository;

    @Test
    public void findByUserId_test() {

    }
}
