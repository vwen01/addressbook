package com.application.addressbook;


import com.application.addressbook.exceptions.InvalidResourceException;
import com.application.addressbook.models.Friend;
import com.application.addressbook.models.User;
import com.application.addressbook.repository.UserRepository;
import com.application.addressbook.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;
    private String userId;
    private User testUser;

    @BeforeEach
    void setup() {
        userService = new UserService(userRepository);
        userId = "5fd5e958d1037b69ccd67d0ds";
        testUser = new User();
    }

    @Test
    @DisplayName("Testing getting a unique set of friends list")
    public void testGetUniqueFriends() {
        Set<Friend> friendSet = new HashSet<>();
        friendSet.add(createFriendForTesting("Bob", "12345"));
        friendSet.add(createFriendForTesting("Mary", "11111"));
        friendSet.add(createFriendForTesting("Jane", "00111"));
        testUser.setAddressBook(friendSet);
        when(userRepository.findById("5fd5e958d1037b69ccd67d0ds")).thenReturn(Optional.of(testUser));

        Set<Friend> friendSetRequest = new HashSet<>();
        friendSetRequest.add(createFriendForTesting("Mary", "11111"));
        friendSetRequest.add(createFriendForTesting("John", "22222"));
        friendSetRequest.add(createFriendForTesting("Jane", "00111"));

        Set<Friend> names = userService.getUniqueFriends("5fd5e958d1037b69ccd67d0ds", friendSetRequest);

        Assertions.assertEquals(names.size(), 2);
        Assertions.assertTrue(names.contains(createFriendForTesting("John", "22222")));
        Assertions.assertTrue(names.contains(createFriendForTesting("Bob", "12345")));
        Assertions.assertFalse(names.contains(createFriendForTesting("Jane", "00111")));
    }

    @Test
    @DisplayName("Testing getting friends list by userId")
    public void testGetFriends() {
        Set<Friend> friendSet = new HashSet<>();
        friendSet.add(createFriendForTesting("Vince", "12345"));
        friendSet.add(createFriendForTesting("John", "11111"));

        testUser.setAddressBook(friendSet);

        when(userRepository.findById("5fd5e958d1037b69ccd67d0ds")).thenReturn(Optional.of(testUser));

        Set<Friend> phoneBook = userService.getFriends(userId);

        Assertions.assertFalse(phoneBook.isEmpty());
        Assertions.assertEquals(phoneBook.size(), 2);
        Assertions.assertTrue(phoneBook.contains(createFriendForTesting("John", "11111")));
    }

    @Test
    @DisplayName("Testing getting friends list by userId")
    public void testGetFriendsWithInvalidUserThrowsException() throws InvalidResourceException {
        String userId = "5fd5e958d1037b69ccd67d0ds";
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Assertions.assertThrows(InvalidResourceException.class, () -> {
            userService.getFriends(userId);
        });

    }

    @Test
    @DisplayName("Testing adding new friend to user")
    public void testAddNewFriend() {
        Set<Friend> friendSet = new HashSet<>();
        Friend newFriend = createFriendForTesting("Terry", "000000");
        testUser.setAddressBook(friendSet);

        when(userRepository.findById("5fd5e958d1037b69ccd67d0ds")).thenReturn(Optional.of(testUser));
        userService.addNewFriend("5fd5e958d1037b69ccd67d0ds", newFriend);

        Set<Friend> phoneBook = userService.getFriends(userId);
        Assertions.assertTrue(phoneBook.contains(newFriend));
    }

    @Test
    @DisplayName("Testing adding new user")
    public void testAddNewUser() {
        User newUser = testUser;
        when(userRepository.save(testUser)).thenReturn(newUser);

        User createdUser = userService.addNewUser(testUser);

        Assertions.assertEquals(createdUser.getUserId(), newUser.getUserId());
        Assertions.assertEquals(createdUser.getName(), newUser.getName());
        Assertions.assertEquals(createdUser.getAddressBook(), newUser.getAddressBook());

    }

    private Friend createFriendForTesting(String name, String phoneNumber) {
        Friend friend = new Friend();
        friend.setName(name);
        friend.setPhoneNumber(phoneNumber);

        return friend;
    }

}
