package homo.efficio.springboot.scratchpad.jackson.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017. 4. 11.
 */
@Getter
@Setter
@NoArgsConstructor
public class UserInfoWithObjectArray extends UserInfo {

    private Address[] addresses;

    public UserInfoWithObjectArray(String id, Address[] addresses) {
        super(id);
        this.addresses = addresses;
    }
}
