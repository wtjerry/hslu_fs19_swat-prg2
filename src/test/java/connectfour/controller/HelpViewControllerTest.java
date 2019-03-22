package connectfour.controller;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HelpViewControllerTest {
    @Test
    public void backPressedNavigatesToStartView() {
        Navigator navigatorMock = mock(Navigator.class);
        HelpViewController helpViewController = new HelpViewController(navigatorMock);

        helpViewController.backPressed();

        verify(navigatorMock).navigateToStartView();
    }
}
