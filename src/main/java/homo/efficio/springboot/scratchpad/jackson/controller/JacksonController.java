package homo.efficio.springboot.scratchpad.jackson.controller;

import homo.efficio.springboot.scratchpad.jackson.model.UserInfo;
import homo.efficio.springboot.scratchpad.jackson.model.UserInfoWithAddress;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017. 4. 11.
 */
@RequestMapping("/jackson")
@RestController
public class JacksonController {

    @RequestMapping(value = "/simple-value-list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserInfo> saveUserInfo(@RequestBody UserInfo userInfo) {

        return ResponseEntity.ok(userInfo);
    }

    @RequestMapping(value = "/object-list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserInfoWithAddress> saveUserInfo(@RequestBody UserInfoWithAddress userInfoWithAddress) {

        return ResponseEntity.ok(userInfoWithAddress);
    }
}
