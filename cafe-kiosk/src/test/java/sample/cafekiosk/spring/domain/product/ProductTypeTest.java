package sample.cafekiosk.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.*;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafekiosk.spring.domain.product.ProductType.BAKERY;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

class ProductTypeTest {

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @Test
    void containsStockType1() {
        // given
//        Product product1 = createProduct(BAKERY, "001");
//        Product product2 = createProduct(HANDMADE, "002");
//        List<Product> products = List.of(product1, product2);
        ProductType givenType = HANDMADE;

        // when
//        List<Product> productList = products.stream()
//                .filter(product -> ProductType.containsStockType(product.getType()))
//                .toList();
        boolean result = ProductType.containsStockType(givenType);

        // then
//        assertThat(productList).hasSize(1)
//                .extracting("productNumber", "type")
//                .containsExactlyInAnyOrder(
//                        tuple("001", BAKERY)
//                );
        assertThat(result).isFalse();
    }

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @Test
    void containsStockType2() {
        // given
//        Product product1 = createProduct(BAKERY, "001");
//        Product product2 = createProduct(HANDMADE, "002");
//        List<Product> products = List.of(product1, product2);
        ProductType givenType = BAKERY;

        // when
//        List<Product> productList = products.stream()
//                .filter(product -> ProductType.containsStockType(product.getType()))
//                .toList();
        boolean result = ProductType.containsStockType(givenType);

        // then
//        assertThat(productList).hasSize(1)
//                .extracting("productNumber", "type")
//                .containsExactlyInAnyOrder(
//                        tuple("001", BAKERY)
//                );
        assertThat(result).isTrue();
    }

}