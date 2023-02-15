package com.ourstocks.jwtapp.repository;

import com.ourstocks.jwtapp.model.UserFollows;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface UserFollowsRepository extends JpaRepository<UserFollows, Long> {
    Set<UserFollows> findAllByDistributor(Long distributor);
    Set<UserFollows> findAllBySubscriber(Long subscriber);

}
