package sdkdata.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sdkdata.renderType.NativeRenderType;
import sdkdata.renderType.RenderType;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
public class NativeProject extends Project {
    RenderType nativeRenderType = new NativeRenderType();

    @Builder
    public NativeProject(String projectId, Boolean renderYn, Boolean agentYn, String osInfo,
                         Boolean requestYn, Boolean webviewYn) {
        this.setProjectId(projectId);
        this.setRenderYn(renderYn);
        this.setAgentYn(agentYn);
        this.setOsInfo(osInfo);
        this.setRequestYn(requestYn);
        this.setWebviewYn(webviewYn);
    }
    public boolean renderTest(ArrayList renderList) {
        return nativeRenderType.renderYn(renderList);
    }
}
