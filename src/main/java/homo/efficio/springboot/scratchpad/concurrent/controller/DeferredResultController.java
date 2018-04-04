package homo.efficio.springboot.scratchpad.concurrent.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

/**
 * @author homo.efficio@gmail.com
 * created on 2018-02-28
 */
@RequestMapping("/deferred-result")
public class DeferredResultController {

    // CompletableFuture를 쓴다면 굳이 DeferredResult를 반환할 필요없이
    // 그냥 CompletableFuture를 반환하면 되므로
    // 아래와 같이 쓸 필요는 없음
    @GetMapping("/simple-deferred-result")
    public DeferredResult<ResponseEntity<String>> getSimpleDeferredResult() {

        System.out.println("Servlet Thread: " + Thread.currentThread().getName());

        DeferredResult<ResponseEntity<String>> dfr = new DeferredResult<>();

        CompletableFuture.supplyAsync(() -> {
                System.out.println("Inner Thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "AsyncResult";
            }).whenCompleteAsync(
                    (result, throwable) -> dfr.setResult(ResponseEntity.ok(result))
            );

        System.out.println("Just for checking Async");

        return dfr;
    }
}
