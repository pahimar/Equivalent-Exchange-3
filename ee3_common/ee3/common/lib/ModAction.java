package ee3.common.lib;


public enum ModAction {
    
    TRANSMUTATION(WorldEvents.TRANSMUTATION);
    
    public int actionId;
    
    ModAction(int actionId) {
        this.actionId = actionId;
    }

}
