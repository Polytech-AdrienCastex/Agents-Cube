package cubes;

import java.util.Random;

/**
 *
 * @author p1002239
 */
public class Agent
{
    public Agent(String name, String but)
    {
        this.but = but;
        this.name = name;
        this.rnd = new Random();
    }
    
    private final String but;
    private final String name;
    
    private final Random rnd;
    
    public String getName()
    {
        return name;
    }
    
    private EnvironnementVariable env;
    public void getEnv(Environnement context)
    {
        env = context.getEnvironnement(this);
    }
    
    public boolean isSatisfied()
    {
        return (
                    (but == null && env.agentUnder == null)
                    || (env.agentUnder != null && but != null && but.equals(env.agentUnder.name))
                ) && !env.isPushed;
    }
    
    public void act(Environnement context)
    {
        if(!isSatisfied())
        {
            if(env.agentOver != null)
            { // Non libre
                context.push(env.agentOver);
            }
            else
            { // Libre
                context.moveTo(this, rnd.nextInt(2) == 0 ? env.place1 : env.place2);
            }
        }
    }
}
