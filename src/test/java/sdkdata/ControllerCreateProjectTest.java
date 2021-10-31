package sdkdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import sdkdata.controller.ProjectController;
import sdkdata.entity.NativeProject;
import sdkdata.repository.ProjectRepository;
import sdkdata.service.ProjectService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@WebMvcTest(controllers = ProjectController.class)
@ActiveProfiles("test")
public class ControllerCreateProjectTest<T> {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    ProjectRepository projectRepository;

    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private ProjectService projectService;

    private NativeProject testNativeProject() {
        String id = "123";
        return new NativeProject(id, true, true, "osInfo", true, false);
    }

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

    private Map<String, T> dataObject() {
        Map<String, T> dataObject = new LinkedHashMap<>();
        dataObject.put("project_key", (T) "123");
        dataObject.put("data", (T) dataList());
        return dataObject;
    };

    @Test
    void shouldCreateProject() throws Exception{
        Map dataObject = dataObject();
        Mockito.when(projectRepository.save(testNativeProject())).thenReturn(new NativeProject());
        this.mockMvc.perform(post("/sdkguide/projects")
                .content(mapper.writeValueAsString(dataObject))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
//    @Test
//    void shouldNotCreateProject() throws Exception{
//        Map dataObject = dataObject();
////        projectRepository.findFirstByProjectId("abcdefg")
//        Mockito.when(projectRepository.save(testNativeProject())).thenReturn(new NativeProject());
//        Mockito.when(projectRepository.findFirstByProjectId("abcdefg")).thenReturn(new NativeProject());
//    }
}
