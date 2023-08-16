package sample.cafekiosk.spring.domain.stock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.HOLD;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;

@DataJpaTest
class StockRepositoryTest {

    @Autowired
    private StockRepository stockRepository;

    @DisplayName("상품번호 리스트로 재고을 조회한다.")
    @Test
    void findAllByProductNumberIn() {
        // given
        Stock stock1 = Stock.create("001", 1);
        Stock stock2 = Stock.create("002", 2);
        Stock stock3 = Stock.create("003", 3);
        stockRepository.saveAll(List.of(stock1, stock2, stock3));

        // when
        List<Stock> stocks = stockRepository.findAllByProductNumberIn(List.of("001", "002"));

        // then
        assertThat(stocks).hasSize(2)
                .extracting("productNumber", "quantity") // 검증하는 필드만 추출
                .containsExactlyInAnyOrder(
                        tuple("001", 1),
                        tuple("002", 2)
                ); // 순서 상관없이 확인함
        // 리스트 검증할 때 extracting - contains를 주로 사
    }


}