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
public class UserInfoWithSimpleList extends UserInfo {

    private List<String> emails;

    public UserInfoWithSimpleList(String id, List<String> emails) {
        super(id);
        this.emails = emails;
    }
}
