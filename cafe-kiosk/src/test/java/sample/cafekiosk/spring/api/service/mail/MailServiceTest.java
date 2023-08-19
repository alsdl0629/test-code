package sample.cafekiosk.spring.api.service.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekiosk.spring.client.mail.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * mockito 사용해서 mock 객체를 만들 때
 * @Mock 동작
 */
@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    //@Mock
    /**
     * @Spy는 when절 사용불가
     * @Spy는 실제 객체를 기반으로 만들어짐
     * 실제 객체를 mocking 하려고 하기 때문에 stubbing이 되지 않음
     */
    @Spy
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    @InjectMocks
    private MailService mailService;

    @DisplayName("메일 전송 테스트")
    @Test
    void sendMail() {
        // given
//        MailSendClient mailSendClient = mock(MailSendClient.class);
//        MailSendHistoryRepository mailSendHistoryRepository = mock(MailSendHistoryRepository.class);
//        MailService mailService = new MailService(mailSendClient, mailSendHistoryRepository);

//        when(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
//                .thenReturn(true);

        /**
         * mailSendClient의 sendEmail만 stubbing된거고 남은 진짜 객체의 기능은 그대로 동작
         * 한 객체에서 일부는 실제 객체를 사용하고 싶고 나머지 일부만 Stubbing을 하고 싶을 때 @Spy 사용
         */
        doReturn(true)
                .when(mailSendClient)
                .sendEmail(anyString(), anyString(), anyString(), anyString());

        // when
        boolean result = mailService.sendMail("", "", "", "");

        // then
        assertThat(result).isTrue();
        verify(mailSendHistoryRepository, Mockito.times(1)).save(any(MailSendHistory.class));
    }

}