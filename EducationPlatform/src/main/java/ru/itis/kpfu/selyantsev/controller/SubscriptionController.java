package ru.itis.kpfu.selyantsev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.itis.kpfu.selyantsev.api.SubscriptionApi;
import ru.itis.kpfu.selyantsev.aspect.annotation.CollectData;
import ru.itis.kpfu.selyantsev.dto.request.UserRequest;
import ru.itis.kpfu.selyantsev.service.SubscriptionService;

@Controller
@RequiredArgsConstructor
public class SubscriptionController implements SubscriptionApi {

    private final SubscriptionService subscriptionService;

    @CollectData
    @Override
    public String addFollower(@ModelAttribute("friend") UserRequest follower) {
        subscriptionService.addFollower(follower);
        return "/dashboard";
    }

    @CollectData
    @Override
    public String removeFollower(@ModelAttribute("markedFollower") UserRequest follower) {
        subscriptionService.removeFollower(follower);
        return "/dashboard";
    }

    @CollectData
    @Override
    public void countFollowers() {
        subscriptionService.countFollowers();
    }

    @CollectData
    @Override
    public void findFollowerRequests() {
        subscriptionService.findFollowerRequests();
    }

    @CollectData
    @Override
    public void updateFollowersStatus() {
        subscriptionService.updateFollowersStatus();
    }
}
