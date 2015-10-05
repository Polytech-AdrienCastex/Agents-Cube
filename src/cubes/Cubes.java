package cubes;

/**
 *
 * @author p1002239
 */
public class Cubes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Environnement env = new Environnement(3, 4);
        
        Agent[] agents = new Agent[4];
        agents[0] = new Agent("A", "B");
        agents[1] = new Agent("B", "C");
        agents[2] = new Agent("C", "D");
        agents[3] = new Agent("D", null);
        
        env.setAgent(agents[2], 0);
        env.setAgent(agents[1], 0);
        env.setAgent(agents[0], 0);
        env.setAgent(agents[3], 0);
        
        System.out.println("*********** 0 ************");
        System.out.println(env);
        for(int i = 0; i < 100; i++)
        {
            for(Agent a : agents)
            {
                a.getEnv(env);
                a.act(env);
            }
            
            System.out.println("*********** " + i + " ************");
            System.out.println(env);
        }
    }
    
}
