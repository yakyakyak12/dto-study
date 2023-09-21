package com.example.kakao.order;

import java.util.List;
import java.util.stream.Collectors;

import com.example.kakao.cart.Cart;
import com.example.kakao.cart.CartResponse.FindAllByUserDTO.CartDTO;
import com.example.kakao.order.item.Item;
import com.example.kakao.product.Product;
import com.example.kakao.product.option.Option;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class OrderResponse {

    // (기능4) 주문상품 정보조회 (유저별)
    // 오더는 유저 밖에 안가지고 있음.
    @ToString
    @Getter
    @Setter
    public static class FindAllByUserDTO {
        private Integer totalPrice;
        private List<CartDTO> carts;

        public FindAllByUserDTO(List<Cart> cartList) {
            this.totalPrice = cartList.stream()
                    .mapToInt(cart -> cart.getPrice()).sum();
            this.carts = cartList.stream()
                    .map(cart -> new CartDTO(cart))
                    .collect(Collectors.toList());
        }

        @Getter
        @Setter
        public class CartDTO {
            private Integer cartId;
            private String optionName;
            private Integer price;
            private Integer quantity;

            public CartDTO(Cart cart) {
                this.cartId = cart.getId();
                this.optionName = cart.getOption().getOptionName();
                this.price = cart.getPrice();
                this.quantity = cart.getQuantity();
            }

        }
    }

    // (기능5) 주문결과 확인
    @ToString
    @Getter
    @Setter
    public static class FindByIdDTO {
        private Integer totalPrice;
        private Integer orderId;
        private List<ItemDTO> orderItems;

        public FindByIdDTO(List<Item> items) {
            this.totalPrice = items.stream()
                    .mapToInt(item -> item.getPrice()).sum();
            this.orderItems = items.stream()
                    .map(item -> item.getOption().getProduct()).distinct()
                    .map(product -> new ItemDTO(product, items))
                    .collect(Collectors.toList());
        }

        @Getter
        @Setter
        public class ItemDTO {
            private Integer productId;
            private String productName;
            private List<OptionDTO> options;

            public ItemDTO(Product product, List<Item> items) {
                this.productId = product.getId();
                this.productName = product.getProductName();
                this.options = items.stream()
                        .filter(item -> item.getOption().getProduct().getId() == product.getId())
                        .map(item -> new OptionDTO(item))
                        .collect(Collectors.toList());
            }
        }

        @Getter
        @Setter
        public class OptionDTO {
            private Integer optionId;
            private String optionName;
            private Integer quantity;
            private Integer price;

            public OptionDTO(Item item) {
                this.optionId = item.getOption().getId();
                this.optionName = item.getOption().getOptionName();
                this.quantity = item.getQuantity();
                this.price = item.getPrice();
            }

        }

    }
}
