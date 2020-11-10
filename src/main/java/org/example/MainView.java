package org.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.*;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.shared.communication.PushMode;
import com.vaadin.flow.shared.ui.Transport;

/**
 * The main view contains a button and a click listener.
 */
@Push(value = PushMode.AUTOMATIC, transport = Transport.WEBSOCKET)
@Route
@PWA(name = "My Application", shortName = "My Application")
public class MainView extends VerticalLayout implements AppShellConfigurator
{
  public MainView()
  {
    PersonGrid personGrid = new PersonGrid();
    PersonAgGrid personAgGrid = new PersonAgGrid();
    personAgGrid.refreshColumnDefs();
    Button button = new Button("Click me", event -> Notification.show("Clicked!"));
    Button button2 = new Button("Request", event -> personGrid.startReqeust());

    setSizeFull();
    //add(personGrid);
    add(personAgGrid);
    add(button);
    add(button2);
  }
}
