package homo.efficio.springboot.scratchpad.util;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-04-14
 */
public class HomoEfficioWebUtils {

    private static final String FINAL_STRING = "final String ";

    public static List<String> getParamDefinitionCodesFromUrlString(String urlString) throws Exception {

//        String urlString = "/v1/logs/basket/deal.11st.co.kr/14782654128048583755870?callback=jQuery112403134002401275859_1492154992123&url=http%3A%2F%2Fdeal.11st.co.kr%2Fproduct%2FSellerProductDetail.tmall%3Fmethod%3DgetSellerProductDetail%26prdNo%3D1117766194%26xfrom%3D%26xzone%3D&ref=http%3A%2F%2Fbuy.11st.co.kr%2Forder%2FOrderList.tmall&b=eyJ1c2VyIjp7ImJpcnRoeWVhciI6IjE5NTgiLCJnZW5kZXIiOiJGIiwibWlkIjoiNTQ2NjMzNzg3Njg5MjE5MTIxNyJ9fQ%3D%3D&items%5B0%5D%5Bid%5D=1117766194&items%5B0%5D%5Bc1%5D=+%EC%9E%90%EB%8F%99%EC%B0%A8%EC%9A%A9%ED%92%88+&items%5B0%5D%5Bc2%5D=+%EC%84%B8%EC%B0%A8%2F%EA%B4%80%EB%A6%AC%EC%9A%A9%ED%92%88+&items%5B0%5D%5Bc3%5D=+%EC%B9%B4%EC%83%B4%ED%91%B8&items%5B0%5D%5Bc4%5D=0&items%5B0%5D%5Bcount%5D=3&items%5B0%5D%5Boptions%5D%5B0%5D%5Bstck_no%5D=6135264233&items%5B0%5D%5Boptions%5D%5B0%5D%5Bopt_no%5D=1%3A2&items%5B0%5D%5Boptions%5D%5B0%5D%5Bname%5D=A11.%EC%86%8C%EB%82%99%EC%8A%A4+%EB%A9%80%ED%8B%B0%EB%B0%94%EC%8A%A4%EC%BC%93+-+8L&items%5B0%5D%5Boptions%5D%5B0%5D%5Bqty%5D=1&items%5B0%5D%5Boptions%5D%5B1%5D%5Bstck_no%5D=6031636659&items%5B0%5D%5Boptions%5D%5B1%5D%5Bopt_no%5D=1%3A13&items%5B0%5D%5Boptions%5D%5B1%5D%5Bname%5D=C03.%EC%86%8C%EB%82%99%EC%8A%A4+%EA%B3%A0%EC%86%8D%EC%BD%94%ED%8C%85%EC%99%81%EC%8A%A4+-+500ml&items%5B0%5D%5Boptions%5D%5B1%5D%5Bqty%5D=1&items%5B0%5D%5Boptions%5D%5B2%5D%5Bstck_no%5D=6031636679&items%5B0%5D%5Boptions%5D%5B2%5D%5Bopt_no%5D=1%3A32&items%5B0%5D%5Boptions%5D%5B2%5D%5Bname%5D=F01.%EC%86%8C%EB%82%99%EC%8A%A4+%EC%BB%B4%ED%8C%8C%EC%9A%B4%EB%93%9C+-+75ml&items%5B0%5D%5Boptions%5D%5B2%5D%5Bqty%5D=1&_=1492154992124";

        String urlDecodedString = URLDecoder.decode(urlString, "UTF-8");
        String urlParamsString = urlDecodedString.substring(urlDecodedString.indexOf('?') + 1);

        final List<String[]> defParamsByLines = getFirstKVStrings(urlParamsString);

        for (String[] tuple : defParamsByLines) {
            tuple[0] = convertSquareBracketToUnderscore(tuple[0]);
        }

        final List<String> finalDefParamLines = new ArrayList<>();
        for (String[] tuple : defParamsByLines) {
            finalDefParamLines.add(
                    tuple[0] + " = \"" + tuple[1] + "\";"
            );
        }

        return finalDefParamLines;
    }

    /**
     * 
     * @param s
     * @return List<String[] {"final String key", "value"}>
     */
    private static List<String[]> getFirstKVStrings(String s) {
        final String urlParamsString = "&" + s;
        final String splittedByAmpersand = urlParamsString.replaceAll("\\&", "\n" + FINAL_STRING);
        final List<String> urlParamsByLines = Arrays.asList(splittedByAmpersand.substring(1).split("\n"));
        final List<String[]> defParamsByLines = new ArrayList<>();

        for (String line : urlParamsByLines) {
            final String[] paramTuple = new String[] {"", ""};
            paramTuple[0] = line.substring(0, line.indexOf("="));
            paramTuple[1] = line.substring(line.indexOf("=") + 1);

            defParamsByLines.add(paramTuple);
        }
        return defParamsByLines;
    }

    public void getParamMethodCodes() throws Exception {

        String urlString = "callback=jQuery112403134002401275859_1492154992123&url=http://deal.11st.co.kr/product/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1117766194&xfrom=&xzone=&ref=http://buy.11st.co.kr/order/OrderList.tmall&b=eyJ1c2VyIjp7ImJpcnRoeWVhciI6IjE5NTgiLCJnZW5kZXIiOiJGIiwibWlkIjoiNTQ2NjMzNzg3Njg5MjE5MTIxNyJ9fQ==&items[0][id]=1117766194&items[0][c1]= 자동차용품 &items[0][c2]= 세차/관리용품 &items[0][c3]= 카샴푸&items[0][c4]=0&items[0][count]=3&items[0][options][0][stck_no]=6135264233&items[0][options][0][opt_no]=1:2&items[0][options][0][name]=A11.소낙스 멀티바스켓 - 8L&items[0][options][0][qty]=1&items[0][options][1][stck_no]=6031636659&items[0][options][1][opt_no]=1:13&items[0][options][1][name]=C03.소낙스 고속코팅왁스 - 500ml&items[0][options][1][qty]=1&items[0][options][2][stck_no]=6031636679&items[0][options][2][opt_no]=1:32&items[0][options][2][name]=F01.소낙스 컴파운드 - 75ml&items[0][options][2][qty]=1&_=1492154992124";

        final List<String[]> firstKVStrings = getFirstKVStrings(urlString.split("\\?")[1]);

        final List<String> paramMethodsLines = new ArrayList<>();
        for (String[] tuple : firstKVStrings) {
            paramMethodsLines.add(
                    ".param(\"" + tuple[0].substring(FINAL_STRING.length()) + "\", \"" + convertSquareBracketToUnderscore(tuple[1]) + "\")"
            );
        }

        System.out.println(paramMethodsLines);
//        return paramMethodsLines;
    }

    private static String convertSquareBracketToUnderscore(String s) {
        return s.replaceAll("]\\[", "_")
                .replaceAll("\\[", "_")
                .replaceAll("]", "");
    }
}
