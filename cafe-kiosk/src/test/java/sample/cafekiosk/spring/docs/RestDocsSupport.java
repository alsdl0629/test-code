package sample.cafekiosk.spring.docs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@ExtendWith(RestDocumentationExtension.class) // Rest Docs을 사용하기 위해
//@SpringBootTest
public abstract class RestDocsSupport {

    protected MockMvc mockMvc;
    protected ObjectMapper objectMapper;

    @BeforeEach
    void setUp(RestDocumentationContextProvider provider) {
        /**
         * MockMvc를 직접 만듬
         * webAppContextSetup은 Spring 서버를 띄워야 하는데 문서 만드는 데 서버를 띄우는 건 별로
         * 그리고 @SpringBootTest(스프링 의존성) 도 달아줘야함
         * 그래서 standaloneSetup 를 사용
         */
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .apply(documentationConfiguration(provider))
//                .build();
        this.mockMvc = MockMvcBuilders.standaloneSetup(initController()) // 내가 문서를 만들고 싶은 컨트롤러를 넣으면 됨
                .apply(documentationConfiguration(provider))
                .build();
    }

    protected abstract Object initController();
}
