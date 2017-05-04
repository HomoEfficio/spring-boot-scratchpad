package homo.efficio.springboot.scratchpad.util;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-05-04
 */
public class HomoEfficioConverterUtils {

    /**
     * 모델 List를 DTO List로 변환해서 반환
     *
     * @param models        모델 List
     * @param converter     모델 -> DTO 변환 함수
     * @param <M>           모델 타입
     * @param <D>           DTO 타입
     * @return              DTO List
     */
    public static <M, D> List<D> getDtosFrom(List<M> models, Function<M, D> converter) {

        if (models.isEmpty())
            return Collections.emptyList();

        List<D> dtos = models.stream()
                .map(converter)
                .collect(Collectors.toList());

        return dtos;
    }
}
