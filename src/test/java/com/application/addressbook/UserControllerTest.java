package com.application.addressbook;

import com.application.addressbook.controllers.UserController;
import com.application.addressbook.models.User;
import com.application.addressbook.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;


public class UserControllerTest {
    @Mock
    private UserService userService;

    private UserController userController;

    @BeforeEach
    void setup() {
        this.userController = new UserController(userService);
    }

    @Test
    @DisplayName("Adding user payload must not have null name")
    public void testAddUserPayload() {
        User newUser = new User();
        assertThatIllegalArgumentException().isThrownBy(() -> userController.addNewUser(newUser))
                .withMessage("Received null user name");
    }


}
