package homo.efficio.springboot.scratchpad.jackson.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import homo.efficio.springboot.scratchpad.jackson.model.*;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    public void POST_JSON_String_Array_to_DTO_with_LIST() throws Exception {

        String id = "333";
        String email1 = "hanmomhanda@gmail.com";
        String email2 = "homo.efficio@gmail.com";
        String email3 = "hanmomhanda@naver.com";

        UserInfo userInfo = new UserInfoWithSimpleList(id, Arrays.asList(email1, email2, email3));

        String userInfoJson = objectMapper.writeValueAsString(userInfo);

        MvcResult result = mockMvc
                .perform(
                        post("/jackson/simple-value-array-to-dto-with-list/post")
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
    public void POST_JSON_Object_Array_to_DTO_with_LIST() throws Exception {

        String id = "333";

        String city = "인천";
        String goo = "서구";
        String street = "청라 크리스탈로";
        String number1 = "148";
        String number2 = "296";

        Address address1 = new Address(city, goo, street, number1);
        Address address2 = new Address(city, goo, street, number2);

        UserInfoWithObjectList userInfoWithObjectList =
                new UserInfoWithObjectList(id, Arrays.asList(address1, address2));

        String userInfoWithAddressJson = objectMapper.writeValueAsString(userInfoWithObjectList);

        MvcResult result = mockMvc
                .perform(
                        post("/jackson/object-array-to-dto-with-list/post")
                                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                                .content(userInfoWithAddressJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.addresses[0].city", is(address1.getCity())))
                .andExpect(jsonPath("$.addresses[1].number", is(address2.getNumber())))
                .andReturn();
    }

    @Test
    public void POST_JSON_Object_Array_to_DTO_with_Array() throws Exception {

        String id = "333";

        String city = "인천";
        String goo = "서구";
        String street = "청라 크리스탈로";
        String number1 = "148";
        String number2 = "296";

        Address address1 = new Address(city, goo, street, number1);
        Address address2 = new Address(city, goo, street, number2);

        UserInfoWithObjectArray userInfoWithObjectArray =
                new UserInfoWithObjectArray(id, new Address[]{address1, address2});

        String userInfoWithAddressJson = objectMapper.writeValueAsString(userInfoWithObjectArray);

        MvcResult result = mockMvc
                .perform(
                        post("/jackson/object-array-to-dto-with-array/post")
                                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                                .content(userInfoWithAddressJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.addresses[0].city", is(address1.getCity())))
                .andExpect(jsonPath("$.addresses[1].number", is(address2.getNumber())))
                .andReturn();
    }





    @Test
    public void GET_JSON_String_Array_to_DTO_with_List() throws Exception {

        String id = "333";
        String email0 = "hanmomhanda@gmail.com";
        String email1 = "homo.efficio@gmail.com";
        String email2 = "hanmomhanda@naver.com";

        StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id)
          .append("&emails[0]=").append(email0)
          .append("&emails[1]=").append(email1)
          .append("&emails[2]=").append(email2);

        MvcResult result = mockMvc
                .perform(get("/jackson/simple-value-array-to-dto-with-list/get?" + sb.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.emails[0]", is(email0)))
                .andExpect(jsonPath("$.emails[1]", is(email1)))
                .andExpect(jsonPath("$.emails[2]", is(email2)))
                .andReturn();
    }



    @Test
    public void GET_JSON_Object_Array_to_DTO_with_List() throws Exception {

        String id = "333";

        String city = "인천";
        String goo = "서구";
        String street = "청라 크리스탈로";
        String number0 = "148";
        String number1 = "296";

        StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id)
                .append("&addresses[0].city=").append(city)
                .append("&addresses[0].goo=").append(goo)
                .append("&addresses[0].street=").append(street)
                .append("&addresses[0].number=").append(number0)
                .append("&addresses[1].city=").append(city)
                .append("&addresses[1].goo=").append(goo)
                .append("&addresses[1].street=").append(street)
                .append("&addresses[1].number=").append(number1);

        MvcResult result = mockMvc
                .perform(get("/jackson/object-array-to-dto-with-list/get?" + sb.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.addresses[0].city", is(city)))
                .andExpect(jsonPath("$.addresses[1].goo", is(goo)))
                .andExpect(jsonPath("$.addresses[0].number", is(number0)))
                .andExpect(jsonPath("$.addresses[1].number", is(number1)))
                .andReturn();
    }

    @Test
    public void GET_JSON_Object_Array_to_DTO_with_Array() throws Exception {

        String id = "333";

        String city = "인천";
        String goo = "서구";
        String street = "청라 크리스탈로";
        String number0 = "148";
        String number1 = "296";

        StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id)
                .append("&addresses[0].city=").append(city)
                .append("&addresses[0].goo=").append(goo)
                .append("&addresses[0].street=").append(street)
                .append("&addresses[0].number=").append(number0)
                .append("&addresses[1].city=").append(city)
                .append("&addresses[1].goo=").append(goo)
                .append("&addresses[1].street=").append(street)
                .append("&addresses[1].number=").append(number1);

        MvcResult result = mockMvc
                .perform(get("/jackson/object-array-to-dto-with-array/get?" + sb.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.addresses[0].city", is(city)))
                .andExpect(jsonPath("$.addresses[1].goo", is(goo)))
                .andExpect(jsonPath("$.addresses[0].number", is(number0)))
                .andExpect(jsonPath("$.addresses[1].number", is(number1)))
                .andReturn();
    }

    @Test
    public void GET_jQuery_JSON_Object_Array_to_DTO_with_List() throws Exception {

        String id = "333";

        String city = "인천";
        String goo = "서구";
        String street = "청라 크리스탈로";
        String number0 = "148";
        String number1 = "296";

        StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id)
                .append("&addresses[0][city]=").append(city)
                .append("&addresses[0][goo]=").append(goo)
                .append("&addresses[0][street]=").append(street)
                .append("&addresses[0][number]=").append(number0)
                .append("&addresses[1][city]=").append(city)
                .append("&addresses[1][goo]=").append(goo)
                .append("&addresses[1][street]=").append(street)
                .append("&addresses[1][number]=").append(number1);

        MvcResult result = mockMvc
                .perform(get("/jackson/object-array-to-dto-with-list/get?" + sb.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.addresses[0].city", is(city)))
                .andExpect(jsonPath("$.addresses[1].goo", is(goo)))
                .andExpect(jsonPath("$.addresses[0].number", is(number0)))
                .andExpect(jsonPath("$.addresses[1].number", is(number1)))
                .andReturn();
    }

    @Test
    public void GET_jQuery_JSON_Object_Array_to_DTO_with_Array() throws Exception {

        String id = "333";

        String city = "인천";
        String goo = "서구";
        String street = "청라 크리스탈로";
        String number0 = "148";
        String number1 = "296";

        StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id)
                .append("&addresses[0][city]=").append(city)
                .append("&addresses[0][goo]=").append(goo)
                .append("&addresses[0][street]=").append(street)
                .append("&addresses[0][number]=").append(number0)
                .append("&addresses[1][city]=").append(city)
                .append("&addresses[1][goo]=").append(goo)
                .append("&addresses[1][street]=").append(street)
                .append("&addresses[1][number]=").append(number1);

        MvcResult result = mockMvc
                .perform(get("/jackson/object-array-to-dto-with-array/get?" + sb.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.addresses[0].city", is(city)))
                .andExpect(jsonPath("$.addresses[1].goo", is(goo)))
                .andExpect(jsonPath("$.addresses[0].number", is(number0)))
                .andExpect(jsonPath("$.addresses[1].number", is(number1)))
                .andReturn();
    }



//    @Test
//    public void 회원등록_객체리스트_JSON_GET() throws Exception {
//
//        String id = "333";
//        String email1 = "hanmomhanda@gmail.com";
//        String email2 = "homo.efficio@gmail.com";
//        String email3 = "hanmomhanda@naver.com";
//
//        UserInfo userInfo = new UserInfo(id, Arrays.asList(email1, email2, email3));
//
//        String city = "인천";
//        String goo = "서구";
//        String street = "청라 크리스탈로";
//        String number1 = "148";
//        String number2 = "296";
//
//        Address address1 = new Address(city, goo, street, number1);
//        Address address2 = new Address(city, goo, street, number2);
//
//        UserInfoWithObjectList userInfoWithObjectList =
//                new UserInfoWithObjectList(userInfo, Arrays.asList(address1, address2));
//
//        String userInfoWithAddressJson = objectMapper.writeValueAsString(userInfoWithObjectList);
//
//        MvcResult result = mockMvc
//                .perform(
//                        get("/jackson/object-list")
//                                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                                .content(userInfoWithAddressJson))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.userInfo.id", is(id)))
//                .andExpect(jsonPath("$.userInfo.emails[0]", is(email1)))
//                .andExpect(jsonPath("$.userInfo.emails[1]", is(email2)))
//                .andExpect(jsonPath("$.userInfo.emails[2]", is(email3)))
//                .andExpect(jsonPath("$.addresses[0].city", is(address1.getCity())))
//                .andExpect(jsonPath("$.addresses[1].number", is(address2.getNumber())))
//                .andReturn();
//    }

//    @Test
//    public void 회원등록_객체리스트_JSON_GET_DataBinder() throws Exception {
//
//        String id = "333";
//        String email1 = "hanmomhanda@gmail.com";
//        String email2 = "homo.efficio@gmail.com";
//        String email3 = "hanmomhanda@naver.com";
//
//        UserInfo userInfo = new UserInfo(id, Arrays.asList(email1, email2, email3));
//
//        String city = "인천";
//        String goo = "서구";
//        String street = "청라 크리스탈로";
//        String number1 = "148";
//        String number2 = "296";
//
//        Address address1 = new Address(city, goo, street, number1);
//        Address address2 = new Address(city, goo, street, number2);
//
//        UserInfoWithObjectList userInfoWithObjectList =
//                new UserInfoWithObjectList(userInfo, Arrays.asList(address1, address2));
//
//        String userInfoWithAddressJson = objectMapper.writeValueAsString(userInfoWithObjectList);
//
//        MvcResult result = mockMvc
//                .perform(
//                        get("/jackson/object-list/data-binder")
//                                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                                .content(userInfoWithAddressJson))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.userInfo.id", is(id)))
//                .andExpect(jsonPath("$.userInfo.emails[0]", is(email1)))
//                .andExpect(jsonPath("$.userInfo.emails[1]", is(email2)))
//                .andExpect(jsonPath("$.userInfo.emails[2]", is(email3)))
//                .andExpect(jsonPath("$.addresses[0].city", is(address1.getCity())))
//                .andExpect(jsonPath("$.addresses[1].number", is(address2.getNumber())))
//                .andReturn();
//    }
}
