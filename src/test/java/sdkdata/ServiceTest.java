package sdkdata;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import sdkdata.entity.NativeProject;
import sdkdata.exception.DataFormatException;
import sdkdata.repository.ProjectRepository;
import sdkdata.service.ProjectService;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
@MockBean(ProjectService.class)
public class ServiceTest<T> {

    private ArrayList dataList() {
        LinkedHashMap agentMap = new LinkedHashMap();
        LinkedHashMap renderMap = new LinkedHashMap();
        LinkedHashMap requestMap = new LinkedHashMap();
        LinkedHashMap webviewMap = new LinkedHashMap();
        ArrayList dataList = new ArrayList();
        agentMap.put("type", "agent_lifecycle");
        dataList.add(agentMap);
        renderMap.put("type", "render");
        dataList.add(renderMap);
        requestMap.put("type", "request");
        dataList.add(requestMap);
        webviewMap.put("type", "webview");
        dataList.add(webviewMap);
        return dataList;
    }

    private ArrayList wrongDataList() {
        LinkedHashMap agentMap = new LinkedHashMap();
        LinkedHashMap renderMap = new LinkedHashMap();
        LinkedHashMap requestMap = new LinkedHashMap();
        LinkedHashMap webviewMap = new LinkedHashMap();
        ArrayList dataList = new ArrayList();
        dataList.add(agentMap);
        renderMap.put("type", "render");
        dataList.add(renderMap);
        requestMap.put("type", "request");
        dataList.add(requestMap);
        webviewMap.put("type", "webview");
        dataList.add(webviewMap);
        return dataList;
    }

    private Map<String, T> dataObject() {
        Map<String, T> dataObject = new LinkedHashMap<>();
        dataObject.put("project_key", (T) "123");
        dataObject.put("data", (T) dataList());
        return dataObject;
    };

    private NativeProject testNativeProject() {
        String id = "123";
        return new NativeProject(id, true, true, "osInfo", true, false);
    }

    @Mock
    ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    public void testCreateProjectId() throws DataFormatException {
        Map dataObject = dataObject();
        Mockito.when(projectRepository.save(testNativeProject())).thenReturn(new NativeProject());
        assertEquals("123", projectService.createProjectId(dataObject));
    }
    @Test
    public void testCreateData() throws DataFormatException {
        Map dataObject = dataObject();
        assertEquals(dataList(), projectService.createData(dataObject));
    }
    @Test
    public void testCreateType() throws DataFormatException {
        ArrayList dataList = dataList();
        HashSet typeSet = new HashSet();
        typeSet.add("agent_lifecycle");
        typeSet.add("render");
        typeSet.add("webview");
        typeSet.add("render");
        assertEquals(true, projectService.createType(dataList).containsAll(typeSet));
    }
}
