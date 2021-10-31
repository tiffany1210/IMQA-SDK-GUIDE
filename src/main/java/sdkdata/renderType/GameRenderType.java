package sdkdata.renderType;

import java.util.ArrayList;

public class GameRenderType implements RenderType {
    @Override
    public Boolean renderYn(ArrayList renderList) {
        System.out.println("game rendering test");
        if (renderList.isEmpty()) {
            return false;
        }
        return true;
    }
}
