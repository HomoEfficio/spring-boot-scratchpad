package homo.efficio.springboot.scratchpad.util;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-04-14
 */
public class HomoEfficioWebUtils {

    // TODO: 배열 값이 포함된 케이스 처리 보완 필요

    private static final String FINAL_STRING = "final String ";

    /**
     * bindingResult에 포함되어 있는 바인딩 에러 발생 필드 이름들을 ','로 Join한 문자열 반환
     *
     * @param bindingResult
     * @return
     */
    public static String getErrorFieldNames(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(v -> v.getField())
                .collect(Collectors.joining(","));
    }

    /**
     * jQuery.ajax() 내에서 data 항목에 JSON으로 작성된 데이터가
     * GET 방식으로 넘어올 때 Spring이 바인딩 해주지 못하는 부분을 보완
     *
     * @param parameterMap  request의 파라미터맵
     * @param dto           바인딩 할 DTO의 클래스 리터럴
     * @param <T>           바인딩 할 DTO의 타입
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T getDTOFromParamMap(Map<String, String[]> parameterMap, Class<T> dto) throws IllegalAccessException, InstantiationException {

        final MutablePropertyValues sourceProps = getPropsFrom(parameterMap);

        T targetDTO = dto.newInstance();
        DataBinder binder = new DataBinder(targetDTO);
        binder.bind(sourceProps);

        return targetDTO;
    }

    private static MutablePropertyValues getPropsFrom(Map<String, String[]> parameterMap) {
        final MutablePropertyValues mpvs = new MutablePropertyValues();

        parameterMap.forEach(
                (k, v) -> {
                    String dotKey =
                            k.replaceAll("\\[]", "")
                             .replaceAll("\\[(\\D+)", ".$1")
                             .replaceAll("]\\[(\\D)", ".$1")
                             .replaceAll("(\\.\\w+)]", "$1");
                    mpvs.addPropertyValue(dotKey, v);
                }
        );

        return mpvs;
    }

    public static List<String> getParamDefinitionCodesFromUrlString(String urlString) throws Exception {

//        String urlString = "/v1/logs/basket/deal.11st.co.kr/14782654128048583755870?callback=jQuery112403134002401275859_1492154992123&url=http%3A%2F%2Fdeal.11st.co.kr%2Fproduct%2FSellerProductDetail.tmall%3Fmethod%3DgetSellerProductDetail%26prdNo%3D1117766194%26xfrom%3D%26xzone%3D&ref=http%3A%2F%2Fbuy.11st.co.kr%2Forder%2FOrderList.tmall&b=eyJ1c2VyIjp7ImJpcnRoeWVhciI6IjE5NTgiLCJnZW5kZXIiOiJGIiwibWlkIjoiNTQ2NjMzNzg3Njg5MjE5MTIxNyJ9fQ%3D%3D&items%5B0%5D%5Bid%5D=1117766194&items%5B0%5D%5Bc1%5D=+%EC%9E%90%EB%8F%99%EC%B0%A8%EC%9A%A9%ED%92%88+&items%5B0%5D%5Bc2%5D=+%EC%84%B8%EC%B0%A8%2F%EA%B4%80%EB%A6%AC%EC%9A%A9%ED%92%88+&items%5B0%5D%5Bc3%5D=+%EC%B9%B4%EC%83%B4%ED%91%B8&items%5B0%5D%5Bc4%5D=0&items%5B0%5D%5Bcount%5D=3&items%5B0%5D%5Bcatalog%5D=%5B0%2C%20%EC%B9%B4%ED%83%88%EB%A1%9C%EA%B7%B81%2C%20%EC%B9%B4%ED%83%88%EB%A1%9C%EA%B7%B82%2C%203%5D&items%5B0%5D%5Boptions%5D%5B0%5D%5Bstck_no%5D=6135264233&items%5B0%5D%5Boptions%5D%5B0%5D%5Bopt_no%5D=1%3A2&items%5B0%5D%5Boptions%5D%5B0%5D%5Bname%5D=A11.%EC%86%8C%EB%82%99%EC%8A%A4+%EB%A9%80%ED%8B%B0%EB%B0%94%EC%8A%A4%EC%BC%93+-+8L&items%5B0%5D%5Boptions%5D%5B0%5D%5Bqty%5D=1&items%5B0%5D%5Boptions%5D%5B1%5D%5Bstck_no%5D=6031636659&items%5B0%5D%5Boptions%5D%5B1%5D%5Bopt_no%5D=1%3A13&items%5B0%5D%5Boptions%5D%5B1%5D%5Bname%5D=C03.%EC%86%8C%EB%82%99%EC%8A%A4+%EA%B3%A0%EC%86%8D%EC%BD%94%ED%8C%85%EC%99%81%EC%8A%A4+-+500ml&items%5B0%5D%5Boptions%5D%5B1%5D%5Bqty%5D=1&items%5B0%5D%5Boptions%5D%5B2%5D%5Bstck_no%5D=6031636679&items%5B0%5D%5Boptions%5D%5B2%5D%5Bopt_no%5D=1%3A32&items%5B0%5D%5Boptions%5D%5B2%5D%5Bname%5D=F01.%EC%86%8C%EB%82%99%EC%8A%A4+%EC%BB%B4%ED%8C%8C%EC%9A%B4%EB%93%9C+-+75ml&items%5B0%5D%5Boptions%5D%5B2%5D%5Bqty%5D=1&_=1492154992124";

        String urlParamsString = getURLDecodedParamsString(urlString);

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
        final String splittedByAmpersand = s.replaceAll("\\&", "\n");
        final List<String> urlParamsByLines = Arrays.asList(splittedByAmpersand.split("\n"));
        final List<String[]> defParamsByLines = new ArrayList<>();

        for (String line : urlParamsByLines) {
            final String[] paramTuple = new String[] {"", ""};
            paramTuple[0] = FINAL_STRING + line.substring(0, line.indexOf("="));
            paramTuple[1] = line.substring(line.indexOf("=") + 1);

            defParamsByLines.add(paramTuple);
        }
        return defParamsByLines;
    }

    public static List<String> getParamMethodCodes(String urlString) throws Exception {

//        String urlString = "/v1/logs/basket/deal.11st.co.kr/14782654128048583755870?callback=jQuery112403134002401275859_1492154992123&url=http%3A%2F%2Fdeal.11st.co.kr%2Fproduct%2FSellerProductDetail.tmall%3Fmethod%3DgetSellerProductDetail%26prdNo%3D1117766194%26xfrom%3D%26xzone%3D&ref=http%3A%2F%2Fbuy.11st.co.kr%2Forder%2FOrderList.tmall&b=eyJ1c2VyIjp7ImJpcnRoeWVhciI6IjE5NTgiLCJnZW5kZXIiOiJGIiwibWlkIjoiNTQ2NjMzNzg3Njg5MjE5MTIxNyJ9fQ%3D%3D&items%5B0%5D%5Bid%5D=1117766194&items%5B0%5D%5Bc1%5D=+%EC%9E%90%EB%8F%99%EC%B0%A8%EC%9A%A9%ED%92%88+&items%5B0%5D%5Bc2%5D=+%EC%84%B8%EC%B0%A8%2F%EA%B4%80%EB%A6%AC%EC%9A%A9%ED%92%88+&items%5B0%5D%5Bc3%5D=+%EC%B9%B4%EC%83%B4%ED%91%B8&items%5B0%5D%5Bc4%5D=0&items%5B0%5D%5Bcount%5D=3&items%5B0%5D%5Bcatalog%5D=%5B0%2C%20%EC%B9%B4%ED%83%88%EB%A1%9C%EA%B7%B81%2C%20%EC%B9%B4%ED%83%88%EB%A1%9C%EA%B7%B82%2C%203%5D&items%5B0%5D%5Boptions%5D%5B0%5D%5Bstck_no%5D=6135264233&items%5B0%5D%5Boptions%5D%5B0%5D%5Bopt_no%5D=1%3A2&items%5B0%5D%5Boptions%5D%5B0%5D%5Bname%5D=A11.%EC%86%8C%EB%82%99%EC%8A%A4+%EB%A9%80%ED%8B%B0%EB%B0%94%EC%8A%A4%EC%BC%93+-+8L&items%5B0%5D%5Boptions%5D%5B0%5D%5Bqty%5D=1&items%5B0%5D%5Boptions%5D%5B1%5D%5Bstck_no%5D=6031636659&items%5B0%5D%5Boptions%5D%5B1%5D%5Bopt_no%5D=1%3A13&items%5B0%5D%5Boptions%5D%5B1%5D%5Bname%5D=C03.%EC%86%8C%EB%82%99%EC%8A%A4+%EA%B3%A0%EC%86%8D%EC%BD%94%ED%8C%85%EC%99%81%EC%8A%A4+-+500ml&items%5B0%5D%5Boptions%5D%5B1%5D%5Bqty%5D=1&items%5B0%5D%5Boptions%5D%5B2%5D%5Bstck_no%5D=6031636679&items%5B0%5D%5Boptions%5D%5B2%5D%5Bopt_no%5D=1%3A32&items%5B0%5D%5Boptions%5D%5B2%5D%5Bname%5D=F01.%EC%86%8C%EB%82%99%EC%8A%A4+%EC%BB%B4%ED%8C%8C%EC%9A%B4%EB%93%9C+-+75ml&items%5B0%5D%5Boptions%5D%5B2%5D%5Bqty%5D=1&_=1492154992124";

        String urlParamsString = getURLDecodedParamsString(urlString);

        final List<String[]> firstKVStrings = getFirstKVStrings(urlParamsString);

        final List<String> paramMethodsLines = new ArrayList<>();
        for (String[] tuple : firstKVStrings) {
            paramMethodsLines.add(
                    ".param(\"" + tuple[0].substring(FINAL_STRING.length()) + "\", " + convertSquareBracketToUnderscore(tuple[0].substring(FINAL_STRING.length())) + ")"
            );
        }

        return paramMethodsLines;
    }

    private static String getURLDecodedParamsString(String urlString) throws UnsupportedEncodingException {
        String urlDecodedString = URLDecoder.decode(urlString, "UTF-8");
        return urlDecodedString.substring(urlDecodedString.indexOf('?') + 1);
    }

    private static String convertSquareBracketToUnderscore(String s) {
        return s.replaceAll("]\\[", "_")
                .replaceAll("\\[", "_")
                .replaceAll("]", "");
    }
}
