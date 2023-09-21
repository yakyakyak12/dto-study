package com.example.kakao.product;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.naming.StringManager;

import com.example.kakao.product.option.Option;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ProductResponse {

    // (기능1) 상품 목록보기
    @ToString
    @Getter
    @Setter
    public static class FindAllDTO {
        private Integer productid;
        private Integer productDelivery;
        private String productImage;
        private String productName;
        private Integer productPrice;

        public FindAllDTO(Product product) {
            this.productid = product.getId();
            this.productDelivery = 1;
            this.productImage = product.getImage();
            this.productName = product.getProductName();
            this.productPrice = product.getPrice();
        }

    }

    // (기능2) 상품 상세보기
    @ToString
    @Getter
    @Setter
    public static class FindByIdDTO {
        private Integer productid;
        private String productName;
        private String productImage;
        private Integer productPrice;
        private Integer productStartCount;
        private List<OptionDTO> options;

        public FindByIdDTO(Product product) {
            this.productid = product.getId();
            this.productName = product.getProductName();
            this.productImage = product.getImage();
            this.productPrice = product.getPrice();
            this.productStartCount = 4;
            this.options = product.getOptions().stream()
                    .map(option -> new OptionDTO(option))
                    .collect(Collectors.toList());
        }

        @Getter
        @Setter
        public class OptionDTO {
            private Integer optionid;
            private String optionName;
            private Integer optionPrice;

            public OptionDTO(Option option) {
                this.optionid = option.getId();
                this.optionName = option.getOptionName();
                this.optionPrice = option.getPrice();
            }

        }
    }
}