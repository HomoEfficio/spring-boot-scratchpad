package homo.efficio.springboot.scratchpad.jackson.controller;

import homo.efficio.springboot.scratchpad.jackson.model.UserInfo;
import homo.efficio.springboot.scratchpad.jackson.model.UserInfoWithObjectArray;
import homo.efficio.springboot.scratchpad.jackson.model.UserInfoWithObjectList;
import homo.efficio.springboot.scratchpad.jackson.model.UserInfoWithSimpleList;
import homo.efficio.springboot.scratchpad.jackson.util.DataBinderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017. 4. 11.
 */
@Slf4j
@RequestMapping("/jackson")
@RestController
public class JacksonController {

    @RequestMapping(value = "/simple-value-array-to-dto-with-list/post", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserInfoWithSimpleList> postSimpleValueListToDtoWithList(@RequestBody UserInfoWithSimpleList userInfoWithSimpleList) {

        return ResponseEntity.ok(userInfoWithSimpleList);
    }

    @RequestMapping(value = "/object-array-to-dto-with-list/post", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserInfoWithObjectList> postObjectListToDtoWithList(@RequestBody UserInfoWithObjectList userInfoWithObjectList) {

        return ResponseEntity.ok(userInfoWithObjectList);
    }

    @RequestMapping(value = "/object-array-to-dto-with-array/post", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserInfoWithObjectArray> postObjectListToDtoWithArray(@RequestBody UserInfoWithObjectArray userInfoWithObjectArray) {

        return ResponseEntity.ok(userInfoWithObjectArray);
    }




    @RequestMapping(value = "/simple-value-array-to-dto-with-list/get", method = RequestMethod.GET)
    public ResponseEntity<UserInfoWithSimpleList> getSimpleValueListToDtoWithList(HttpServletRequest request, UserInfoWithSimpleList userInfoWithSimpleList) {

        return ResponseEntity.ok(userInfoWithSimpleList);
    }

    @RequestMapping(value = "/object-array-to-dto-with-list/get", method = RequestMethod.GET)
    public ResponseEntity<UserInfoWithObjectList> getObjectListToDtoWithList(HttpServletRequest request, UserInfoWithObjectList userInfoWithObjectList) {

        return ResponseEntity.ok(userInfoWithObjectList);
    }

    @RequestMapping(value = "/object-array-to-dto-with-array/get", method = RequestMethod.GET)
    public ResponseEntity<UserInfoWithObjectArray> getObjectListToDtoWithArray(HttpServletRequest request, UserInfoWithObjectArray userInfoWithObjectArray) {

        return ResponseEntity.ok(userInfoWithObjectArray);
    }




    @RequestMapping(value = "/object-list/data-binder", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserInfoWithObjectList> saveUserInfoJsonGetDataBinder(HttpServletRequest request) {

        UserInfoWithObjectList userInfoWithObjectList = null;
        try {
            userInfoWithObjectList = DataBinderUtil.getDTOFromParamMap(request.getParameterMap(), UserInfoWithObjectList.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(userInfoWithObjectList);
    }
}
