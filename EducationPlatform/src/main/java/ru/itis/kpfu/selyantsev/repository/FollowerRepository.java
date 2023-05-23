package ru.itis.kpfu.selyantsev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.kpfu.selyantsev.model.Subscription;
import ru.itis.kpfu.selyantsev.model.User;

import java.util.UUID;

@Repository
public interface FollowerRepository extends JpaRepository<Subscription, UUID> {
    @Transactional
    @Modifying
    @Query("DELETE FROM t_subscription f " +
            "WHERE (f.user = :user AND f.follower = :friend) " +
            "OR (f.user = :friend AND f.follower = :user)")
    void deleteFriendship(@Param("user") User user, @Param("friend") User follower);
}
