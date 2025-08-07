package uz.brb.card.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8086/api/v1/users")
public interface UserClient {

    @GetMapping("/existsById/{id}")
    boolean existsById(@PathVariable("id") Long id);
}
