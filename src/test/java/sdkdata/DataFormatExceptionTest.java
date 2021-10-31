package sdkdata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import sdkdata.exception.DataFormatException;
import sdkdata.exception.ErrorCode;
import sdkdata.service.ProjectService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@MockBean(ProjectService.class)
public class DataFormatExceptionTest {

    private ArrayList nullAgent() {
        LinkedHashMap agentMap = new LinkedHashMap();
        LinkedHashMap renderMap = new LinkedHashMap();
        LinkedHashMap requestMap = new LinkedHashMap();
        LinkedHashMap webviewMap = new LinkedHashMap();
        ArrayList dataList = new ArrayList();
        agentMap.put("type", null);
        dataList.add(agentMap);
        renderMap.put("type", "render");
        dataList.add(renderMap);
        requestMap.put("type", "request");
        dataList.add(requestMap);
        webviewMap.put("type", "webview");
        dataList.add(webviewMap);
        return dataList;
    }

    private Map<String, String> nullObject() {
        Map<String, String> dataObject = new LinkedHashMap<>();
        return dataObject;
    }
    private Map<String, String> nullProjectKey() {
        Map<String, String> dataObject = new LinkedHashMap<>();
        dataObject.put("project_key", null);
        return dataObject;
    }
    private Map<String, Integer> wrongProjectKey() {
        Map<String, Integer> dataObject = new LinkedHashMap<>();
        dataObject.put("project_key", 123);
        return dataObject;
    }

    @InjectMocks
    private ProjectService projectService;

    @Test
    public void getProjectIdNull() {
        Map dataObject = nullProjectKey();
        try {
            projectService.createProjectId(dataObject);
        } catch (DataFormatException err) {
            assertEquals(ErrorCode.NULL_DATA, err.getCode());
        }
    }

    @Test
    public void getProjectIdWrong() {
        Map dataObject = wrongProjectKey();
        try {
            projectService.createProjectId(dataObject);
        } catch (DataFormatException err) {
            assertEquals(ErrorCode.WRONG_DATA_TYPE, err.getCode());
        }
    }

    @Test
    public void getTypeNull() {
        ArrayList dataList = nullAgent();
        try {
            projectService.createType(dataList);
        } catch (DataFormatException err) {
            assertEquals(ErrorCode.NULL_DATA, err.getCode());
        }
    }

    @Test
    public void getObjectNull() {
        Map dataObject = nullObject();
        try {
            projectService.createData(dataObject);
        } catch (DataFormatException err) {
            assertEquals(ErrorCode.NULL_DATA, err.getCode());
        }
    }
}
