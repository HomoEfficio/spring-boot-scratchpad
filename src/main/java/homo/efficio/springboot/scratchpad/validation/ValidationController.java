package homo.efficio.springboot.scratchpad.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author homo.efficio@gmail.com
 * created on 2018-04-04
 */
@RestController
@RequestMapping("/bean-validation")
public class ValidationController {

    @RequestMapping("/period")
//    public ResponseEntity<String> validPeriod(@Valid SearchDto dto, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            System.out.println(bindingResult);
//        }
    public ResponseEntity<String> validPeriod(@Valid SearchDto dto) {
        SearchDto result = new SearchDto(
                dto.getKeyword(),
                dto.getStartDate(),
                dto.getEndDate()
        );

        return ResponseEntity.ok(result.toString());

    }
}
