package sdkdata.renderType;

import java.util.ArrayList;

public class WebviewRenderType implements RenderType {
    @Override
    public Boolean renderYn(ArrayList renderList) {
        System.out.println("webview rendering test");
        if (renderList.isEmpty()) {
            return false;
        }
        return true;
    }
}
