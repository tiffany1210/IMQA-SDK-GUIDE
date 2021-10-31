package sdkdata.renderType;

import java.util.ArrayList;

public class NativeRenderType implements RenderType {
    @Override
    public Boolean renderYn(ArrayList renderList) {
        System.out.println("native rendering test");
        if (renderList.isEmpty()) {
            return false;
        }
        return true;
    }
}
