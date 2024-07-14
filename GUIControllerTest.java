
import org.junit.Before;
import org.junit.Test;

import mock.MockGUIController;
import mock.MockModel;
import model.IModel;
import model.RetrivableClient;

import view.gui.IViewGUI;
import view.gui.TextViewGUI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests for GUIControllerTest.
 */
public class GUIControllerTest {

  private MockGUIController controller;
  IModel mockModel;
  IViewGUI mockView;

  @Before
  public void setUp() {
    mockModel = new MockModel();
    mockView = new TextViewGUI();
    controller = new MockGUIController(mockModel, mockView);
  }

  @Test
  public void testGoController() {
    controller.goController();
    assertEquals("go controller", controller.getText(), "go controller");
  }

  @Test
  public void testSetClient() {
    RetrivableClient client = new RetrivableClient("test");
    controller.setClient(client);
    assertEquals("set client", controller.getText(), "set client");
  }

  @Test
  public void testGetUser() {
    RetrivableClient user = controller.getUser();
    assertNotNull("User should not be null",user);
    assertEquals("Get User", user.getName(), "Get User");
  }
}

