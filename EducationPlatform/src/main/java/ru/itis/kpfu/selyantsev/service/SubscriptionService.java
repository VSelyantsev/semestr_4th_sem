package ru.itis.kpfu.selyantsev.service;


import ru.itis.kpfu.selyantsev.dto.request.UserRequest;
import ru.itis.kpfu.selyantsev.model.Subscription;

import java.util.List;

public interface SubscriptionService {

    void addFollower(UserRequest follower);
    void removeFollower(UserRequest follower);

    Long countFollowers();

    List<Subscription> findFollowerRequests();

    void updateFollowersStatus();

    boolean isFollower(UserRequest isFollower);
}
