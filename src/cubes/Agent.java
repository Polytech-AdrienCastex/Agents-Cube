package cubes;

import java.util.Random;

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
    private EnvironnementVariable env;
    
    public String getName()
    {
        return name;
    }
    
    public void getEnv(Environnement context)
    {
        env = context.getEnvironnement(this);
    }
    
    public boolean isSatisfied()
    {
        return (
                    (but == null && env.agentUnder == null) // On the table
                    || (env.agentUnder != null && but != null && but.equals(env.agentUnder.name)) // On the cube
                ) && !env.isPushed; // Not pushed
    }
    
    public void act(Environnement context)
    {
        if(!isSatisfied())
        {
            if(env.agentOver != null)
            { // Not free (cube over)
                context.push(env.agentOver);
            }
            else
            { // Free (no cube over)
                context.moveTo(this, rnd.nextInt(2) == 0 ? env.place1 : env.place2);
            }
        }
    }
}
