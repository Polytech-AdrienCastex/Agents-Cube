package cubes;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Stack;

public class Environnement
{
    public Environnement(int maxW, int maxH)
    {
        this.maxW = maxW;
        this.maxH = maxH;
        
        this.piles = new Stack[maxW];
        for(int i = 0; i < piles.length; i++)
            piles[i] = new Stack<>();
        
        this.pushed = new LinkedList<>();
    }
    
    private final int maxH;
    private final int maxW;
    
    public void setAgent(Agent agent, int place)
    {
        piles[place].push(agent);
    }
    
    private final Stack<Agent>[] piles;
    private final Collection<Agent> pushed;
    
    private int getCurrentPlace(Agent agent)
    {
        for(int i = 0; i < piles.length; i++)
            if(piles[i].contains(agent))
                return i;
        return -1;
    }
    
    public void moveTo(Agent agent, int newPlace)
    {
        int currentPlace = getCurrentPlace(agent);
        
        if(piles[currentPlace].peek() != agent)
            throw new IllegalArgumentException();
        
        piles[currentPlace].pop();
        piles[newPlace].push(agent);
        
        pushed.remove(agent);
    }
    
    public void push(Agent agent)
    {
        pushed.add(agent);
    }

    public EnvironnementVariable getEnvironnement(Agent agent)
    {
        int currentPlace = getCurrentPlace(agent);
        Stack<Agent> pile = piles[currentPlace];
        
        boolean isTop = pile.peek() == agent;
        
        int index = pile.indexOf(agent);
        
        return new EnvironnementVariable(
                (isTop ? null : pile.elementAt(index + 1)),
                (index == 0 ? null : pile.elementAt(index - 1)),
                pushed.contains(agent),
                (currentPlace + 1) % piles.length,
                (currentPlace - 1 + piles.length) % piles.length);
    }

    @Override
    public String toString()
    {
        String str = "";
        
        String[][] data = new String[3][4];
        
        for(int x = 0; x < piles.length; x++)
            for(int y = 0; y < maxH; y++)
                data[x][y] = (piles[x].size() > y ? piles[x].elementAt(y).getName() : " ");
        
        for(int y = maxH - 1; y >= 0; y--)
        {
            for(int x = 0; x < maxW; x++)
                str += data[x][y] + " ";
            str += "\n";
        }
        str += "-----\n";
        str += "1 2 3 ";
        
        return str;
    }
}
