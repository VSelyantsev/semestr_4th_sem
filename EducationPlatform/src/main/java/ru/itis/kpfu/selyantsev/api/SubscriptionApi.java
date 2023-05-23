package ru.itis.kpfu.selyantsev.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.kpfu.selyantsev.dto.request.UserRequest;

@RequestMapping("api/follower")
public interface SubscriptionApi {

    @PostMapping("/addFollower")
    String addFollower(@RequestBody UserRequest friend);

    @PostMapping("/remove")
    @ResponseStatus(HttpStatus.OK)
    String removeFollower(@RequestBody UserRequest friend);

    @GetMapping("/counted")
    void countFollowers();

    @ResponseStatus(HttpStatus.OK)
    void findFollowerRequests();

    @GetMapping("/requests")
    @ResponseStatus(HttpStatus.OK)
    void updateFollowersStatus();
}
