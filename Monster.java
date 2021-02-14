/** Monster Class for the Apocalypse game
 *  @author Keith Cooper
 *  @version 15/02/2021
 *  requires Character.Java and ApocDisp.Java to work
 *  Only monster.hurt() and monster.getMonsterType() are called outside of construction
 */

import java.util.Random;

public class Monster {
	private String monsterType;
	private int monsterHealth;
	private int numberOfMonsters;
	private int xpos;
	private int ypos;
	
	public Monster (int dangerIn, int[] position) {
		this.monsterHealth = dangerIn;
		this.xpos = position[0];
		this.ypos = position[1];
		this.monsterType = setMonsterType(dangerIn, position[0], position[1]);
		this.numberOfMonsters = setNumberOfMonsters(dangerIn);
	}
	
	//Getters
	public String getMonsterType() {
		return monsterType;
	}

	public int getNumberOfMonsters() {
		return numberOfMonsters;
	}
	
	public int getMonsterHealth() {
		return monsterHealth;
	}
	
	//Setters
	public void setMonsterHealth(int monsterHealthIn) 
	{
		this.monsterHealth = monsterHealthIn;
	}
	
    public void hurt()
    {
        monsterHealth--;
    }
    
    //Construction methods
	public int setNumberOfMonsters(int monsterHealthIn)
	{
		if (monsterHealthIn == 5)
        {
        	numberOfMonsters = 3;
        }
        else if (monsterHealthIn == 4)
        {
        	numberOfMonsters = 2;
        }
        else numberOfMonsters = 1;
		
		return numberOfMonsters;
	}
	
	public String setMonsterType(int monsterHealthIn, int xposIn, int yposIn) {
		Random rand = new Random();
        int choice = rand.nextInt(10);
        switch (choice)
		{
		case 0: 	monsterType = "T-Rex";
					break;
		case 1:		monsterType = "Mountain Lion";
					break;
		case 2: 	monsterType = "Wild Dogs";
					break;
		case 3:		monsterType = "Barbarian";
					break;
		case 4: 	monsterType = "Evil Robot";
					break;
		case 5:		monsterType = "Velociraptor";
					break;
		default:	if(xpos > 7 && ypos > 7)
					{
						monsterType = "Velociraptor";
					}
					else if(xpos > 7 && ypos < 3)
					{
						monsterType = "Robot";
					}
					else if(xpos < 3 && ypos < 3)
					{
						monsterType = "Mountain Lion";
					}
					else
					{
						monsterType = "Barbarian";
					}
		};
		return monsterType;
	}
	


	
	
	

	
}