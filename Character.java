/** Character Class for the Apocalypse game
 *  @author Keith Cooper
 *  @version 15/02/2021
 *  requires Monster.Java and ApocDisp.Java to work
 */

public class Character
{
    int health;
    int weapon;
    String name;
    int xpos;
    int ypos;
    int potions;
    boolean hasPup;
    int[][] map = 
    {
        {9, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9},
        {5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5},
        {5, 4, 3, 3, 3, 3, 3, 3, 3, 4, 5},
        {5, 4, 3, 2, 2, 2, 2, 2, 3, 4, 5},
        {5, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5},
        {5, 4, 3, 2, 1, 0, 1, 2, 3, 4, 5},
        {5, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5},
        {5, 4, 3, 2, 2, 2, 2, 2, 3, 4, 5},
        {5, 4, 3, 3, 3, 3, 3, 3, 3, 4, 5},
        {5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5},
        {9, 5, 5, 5, 5, 5, 5, 5, 5, 5, 9},
    };

    public Character(String nameIn)
    {
        this.name = nameIn;
        this.health = 10;
        this.weapon = 1;
        this.xpos = 5;
        this.ypos = 5;
        this.hasPup = false;
        this.potions = 0;
    }
    
    //Getters and lookups
    public boolean getHasPup()
    {
    	return hasPup;
    }
    
    public int getHealth()
    {
        return health;
    }
    
    public int getWeapon()
    {
        return weapon;
    }
    
    public String getPosition()
    {
        return "You are currently at position " + xpos + ", " + ypos;
    }
    
    public int[] getLocation()
    {
    	int[] arr =  {xpos, ypos};
        return arr;
    }
    
    public int danger ()
    {
        int danger = map[xpos][ypos];
        return danger;
    }
    
    //Setters and adjustments 
    
    public void addPotion(int num)
    {
        if((potions + num) <= 20)
        {
        	potions = potions + num;
        }
        else
        {
        	potions = 20;
        }
    	
    }
    
    public void improveWeapon(int num)
    {
    	weapon = weapon + num;
    }
    
    public void setHasPup()
    {
    	this.hasPup = true;
    }

    public void hurt()
    {
        health--;
    }

    //Movements - Methods relate to buttons in ApocDisp

    public int[] runAway()
    {
    	if (xpos > 5)
    	{
    		xpos--;
    	}
    	else if (xpos < 5)
    	{
    		xpos++;
    	} 
    	if (ypos > 5)
    	{
    		ypos--;
    	}
    	else if (ypos < 5)
    	{
    		ypos++;
    	} 
    	int[] arr = {xpos,ypos};
        return arr;
    }

    public String moveNorth()
    {
        if (ypos >= 1)
        {
        	ypos = ypos - 1;
        	String text = "You travel north";
        	return text;
        }
        else
        {
        	String text = "You can't travel any more in this direction";
        	return text;
        }
    }

    public String moveSouth()
    {
        if (ypos < 10)
        {
        	ypos = ypos + 1;
        	String text = "You travel south";
        	return text;
        }
        else
        {
        	String text = "You can't travel any more in this direction";
        	return text;
        }
    }

    public String moveWest()
    {
        if (xpos >= 1)
        {
        	xpos = xpos - 1;
        	String text = "You travel west";
        	return text;
        }
        else
        {
        	String text = "You can't travel any more in this direction";
        	return text;
        }
    }

    public String moveEast()
    {
        if (xpos < 10)
        {
        	xpos = xpos + 1;
        	String text = "You travel east";
        	return text;
        }
        else
        {
        	String text = "You can't travel any more in this direction";
        	return text;
        }
    }
    
    //method to return to start in case of player death
    public void returnToStart()
    {
        xpos = 5;
        ypos = 5;
        health = 10;
    }
    
    //method to heal when "heal" button pressed during encounter
    public String Heal()
    {
        if (potions > 0)
        {
            if (health > 3)
            {
                health = 10;
            }
            else 
            {
                health = health + 6;
            }
            potions = potions - 1;
            return ("You have been healed. Your health is now "+ health);
        }
        else 
        {
            return ("You don't have any potions");
        }
        
    }

    /*Method no longer in use
    public String encounter(Monster monster)
    {
        Random rand = new Random();
        int choice = rand.nextInt(10);
        String text = "";
        int monsterHealth = monster.getMonsterHealth();
        String monsterType = monster.getMonsterType();
        
		text += "You encounter a " + monsterType;
		text += "\nIt has " + monsterHealth + " health left";
		text += "\nYou have " + health + " health left\n";
		text += "\nThe " + monsterType + " attacks you";
		int monsterDanger = danger() * rand.nextInt(10);
	    int characterDamage = weapon * (rand.nextInt(9)+1);
	    if (monsterDanger > characterDamage)
        {
        	this.health = this.health-1;
        	text += "\nYou have been hurt!";
        }
        else
        {
        	text += "\nYou inflict a deep cut";
        }
	    return text;
    }*/
}