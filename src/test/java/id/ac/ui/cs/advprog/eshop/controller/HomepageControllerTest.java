package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
class HomepageControllerTest {
    @InjectMocks
    private HomepageController homepageController;

    @Test
    void testHomePage() {
        Model model = mock(Model.class);
        String viewName = homepageController.homePage(model);
        assertEquals("homepage", viewName);
    }
}
