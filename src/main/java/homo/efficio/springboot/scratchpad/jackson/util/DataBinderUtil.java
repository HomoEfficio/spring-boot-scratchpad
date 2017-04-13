package homo.efficio.springboot.scratchpad.jackson.util;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.DataBinder;

import java.util.Map;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-04-13
 */
public class DataBinderUtil {

    public static <T> T getDTOFromParamMap(Map<String, String[]> parameterMap, Class<T> dto) throws IllegalAccessException, InstantiationException {

        final MutablePropertyValues source = new MutablePropertyValues();

        parameterMap.forEach(
                (k, v) -> {
                    String dotKey = k.replaceAll("\\[(\\D+)", ".$1")
                            .replaceAll("(\\.\\w+)]", "$1");
                    source.addPropertyValue(dotKey, v);
                }
        );

        T targetDTO = dto.newInstance();
        DataBinder binder = new DataBinder(targetDTO);
        binder.bind(source);

        return targetDTO;
    }
}
