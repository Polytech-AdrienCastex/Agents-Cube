package cubes;

import java.util.Random;
import java.util.stream.IntStream;
import javafx.util.Pair;

public class Cubes
{
    public static void main(String[] args)
    {
        // Agents list
        Agent[] agents = new Agent[]
        {
            new Agent("A", "B"),
            new Agent("B", "C"),
            new Agent("C", "D"),
            new Agent("D", null)
        };
        
        Environnement env = new Environnement(3, agents.length);
        
        // Randomize the place of each Agent on the position 0
        Random rnd = new Random();
        IntStream.range(0, agents.length)
                .mapToObj(i -> new Pair<Integer, Agent>(rnd.nextInt(), agents[i]))
                .sorted((o1, o2) -> Integer.compare(o1.getKey(), o2.getKey()))
                .forEachOrdered(p -> env.setAgent(p.getValue(), 0));
        
        // Execution loop
        System.out.println("*********** INITIAL ************");
        System.out.println(env);
        for(int i = 0; i < 50; i++)
        {
            for(Agent a : agents)
            {
                a.getEnv(env);
                a.act(env);
            }
            
            System.out.println("*********** " + (i + 1) + " ************");
            System.out.println(env);
        }
    }
    
}
