package homo.efficio.springboot.scratchpad.jackson.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import homo.efficio.springboot.scratchpad.jackson.model.Address;
import homo.efficio.springboot.scratchpad.jackson.model.UserInfo;
import homo.efficio.springboot.scratchpad.jackson.model.UserInfoWithAddress;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-04-11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .alwaysDo(print())
                .build();
    }

    @Test
    public void 회원등록_단순문자열리스트() throws Exception {

        String id = "333";
        String email1 = "hanmomhanda@gmail.com";
        String email2 = "homo.efficio@gmail.com";
        String email3 = "hanmomhanda@naver.com";

        UserInfo userInfo = new UserInfo(id, Arrays.asList(email1, email2, email3));

        String userInfoJson = objectMapper.writeValueAsString(userInfo);

        MvcResult result = mockMvc
                .perform(
                        post("/jackson/simple-value-list")
                                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                                .content(userInfoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.emails[0]", is(email1)))
                .andExpect(jsonPath("$.emails[1]", is(email2)))
                .andExpect(jsonPath("$.emails[2]", is(email3)))
                .andReturn();
    }

    @Test
    public void 회원등록_객체리스트() throws Exception {

        String id = "333";
        String email1 = "hanmomhanda@gmail.com";
        String email2 = "homo.efficio@gmail.com";
        String email3 = "hanmomhanda@naver.com";

        UserInfo userInfo = new UserInfo(id, Arrays.asList(email1, email2, email3));

        String city = "인천";
        String goo = "서구";
        String street = "청라 크리스탈로";
        String number1 = "148";
        String number2 = "296";

        Address address1 = new Address(city, goo, street, number1);
        Address address2 = new Address(city, goo, street, number2);

        UserInfoWithAddress userInfoWithAddress =
                new UserInfoWithAddress(userInfo, Arrays.asList(address1, address2));

        String userInfoWithAddressJson = objectMapper.writeValueAsString(userInfoWithAddress);

        MvcResult result = mockMvc
                .perform(
                        post("/jackson/object-list")
                                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                                .content(userInfoWithAddressJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userInfo.id", is(id)))
                .andExpect(jsonPath("$.userInfo.emails[0]", is(email1)))
                .andExpect(jsonPath("$.userInfo.emails[1]", is(email2)))
                .andExpect(jsonPath("$.userInfo.emails[2]", is(email3)))
                .andExpect(jsonPath("$.addresses[0].city", is(address1.getCity())))
                .andExpect(jsonPath("$.addresses[1].number", is(address2.getNumber())))
                .andReturn();
    }
}
