package sdkdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdkdata.entity.NativeProject;
import sdkdata.exception.DataFormatException;
import sdkdata.entity.Project;
import sdkdata.exception.ErrorCode;
import sdkdata.exception.ProjectException;
import sdkdata.repository.ProjectRepository;

import java.util.*;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project fetchProject() {
        return Optional.ofNullable(projectRepository.findTopByOrderByIdDesc())
                .orElseThrow(() -> new ProjectException(ErrorCode.PROJECT_NOT_FOUND));
    }

    public <T> Project createProject(Map<String, T> dumpData) throws DataFormatException {
        // 프로젝트 종류 체크해주는 로직 필요
        NativeProject project = new NativeProject();

        String projId = createProjectId(dumpData);
        project.setProjectId(projId);

        ArrayList dataList = createData(dumpData);

        HashSet typeSet = createType(dataList);

        // renderType 사용하여 render 부분 분리
        ArrayList renderList = createRenderList(dataList);
        project.setRenderYn(project.renderTest(renderList));

        project.setAgentYn(createAgent(typeSet));
//        project.setRenderYn(createRender(typeSet));
        project.setRequestYn(createRequest(typeSet));
        project.setWebviewYn(createWebview(typeSet));

        projectRepository.save(project);
        return Optional.ofNullable(projectRepository.findFirstByProjectId(projId))
                .orElseThrow(() -> new ProjectException(ErrorCode.PROJECT_NOT_CREATED));
    }

    public <T> String createProjectId(Map<String, T> dumpData) {
        Object id = dumpData.get("project_key");
        if (id == null) {
            throw new DataFormatException("There is no project KEY", ErrorCode.NULL_DATA);
        } else if (id.getClass() != String.class) {
            throw new DataFormatException("The format of project KEY is wrong", ErrorCode.WRONG_DATA_TYPE);
        }
        return (String) id;
    }

    public <T> ArrayList createData(Map<String, T> dumpData) throws DataFormatException {
        T dataList = dumpData.get("data");
        if (dataList == null) {
            throw new DataFormatException("There is no Project DATA array", ErrorCode.NULL_DATA);
        } else if (dataList.getClass() != ArrayList.class) {
            throw new DataFormatException("The format of project DATA array is wrong", ErrorCode.WRONG_DATA_TYPE);
        }
        return (ArrayList) dataList;
    }

    public HashSet createType(ArrayList dataList) throws DataFormatException {
        HashSet typeSet = new HashSet();
        for (Object data: dataList) {
            if (data == null) {
                throw new DataFormatException("There is no DATA", ErrorCode.NULL_DATA);
            } else if (data.getClass() != LinkedHashMap.class) {
                throw new DataFormatException("The format of DATA in data array is wrong", ErrorCode.WRONG_DATA_TYPE);
            }
            LinkedHashMap eachData = (LinkedHashMap) data;
            Object type = eachData.get("type");
            if (type == null) {
                throw new DataFormatException("There is no TYPE", ErrorCode.NULL_DATA);
            } else if (type.getClass() != String.class) {
                throw new DataFormatException("The format of TYPE is wrong", ErrorCode.WRONG_DATA_TYPE);
            }
            typeSet.add(type);
        }
        return typeSet;
    }

    public Boolean createAgent(HashSet typeSet) {
        return typeSet.contains("agent_lifecycle");
    }

    public Boolean createRender(HashSet typeSet) {
        return typeSet.contains("render");
    }

    public Boolean createRequest(HashSet typeSet) {
        return typeSet.contains("request");
    }

    private Boolean createWebview(HashSet typeSet) {
        return typeSet.contains("webview");
    }

    private <T> ArrayList createRenderList(ArrayList<LinkedHashMap> dataList) throws DataFormatException {
        ArrayList renderList = new ArrayList();
        for (LinkedHashMap<String, String> data: dataList) {
            String type = data.get("type");
            if (type == "render") {
                renderList.add(data);
            }
        }
        return renderList;
    }

    public ArrayList<LinkedHashMap> createOsList(ArrayList<LinkedHashMap> resList) throws DataFormatException {
        ArrayList osList = new ArrayList();
        if (!resList.isEmpty()) {
            for (LinkedHashMap<String, LinkedHashMap> res: resList) {
//                if (res == null) {
//                    throw new DataFormatException("There is no RES data", ErrorCode.NULL_DATA);
//                } else if (res.getClass() != LinkedHashMap.class) {
//                    throw new DataFormatException("The format of RES data is wrong", ErrorCode.WRONG_DATA_TYPE);
//                }
                LinkedHashMap osData = res.get("os");
//                if (osData == null) {
//                    throw new DataFormatException("There is no OS data", ErrorCode.NULL_DATA);
//                } else if (osData.getClass() != LinkedHashMap.class) {
//                    throw new DataFormatException("The format of OS data is wrong", ErrorCode.WRONG_DATA_TYPE);
//                }
                osList.add(osData);
            }
            return osList;
        }
        return null;
    }
}
