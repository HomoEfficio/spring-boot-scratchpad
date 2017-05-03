package homo.efficio.springboot.scratchpad.util;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.util.WebUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-04-14
 */
@RunWith(JUnit4.class)
public class HomoEfficioWebUtilsTest {

    private static final String FINAL_STRING = "final String ";

    @Test
    public void convertSquareBracketToDot() {
        String[] strArr1 = new String[] {"111", "222"};
        String[] strArr2 = new String[] {"333", "444"};
        String[] strArr3 = new String[] {"With", "l2"};
        String[] strArr4 = new String[] {"l4", "array"};

        Map<String, Object> params = new HashMap<>();
        params.put("simple1", "simpleValue1");
        params.put("l1dot.l2", "dot");
        params.put("l1[l2]", "squareBracket");
        params.put("l1arr[]", strArr1);
        params.put("l1[l2arr][]", strArr2);
        params.put("l1arr[0][l2]", "Withl2");
        params.put("l1arr[0][l2arr][]", strArr3);
        params.put("l1arr[0][l2][l3]", "Withl3");
        params.put("l1arr[0][l2][l3arr][0][l4]", "Withl4");
        params.put("l1arr[0][l2][l3arr][0][l4arr][]", strArr4);
        params.put("l1['l2WithSingleQuote'][\"l3WithDoubleQuote\"]", "squareBracketWithQuotes");

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameters(params);
        Map<String, Object> paramMap = HomoEfficioWebUtils.getParametersStartingWith(request, "");

        assertEquals("simpleValue1", paramMap.get("simple1"));
        assertEquals("dot", paramMap.get("l1dot.l2"));
        assertEquals("squareBracket", paramMap.get("l1.l2"));
        assertEquals(strArr1, paramMap.get("l1arr"));
        assertEquals(strArr2, paramMap.get("l1.l2arr"));
        assertEquals("Withl2", paramMap.get("l1arr[0].l2"));
        assertEquals(strArr3, paramMap.get("l1arr[0].l2arr"));
        assertEquals("Withl3", paramMap.get("l1arr[0].l2.l3"));
        assertEquals("Withl4", paramMap.get("l1arr[0].l2.l3arr[0].l4"));
        assertEquals(strArr4, paramMap.get("l1arr[0].l2.l3arr[0].l4arr"));
        assertEquals("squareBracketWithQuotes", paramMap.get("l1.l2WithSingleQuote.l3WithDoubleQuote"));
    }

    @Test
    public void object인덱스형_key를_dot형으로_변환() throws Exception {

        String k = "items[0][options][1][a][12][b][abc][c][1234][c1][33][_1][___][99][a33][b3][aa3]";

        String result =
                k.replaceAll("\\[]", "")
                 .replaceAll("\\[(\\D+)", ".$1")
                 .replaceAll("]\\[(\\D)", ".$1")
                 .replaceAll("(\\.\\w+)]", "$1");

        Assert.assertThat(result, Matchers.is("items[0].options[1].a[12].b.abc.c[1234].c1[33]._1.___[99].a33.b3.aa3"));
    }

    @Test
    public void 배열형_key를_dot형으로_변환() throws Exception {

        String k = "items[0][options][catalog][1][mini][]";

        String result =
                k.replaceAll("\\[]", "")
                 .replaceAll("\\[(\\D+)", ".$1")
                 .replaceAll("]\\[(\\D)", ".$1")
                 .replaceAll("(\\.\\w+)]", "$1");

        Assert.assertThat(result, Matchers.is("items[0].options.catalog[1].mini"));
    }

    @Test
    public void URL_String에서_ParamKey_ParamValue_정의문_생성_테스트() throws Exception {

        // TODO: 배열 값이 포함된 케이스 처리 보완 필요

        // 배열 값 없음
        String urlString = "/v1/logs/basket/deal.11st.co.kr/14782654128048583755870?callback=jQuery112403134002401275859_1492154992123&url=http%3A%2F%2Fdeal.11st.co.kr%2Fproduct%2FSellerProductDetail.tmall%3Fmethod%3DgetSellerProductDetail%26prdNo%3D1117766194%26xfrom%3D%26xzone%3D&ref=http%3A%2F%2Fbuy.11st.co.kr%2Forder%2FOrderList.tmall&b=eyJ1c2VyIjp7ImJpcnRoeWVhciI6IjE5NTgiLCJnZW5kZXIiOiJGIiwibWlkIjoiNTQ2NjMzNzg3Njg5MjE5MTIxNyJ9fQ%3D%3D&items%5B0%5D%5Bid%5D=1117766194&items%5B0%5D%5Bc1%5D=+%EC%9E%90%EB%8F%99%EC%B0%A8%EC%9A%A9%ED%92%88+&items%5B0%5D%5Bc2%5D=+%EC%84%B8%EC%B0%A8%2F%EA%B4%80%EB%A6%AC%EC%9A%A9%ED%92%88+&items%5B0%5D%5Bc3%5D=+%EC%B9%B4%EC%83%B4%ED%91%B8&items%5B0%5D%5Bc4%5D=0&items%5B0%5D%5Bcount%5D=3&items%5B0%5D%5Boptions%5D%5B0%5D%5Bstck_no%5D=6135264233&items%5B0%5D%5Boptions%5D%5B0%5D%5Bopt_no%5D=1%3A2&items%5B0%5D%5Boptions%5D%5B0%5D%5Bname%5D=A11.%EC%86%8C%EB%82%99%EC%8A%A4+%EB%A9%80%ED%8B%B0%EB%B0%94%EC%8A%A4%EC%BC%93+-+8L&items%5B0%5D%5Boptions%5D%5B0%5D%5Bqty%5D=1&items%5B0%5D%5Boptions%5D%5B1%5D%5Bstck_no%5D=6031636659&items%5B0%5D%5Boptions%5D%5B1%5D%5Bopt_no%5D=1%3A13&items%5B0%5D%5Boptions%5D%5B1%5D%5Bname%5D=C03.%EC%86%8C%EB%82%99%EC%8A%A4+%EA%B3%A0%EC%86%8D%EC%BD%94%ED%8C%85%EC%99%81%EC%8A%A4+-+500ml&items%5B0%5D%5Boptions%5D%5B1%5D%5Bqty%5D=1&items%5B0%5D%5Boptions%5D%5B2%5D%5Bstck_no%5D=6031636679&items%5B0%5D%5Boptions%5D%5B2%5D%5Bopt_no%5D=1%3A32&items%5B0%5D%5Boptions%5D%5B2%5D%5Bname%5D=F01.%EC%86%8C%EB%82%99%EC%8A%A4+%EC%BB%B4%ED%8C%8C%EC%9A%B4%EB%93%9C+-+75ml&items%5B0%5D%5Boptions%5D%5B2%5D%5Bqty%5D=1&_=1492154992124";

        // 배열 값 있음
//        String urlString = "/v1/logs/basket/deal.11st.co.kr/14782654128048583755870?callback=jQuery112403134002401275859_1492154992123&url=http%3A%2F%2Fdeal.11st.co.kr%2Fproduct%2FSellerProductDetail.tmall%3Fmethod%3DgetSellerProductDetail%26prdNo%3D1117766194%26xfrom%3D%26xzone%3D&ref=http%3A%2F%2Fbuy.11st.co.kr%2Forder%2FOrderList.tmall&b=eyJ1c2VyIjp7ImJpcnRoeWVhciI6IjE5NTgiLCJnZW5kZXIiOiJGIiwibWlkIjoiNTQ2NjMzNzg3Njg5MjE5MTIxNyJ9fQ%3D%3D&items%5B0%5D%5Bid%5D=1117766194&items%5B0%5D%5Bc1%5D=+%EC%9E%90%EB%8F%99%EC%B0%A8%EC%9A%A9%ED%92%88+&items%5B0%5D%5Bc2%5D=+%EC%84%B8%EC%B0%A8%2F%EA%B4%80%EB%A6%AC%EC%9A%A9%ED%92%88+&items%5B0%5D%5Bc3%5D=+%EC%B9%B4%EC%83%B4%ED%91%B8&items%5B0%5D%5Bc4%5D=0&items%5B0%5D%5Bcount%5D=3&items%5B0%5D%5Bcatalog%5D=%5B0%2C%20%EC%B9%B4%ED%83%88%EB%A1%9C%EA%B7%B81%2C%20%EC%B9%B4%ED%83%88%EB%A1%9C%EA%B7%B82%2C%203%5D&items%5B0%5D%5Boptions%5D%5B0%5D%5Bstck_no%5D=6135264233&items%5B0%5D%5Boptions%5D%5B0%5D%5Bopt_no%5D=1%3A2&items%5B0%5D%5Boptions%5D%5B0%5D%5Bname%5D=A11.%EC%86%8C%EB%82%99%EC%8A%A4+%EB%A9%80%ED%8B%B0%EB%B0%94%EC%8A%A4%EC%BC%93+-+8L&items%5B0%5D%5Boptions%5D%5B0%5D%5Bqty%5D=1&items%5B0%5D%5Boptions%5D%5B1%5D%5Bstck_no%5D=6031636659&items%5B0%5D%5Boptions%5D%5B1%5D%5Bopt_no%5D=1%3A13&items%5B0%5D%5Boptions%5D%5B1%5D%5Bname%5D=C03.%EC%86%8C%EB%82%99%EC%8A%A4+%EA%B3%A0%EC%86%8D%EC%BD%94%ED%8C%85%EC%99%81%EC%8A%A4+-+500ml&items%5B0%5D%5Boptions%5D%5B1%5D%5Bqty%5D=1&items%5B0%5D%5Boptions%5D%5B2%5D%5Bstck_no%5D=6031636679&items%5B0%5D%5Boptions%5D%5B2%5D%5Bopt_no%5D=1%3A32&items%5B0%5D%5Boptions%5D%5B2%5D%5Bname%5D=F01.%EC%86%8C%EB%82%99%EC%8A%A4+%EC%BB%B4%ED%8C%8C%EC%9A%B4%EB%93%9C+-+75ml&items%5B0%5D%5Boptions%5D%5B2%5D%5Bqty%5D=1&_=1492154992124";

        List<String> actualResults = HomoEfficioWebUtils.getParamDefinitionCodesFromUrlString(urlString);

        List<String> expectedResults = new ArrayList<>();
        expectedResults.add("final String callback = \"jQuery112403134002401275859_1492154992123\";");
        expectedResults.add("final String url = \"http://deal.11st.co.kr/product/SellerProductDetail.tmall?method=getSellerProductDetail\";");
        expectedResults.add("final String prdNo = \"1117766194\";");
        expectedResults.add("final String xfrom = \"\";");
        expectedResults.add("final String xzone = \"\";");
        expectedResults.add("final String ref = \"http://buy.11st.co.kr/order/OrderList.tmall\";");
        expectedResults.add("final String b = \"eyJ1c2VyIjp7ImJpcnRoeWVhciI6IjE5NTgiLCJnZW5kZXIiOiJGIiwibWlkIjoiNTQ2NjMzNzg3Njg5MjE5MTIxNyJ9fQ==\";");
        expectedResults.add("final String items_0_id = \"1117766194\";");
        expectedResults.add("final String items_0_c1 = \" 자동차용품 \";");
        expectedResults.add("final String items_0_c2 = \" 세차/관리용품 \";");
        expectedResults.add("final String items_0_c3 = \" 카샴푸\";");
        expectedResults.add("final String items_0_c4 = \"0\";");
        expectedResults.add("final String items_0_count = \"3\";");
//        expectedResults.add("final String items_0_catalog = [0, \"카탈로그1\", \"카탈로그2\", 3];");
        expectedResults.add("final String items_0_options_0_stck_no = \"6135264233\";");
        expectedResults.add("final String items_0_options_0_opt_no = \"1:2\";");
        expectedResults.add("final String items_0_options_0_name = \"A11.소낙스 멀티바스켓 - 8L\";");
        expectedResults.add("final String items_0_options_0_qty = \"1\";");
        expectedResults.add("final String items_0_options_1_stck_no = \"6031636659\";");
        expectedResults.add("final String items_0_options_1_opt_no = \"1:13\";");
        expectedResults.add("final String items_0_options_1_name = \"C03.소낙스 고속코팅왁스 - 500ml\";");
        expectedResults.add("final String items_0_options_1_qty = \"1\";");
        expectedResults.add("final String items_0_options_2_stck_no = \"6031636679\";");
        expectedResults.add("final String items_0_options_2_opt_no = \"1:32\";");
        expectedResults.add("final String items_0_options_2_name = \"F01.소낙스 컴파운드 - 75ml\";");
        expectedResults.add("final String items_0_options_2_qty = \"1\";");
        expectedResults.add("final String _ = \"1492154992124\";");

        assertThat("url parameter의 개수가 다름", actualResults.size(), is(expectedResults.size()));

        for (int i = 0, len = actualResults.size(); i < len ; i++) {
            assertThat(actualResults.get(i), is(expectedResults.get(i)));
        }
    }

    @Test
    public void URL_String에서_SpringMVC_param메서드구문_생성_테스트() throws Exception {

        // 배열 값 없음
        String urlString = "/v1/logs/basket/deal.11st.co.kr/14782654128048583755870?callback=jQuery112403134002401275859_1492154992123&url=http%3A%2F%2Fdeal.11st.co.kr%2Fproduct%2FSellerProductDetail.tmall%3Fmethod%3DgetSellerProductDetail%26prdNo%3D1117766194%26xfrom%3D%26xzone%3D&ref=http%3A%2F%2Fbuy.11st.co.kr%2Forder%2FOrderList.tmall&b=eyJ1c2VyIjp7ImJpcnRoeWVhciI6IjE5NTgiLCJnZW5kZXIiOiJGIiwibWlkIjoiNTQ2NjMzNzg3Njg5MjE5MTIxNyJ9fQ%3D%3D&items%5B0%5D%5Bid%5D=1117766194&items%5B0%5D%5Bc1%5D=+%EC%9E%90%EB%8F%99%EC%B0%A8%EC%9A%A9%ED%92%88+&items%5B0%5D%5Bc2%5D=+%EC%84%B8%EC%B0%A8%2F%EA%B4%80%EB%A6%AC%EC%9A%A9%ED%92%88+&items%5B0%5D%5Bc3%5D=+%EC%B9%B4%EC%83%B4%ED%91%B8&items%5B0%5D%5Bc4%5D=0&items%5B0%5D%5Bcount%5D=3&items%5B0%5D%5Boptions%5D%5B0%5D%5Bstck_no%5D=6135264233&items%5B0%5D%5Boptions%5D%5B0%5D%5Bopt_no%5D=1%3A2&items%5B0%5D%5Boptions%5D%5B0%5D%5Bname%5D=A11.%EC%86%8C%EB%82%99%EC%8A%A4+%EB%A9%80%ED%8B%B0%EB%B0%94%EC%8A%A4%EC%BC%93+-+8L&items%5B0%5D%5Boptions%5D%5B0%5D%5Bqty%5D=1&items%5B0%5D%5Boptions%5D%5B1%5D%5Bstck_no%5D=6031636659&items%5B0%5D%5Boptions%5D%5B1%5D%5Bopt_no%5D=1%3A13&items%5B0%5D%5Boptions%5D%5B1%5D%5Bname%5D=C03.%EC%86%8C%EB%82%99%EC%8A%A4+%EA%B3%A0%EC%86%8D%EC%BD%94%ED%8C%85%EC%99%81%EC%8A%A4+-+500ml&items%5B0%5D%5Boptions%5D%5B1%5D%5Bqty%5D=1&items%5B0%5D%5Boptions%5D%5B2%5D%5Bstck_no%5D=6031636679&items%5B0%5D%5Boptions%5D%5B2%5D%5Bopt_no%5D=1%3A32&items%5B0%5D%5Boptions%5D%5B2%5D%5Bname%5D=F01.%EC%86%8C%EB%82%99%EC%8A%A4+%EC%BB%B4%ED%8C%8C%EC%9A%B4%EB%93%9C+-+75ml&items%5B0%5D%5Boptions%5D%5B2%5D%5Bqty%5D=1&_=1492154992124";

        // 배열 값 있음
//        String urlString = "/v1/logs/basket/deal.11st.co.kr/14782654128048583755870?callback=jQuery112403134002401275859_1492154992123&url=http%3A%2F%2Fdeal.11st.co.kr%2Fproduct%2FSellerProductDetail.tmall%3Fmethod%3DgetSellerProductDetail%26prdNo%3D1117766194%26xfrom%3D%26xzone%3D&ref=http%3A%2F%2Fbuy.11st.co.kr%2Forder%2FOrderList.tmall&b=eyJ1c2VyIjp7ImJpcnRoeWVhciI6IjE5NTgiLCJnZW5kZXIiOiJGIiwibWlkIjoiNTQ2NjMzNzg3Njg5MjE5MTIxNyJ9fQ%3D%3D&items%5B0%5D%5Bid%5D=1117766194&items%5B0%5D%5Bc1%5D=+%EC%9E%90%EB%8F%99%EC%B0%A8%EC%9A%A9%ED%92%88+&items%5B0%5D%5Bc2%5D=+%EC%84%B8%EC%B0%A8%2F%EA%B4%80%EB%A6%AC%EC%9A%A9%ED%92%88+&items%5B0%5D%5Bc3%5D=+%EC%B9%B4%EC%83%B4%ED%91%B8&items%5B0%5D%5Bc4%5D=0&items%5B0%5D%5Bcount%5D=3&items%5B0%5D%5Bcatalog%5D=%5B0%2C%20%EC%B9%B4%ED%83%88%EB%A1%9C%EA%B7%B81%2C%20%EC%B9%B4%ED%83%88%EB%A1%9C%EA%B7%B82%2C%203%5D&items%5B0%5D%5Boptions%5D%5B0%5D%5Bstck_no%5D=6135264233&items%5B0%5D%5Boptions%5D%5B0%5D%5Bopt_no%5D=1%3A2&items%5B0%5D%5Boptions%5D%5B0%5D%5Bname%5D=A11.%EC%86%8C%EB%82%99%EC%8A%A4+%EB%A9%80%ED%8B%B0%EB%B0%94%EC%8A%A4%EC%BC%93+-+8L&items%5B0%5D%5Boptions%5D%5B0%5D%5Bqty%5D=1&items%5B0%5D%5Boptions%5D%5B1%5D%5Bstck_no%5D=6031636659&items%5B0%5D%5Boptions%5D%5B1%5D%5Bopt_no%5D=1%3A13&items%5B0%5D%5Boptions%5D%5B1%5D%5Bname%5D=C03.%EC%86%8C%EB%82%99%EC%8A%A4+%EA%B3%A0%EC%86%8D%EC%BD%94%ED%8C%85%EC%99%81%EC%8A%A4+-+500ml&items%5B0%5D%5Boptions%5D%5B1%5D%5Bqty%5D=1&items%5B0%5D%5Boptions%5D%5B2%5D%5Bstck_no%5D=6031636679&items%5B0%5D%5Boptions%5D%5B2%5D%5Bopt_no%5D=1%3A32&items%5B0%5D%5Boptions%5D%5B2%5D%5Bname%5D=F01.%EC%86%8C%EB%82%99%EC%8A%A4+%EC%BB%B4%ED%8C%8C%EC%9A%B4%EB%93%9C+-+75ml&items%5B0%5D%5Boptions%5D%5B2%5D%5Bqty%5D=1&_=1492154992124";

        List<String> actualResults = HomoEfficioWebUtils.getParamMethodCodes(urlString);

        List<String> expectedResults = new ArrayList<>();
        expectedResults.add(".param(\"callback\", callback)");
        expectedResults.add(".param(\"url\", url)");
        expectedResults.add(".param(\"prdNo\", prdNo)");
        expectedResults.add(".param(\"xfrom\", xfrom)");
        expectedResults.add(".param(\"xzone\", xzone)");
        expectedResults.add(".param(\"ref\", ref)");
        expectedResults.add(".param(\"b\", b)");
        expectedResults.add(".param(\"items[0][id]\", items_0_id)");
        expectedResults.add(".param(\"items[0][c1]\", items_0_c1)");
        expectedResults.add(".param(\"items[0][c2]\", items_0_c2)");
        expectedResults.add(".param(\"items[0][c3]\", items_0_c3)");
        expectedResults.add(".param(\"items[0][c4]\", items_0_c4)");
        expectedResults.add(".param(\"items[0][count]\", items_0_count)");
//        expectedResults.add(".param(\"items[0][catalog]\", items_0_catalog)");
        expectedResults.add(".param(\"items[0][options][0][stck_no]\", items_0_options_0_stck_no)");
        expectedResults.add(".param(\"items[0][options][0][opt_no]\", items_0_options_0_opt_no)");
        expectedResults.add(".param(\"items[0][options][0][name]\", items_0_options_0_name)");
        expectedResults.add(".param(\"items[0][options][0][qty]\", items_0_options_0_qty)");
        expectedResults.add(".param(\"items[0][options][1][stck_no]\", items_0_options_1_stck_no)");
        expectedResults.add(".param(\"items[0][options][1][opt_no]\", items_0_options_1_opt_no)");
        expectedResults.add(".param(\"items[0][options][1][name]\", items_0_options_1_name)");
        expectedResults.add(".param(\"items[0][options][1][qty]\", items_0_options_1_qty)");
        expectedResults.add(".param(\"items[0][options][2][stck_no]\", items_0_options_2_stck_no)");
        expectedResults.add(".param(\"items[0][options][2][opt_no]\", items_0_options_2_opt_no)");
        expectedResults.add(".param(\"items[0][options][2][name]\", items_0_options_2_name)");
        expectedResults.add(".param(\"items[0][options][2][qty]\", items_0_options_2_qty)");
        expectedResults.add(".param(\"_\", _)");

        assertThat("url parameter의 개수가 다름", actualResults.size(), is(expectedResults.size()));

        for (int i = 0, len = actualResults.size(); i < len ; i++) {
            assertThat(actualResults.get(i), is(expectedResults.get(i)));
        }
    }
}
