package ru.itis.kpfu.selyantsev.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.selyantsev.dto.request.UserRequest;
import ru.itis.kpfu.selyantsev.dto.response.UserResponse;
import ru.itis.kpfu.selyantsev.model.Subscription;
import ru.itis.kpfu.selyantsev.model.FollowerStatus;
import ru.itis.kpfu.selyantsev.model.User;
import ru.itis.kpfu.selyantsev.repository.FollowerRepository;
import ru.itis.kpfu.selyantsev.service.SubscriptionService;
import ru.itis.kpfu.selyantsev.service.UserService;
import ru.itis.kpfu.selyantsev.utils.mappers.UserMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseSubscriptionService implements SubscriptionService {

    private final UserService userService;
    private final UserMapper mapper;
    private final FollowerRepository followerRepository;

    @Override
    public void addFollower(UserRequest follower) {
        User currentUser = userService.getCurrentUser();
        User userFromDB = mapper.toEntityFromResponse(userService.findUserByUsername(follower.getUsername()));
        Subscription followerToSave = Subscription.builder()
                .user(currentUser)
                .follower(userFromDB)
                .status(FollowerStatus.REQUEST_SENT)
                .build();
        followerRepository.save(followerToSave);
    }

    @Override
    public void removeFollower(UserRequest follower) {
        User currentUser = userService.getCurrentUser();
        User userFromDatabase = mapper.toEntityFromResponse(userService.findUserByUsername(follower.getUsername()));
        followerRepository.deleteFriendship(currentUser, userFromDatabase);
    }

    @Override
    public Long countFollowers() {
        User currentUser = userService.getCurrentUser();
        return followerRepository.findAll()
                .stream()
                .filter(follower -> follower.getFollower().getId() == currentUser.getId()
                        & follower.getStatus() == FollowerStatus.ACCEPTED)
                .count();
    }

    @Override
    public List<Subscription> findFollowerRequests() {
        UserResponse currentUser = mapper.toResponse(userService.getCurrentUser());
        return followerRepository.findAll()
                .stream()
                .filter(follower -> currentUser.getId() == follower.getFollower().getId()
                        & (follower.getStatus() == FollowerStatus.REQUEST_SENT)).toList();
    }

    @Override
    public void updateFollowersStatus() {
        User currentUser = userService.getCurrentUser();
        followerRepository.findAll()
                .forEach(follower -> {
                    if (currentUser.getId() == follower.getFollower().getId()
                            & follower.getStatus() == FollowerStatus.REQUEST_SENT) {
                        follower.setStatus(FollowerStatus.ACCEPTED);
                        followerRepository.save(follower);
                    }
                });
    }

    @Override
    public boolean isFollower(UserRequest request) {
        User currentUser = userService.getCurrentUser();
        User isFollower = mapper.toEntity(request);
        // get follower for current user check if null
        List<Subscription> followers = followerRepository.findAll()
                .stream()
                .filter(follower -> follower.getUser().getId() == currentUser.getId())
                .toList();
        for (Subscription follower : followers) {
            if ((follower.getFollower().getId() == isFollower.getId())
                    & follower.getStatus() == FollowerStatus.ACCEPTED) {
                return true;
            }
        }
        return false;
    }
}
