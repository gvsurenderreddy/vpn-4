package net.lliira.vpn.service;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ServiceTest {
  private final VpnService service;

  public ServiceTest() {
    service = new VpnService();
  }

  @Test
  public void testStartService() throws IOException, VpnServiceException {
    service.start();

    try {
      Thread.sleep(5 * 1000);
    } catch (InterruptedException ex) {}

    // remove running flag
    File file = new File(VpnService.SIGNAL_FILE);
    Assert.assertTrue(file.exists());
    file.delete();
  }
}
