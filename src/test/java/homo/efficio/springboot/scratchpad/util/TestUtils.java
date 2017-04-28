package homo.efficio.springboot.scratchpad.util;

import org.junit.Test;

import java.util.*;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-04-14
 */
public class TestUtils {

    @Test
    public void getStringDefinitionCodesFromUrlString() throws Exception {

        String urlString = "callback=jQuery112403134002401275859_1492154992123&url=http://deal.11st.co.kr/product/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1117766194&xfrom=&xzone=&ref=http://buy.11st.co.kr/order/OrderList.tmall&b=eyJ1c2VyIjp7ImJpcnRoeWVhciI6IjE5NTgiLCJnZW5kZXIiOiJGIiwibWlkIjoiNTQ2NjMzNzg3Njg5MjE5MTIxNyJ9fQ==&items[0][id]=1117766194&items[0][c1]= 자동차용품 &items[0][c2]= 세차/관리용품 &items[0][c3]= 카샴푸&items[0][c4]=0&items[0][count]=3&items[0][options][0][stck_no]=6135264233&items[0][options][0][opt_no]=1:2&items[0][options][0][name]=A11.소낙스 멀티바스켓 - 8L&items[0][options][0][qty]=1&items[0][options][1][stck_no]=6031636659&items[0][options][1][opt_no]=1:13&items[0][options][1][name]=C03.소낙스 고속코팅왁스 - 500ml&items[0][options][1][qty]=1&items[0][options][2][stck_no]=6031636679&items[0][options][2][opt_no]=1:32&items[0][options][2][name]=F01.소낙스 컴파운드 - 75ml&items[0][options][2][qty]=1&_=1492154992124";

        final String urlParamsString = "&" + urlString.split("\\?")[1];
        final String splittedByAmpersand = urlParamsString.replaceAll("\\&", "\nfinal String ");
        final List<String> urlParamsByLines = Arrays.asList(splittedByAmpersand.substring(1).split("\n"));
        final List<String[]> defParamsByLines = new ArrayList<>();

        for (String line : urlParamsByLines) {
            final String[] paramTuple = new String[] {"", ""};
            paramTuple[0] = line.substring(0, line.indexOf("="));
            paramTuple[1] = line.substring(line.indexOf("=") + 1);

            defParamsByLines.add(paramTuple);
        }
        for (String[] tuple : defParamsByLines) {
            tuple[0] = tuple[0].replaceAll("]\\[", "_")
                               .replaceAll("\\[", "_")
                               .replaceAll("]", "");
        }

        final List<String> finalDefParamLines = new ArrayList<>();
        for (String[] tuple : defParamsByLines) {
            finalDefParamLines.add(
                    tuple[0] + " = \"" + tuple[1] + "\";"
            );
        }

        System.out.println(finalDefParamLines);




    }

    @Test
    public void xx() {
        System.out.println("Handling servlet request parameter names including square brackets".length());
    }
}
