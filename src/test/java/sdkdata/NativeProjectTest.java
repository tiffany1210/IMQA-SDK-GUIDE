package sdkdata;

import org.springframework.beans.factory.annotation.Autowired;
import sdkdata.entity.NativeProject;
import org.junit.Test;
import sdkdata.service.ProjectService;

import static org.junit.Assert.assertEquals;

public class NativeProjectTest {

    @Autowired
    ProjectService projectService;

    private NativeProject testNativeProject() {
        String id = "123";
        return new NativeProject(id, true, true, "osInfo", true, false);
    }
    @Test
    public void testNativeAgent() {
        NativeProject np = testNativeProject();
        assertEquals(true, np.getAgentYn());
    }
    @Test
    public void testNativeRender() {
        NativeProject np = testNativeProject();
        assertEquals(true, np.getRenderYn());
    }
    @Test
    public void testNativeRequest() {
        NativeProject np = testNativeProject();
        assertEquals(true, np.getRequestYn());
    }
    @Test
    public void testWebview() {
        NativeProject np = testNativeProject();
        assertEquals(false, np.getWebviewYn());
    }
}
