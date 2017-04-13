package homo.efficio.springboot.scratchpad.jackson.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017. 4. 11.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String city;
    private String goo;
    private String street;
    private String number;
}
