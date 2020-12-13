package com.application.addressbook.services;

import com.application.addressbook.exceptions.InvalidResourceException;
import com.application.addressbook.models.Friend;
import com.application.addressbook.models.User;
import com.application.addressbook.repository.UserRepository;
import org.apache.commons.collections4.SetUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addNewUser(User user) {
        return userRepository.save(user);
    }

    private User getUser(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new InvalidResourceException(String.format("User not found [id: %s]", userId)));
    }

    public Set<Friend> getUniqueFriends(String userId, Set<Friend> setOfFriends) {
        User user = getUser(userId);
        Set<Friend> userFriends = user.getAddressBook();

        return SetUtils.disjunction(userFriends, setOfFriends);
    }

    public Set<Friend> getFriends(String userId) {
        User user = getUser(userId);

        return user.getAddressBook();
    }

    public User addNewFriend(String userId, Friend friend) {
        User user = getUser(userId);

        user.getAddressBook().add(friend);
        userRepository.save(user);

        return user;
    }

}
