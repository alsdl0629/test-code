package sample.cafekiosk.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  readOnly = true: 읽기 전용
 *  CRUD 에서 CUD 동작 X / only Read
 *  JPA : CUD 스냅샷 저장, 변경감지 X (성능 향상)
 *
 *  CQRS - Command / Query
 *  DB URL 분리
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse createProduct(ProductCreateRequest request) {
        String nextProductNumber = createNextProductNumber();

        Product product = request.toEntity(nextProductNumber);
        Product savedProduct = productRepository.save(product);

        return ProductResponse.of(savedProduct);
    }

    // DB에서 마지막 저장된 Product의 상품번호를 읽어와서 + 1
    public List<ProductResponse> getSellingProducts() {

        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

        return products.stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    private String createNextProductNumber() {
        String latestProductNumber = productRepository.findLatestProductNumber();
        if (latestProductNumber == null) {
            return "001";
        }

        int latestProdcutNumberInt = Integer.parseInt(latestProductNumber);
        int nextProductNumberInt = latestProdcutNumberInt + 1;

        return String.format("%03d", nextProductNumberInt);
    }
}
