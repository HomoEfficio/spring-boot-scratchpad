package homo.efficio.springboot.scratchpad.jackson.model;

import java.util.List;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-04-28
 */
public class SampleDTO {

    private String id;
    private List<Address> addresses;
    private List<String> emails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
}
