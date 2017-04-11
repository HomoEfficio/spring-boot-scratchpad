package homo.efficio.springboot.scratchpad.jackson.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017. 4. 11.
 */
@Getter
@AllArgsConstructor
public class UserInfoWithAddress {

    private UserInfo userInfo;
    private List<Address> addresses;
}
