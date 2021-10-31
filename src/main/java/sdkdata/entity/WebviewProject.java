package sdkdata.entity;

import lombok.Builder;
import sdkdata.renderType.RenderType;
import sdkdata.renderType.WebviewRenderType;

import java.util.ArrayList;

public class WebviewProject extends Project {
    RenderType webviewRenderType = new WebviewRenderType();

    @Builder
    public WebviewProject(String projectId, Boolean renderYn, Boolean agentYn, String osInfo,
                         Boolean requestYn, Boolean webviewYn) {
        this.setProjectId(projectId);
        this.setRenderYn(renderYn);
        this.setAgentYn(agentYn);
        this.setOsInfo(osInfo);
        this.setRequestYn(requestYn);
        this.setWebviewYn(webviewYn);
    }
    public boolean renderTest(ArrayList renderList) {
        return webviewRenderType.renderYn(renderList);
    }
}
