package com.example.kakao.product;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.kakao._core.utils.ApiUtils;
import com.example.kakao.product.ProductResponse.FindByIdDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ProductRestController {

    private final ProductService productService;

    // (기능1) 상품 목록보기
    @GetMapping("/products")
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page) {
        List<ProductResponse.FindAllDTO> responsDTOs = productService.findAll(page);
        return ResponseEntity.ok(ApiUtils.success(responsDTOs));
    }

    // (기능2) 상품 상세보기
    @GetMapping("/products/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        FindByIdDTO product = productService.findById(id);
        return ResponseEntity.ok(ApiUtils.success(product));
    }
}