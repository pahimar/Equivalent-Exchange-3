package ee3.client.core.handlers;

import net.minecraft.src.RenderEngine;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class DrawBlockHighlightHandler {

    
    @ForgeSubscribe
    public void onDrawBlockHighlightEvent(DrawBlockHighlightEvent event) {
        RenderEngine renderEngine = event.context.renderEngine;
        
        // TODO Magic happens here
    }

}
