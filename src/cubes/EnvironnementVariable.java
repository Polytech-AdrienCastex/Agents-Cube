package cubes;

/**
 *
 * @author p1002239
 */
public class EnvironnementVariable
{
    public EnvironnementVariable(
            Agent agentOver,
            Agent agentUnder,
            boolean isPushed,
            int place1,
            int place2)
    {
        this.agentOver = agentOver;
        this.agentUnder = agentUnder;
        this.isPushed = isPushed;
        this.place1 = place1;
        this.place2 = place2;
    }
    
    public final Agent agentOver;
    public final Agent agentUnder;
    public final boolean isPushed;
    public final int place1;
    public final int place2;
}
