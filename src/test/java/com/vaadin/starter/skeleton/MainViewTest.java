package com.vaadin.starter.skeleton;

import com.github.mvysny.kaributesting.v10.MockVaadin;
import com.github.mvysny.kaributesting.v10.Routes;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.github.mvysny.kaributesting.v10.LocatorJ.*;
import static com.github.mvysny.kaributesting.v10.NotificationsKt.expectNotifications;

/**
 * Tests the UI. Uses the Browserless testing approach as provided by the [Karibu Testing](https://github.com/mvysny/karibu-testing) library.
 * @author mavi
 */
public class MainViewTest {

    private static Routes routes;

    @BeforeClass
    public static void discoverRoutes() {
        routes = new Routes().autoDiscoverViews("com.vaadin.starter.skeleton");
    }

    @Before
    public void setupVaadin() {
        MockVaadin.setup(routes);
    }

    @After
    public void tearDownVaadin() {
        MockVaadin.tearDown();
    }

    @Test
    public void helloTest() {
        UI.getCurrent().navigate(MainView.class);
        _assertOne(MainView.class);

        _setValue(_get(TextField.class, spec -> spec.withCaption("Your name")), "Martin");
        _click(_get(Button.class, spec -> spec.withCaption("Say hello")));

        expectNotifications("Hello Martin");
    }
}
