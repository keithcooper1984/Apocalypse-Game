/** Display Class for the Apocalypse game
 *  @author Keith Cooper
 *  @version 15/02/2021
 *  requires classes Character.Java and Monster.Java to work
 *  requires jpg images titled 	"apocalypse", "monster image", "victory", "farmhouse", 
 *  							"pup", "Forest", "fishing", "camp" & "Facility"
 *  main and start methods are only used to declare Character(player) object
 *  Character(player) object is passed to each method in turn: 
 *  	it is the only object into each method
 *  	it is the only object out of each method
 *  Character(player) object has attributes changed within each method
 *  Victory conditions are to reach (xpos 0 , ypos 0) or (xpos 10 , ypos 0) or (xpos 0 , ypos 10) or (xpos 10 , ypos 10)
 *  	as defined in player.xpos & player.ypos
 *  Methods (scenes) used are: 
 *  	startStage 		- 	beginning of story, only used once
 *  	navigationStage	- 	contains movement buttons. Called repeatedly. Uses checkwin() method 
 *  						to decide if to pass to eventStage or if victory conditions have been met
 *  	eventStage 		-	fighting stage. Called repeatedly. Has buttons for fight, run and heal
 *  	winStage		-	post-fighting stage. Contains rewards and passes to navigation stage
 *  	getPup			-	Special stage to allow for Pup character to be introduced. Can only be called once
 *  						Needs player.hasPup = false && (location [4,4] or Needs location [6,6])
 *  	forest			-	Special stage used only when forest victory conditions met. Leads to game exit
 *  						Needs location [0,0]
 *		facility		-	Special stage used only when facility victory conditions met. Leads to game exit
 *						Needs location [10,0]
 *		camp			-	Special stage used only when camp victory conditions met. Leads to game exit
 *							Needs location [0,10]
 *		beach			-	Special stage used only when beach victory conditions met. Leads to game exit
 *							Needs location [10,10]
 *	Method checkWin is only method that does not create scene. Method uses player location to send player to special stages 
 *  	
 */

import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.util.Collections;
import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;

public class ApocDisp extends Application {
		public static void main(String[] args) {
			launch(args);
	    }

		@Override
	    public void start(Stage primaryStage) {
			Character player = new Character("Dannat");
			startStage(player);
		}
		
		public void startStage(Character player) {
			Stage eventStage = new Stage();
			eventStage.setTitle("Introduction");
	        	        
		    TextArea introduction = new TextArea("\"It used to be that you could just press a button and get food. Literally, just press a button, wait a few minutes and someone would bring it to your house. Now…\" your father let the word hang as he looked at the few potatoes on his plate.\n"
		    		+ "\"What happened?\"\n \"Good times made us soft. Though we had tamed the world; it would do whatever we wanted.” He shrugged, “Guess the world thought different”."
		    		);
	        introduction.setWrapText(true);
	        introduction.setEditable(false);
	        introduction.setMinHeight(500);
	        introduction.setStyle("-fx-text-fill:white;-fx-font-weight:bold;-fx-font-size:15");
	        
	        int[] count = {0};
	        
	        Button cont = new Button("Continue");
	      	cont.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	        	public void handle(ActionEvent event)            	              
               {      
		        count[0] = count[0] +1;
	        	String text = "";
	        	switch (count[0])
	    		{
	    		case 0: 	text +=    			
	    			"“But what happened?” you asked again.\r\n"
	    				+ "“Disease, disaster, war. Probably could have survived one but they all came over and over again. Took out all the heroes, took out all the people who tried to make things right. Then the monsters came.”\r\n"
	    				+ "“The dinosaurs?”\r\n"
	    				+ "“Them too,” he nodded, “but they are just trying to find a way to live. Ain’t their fault they’re confused, a scientist’s cage don’t make a good nursery. No, the monsters are men. You haven’t seen it yet, we’re hidden here, but it’s the men that spread the disease, it’s the men that caused the disasters, it’s the men that started the war and it’s the men that make us have to guard the house at night.”\r\n"
	    				+ "";
	    					break;
	    		case 1:		text +=    			
		    			"Your mother walked into the room, “It isn’t that bad; sure there are some wrong uns but there are good uns too. We just need to wait it out until the good uns win and then we can rebuild. Now, off you go to bed.”\r\n"
		    			+ "You give your parents a hug and go over to your pallet in the corner. Another day of working the fields had left you exhausted and you had barely time to pull the blanket over you before you are asleep.\r\n"
		    			+ "";
	    					break;
	    		case 2: 	text +=    			
		    			"You are roughly shaken awake. \r\n"
		    			+ "“QUICK! WAKE UP!”\r\n"
		    			+ "Your mother pulls you to your feet before you have even opened your eyes and shoves something into your hands.\r\n"
		    			+ "“THE HOUSE IS UNDER ATTACK! DAD IS HOLDING THEM OFF BUT YOU HAVE TO RUN TO THE CAVE!”\r\n"
		    			+ "“I can help,” you protest but your mother shuts you down quickly.\r\n"
		    			+ "“Dad has our only gun. Run to the cave and wait for us there. Don’t stop for anyone and don’t come back!” she instructs as she pushes you out of the back door.\r\n"
		    			+ "You obey and run across the fields. Shouts arise behind you as and you glance back to see some of the barbarians come after you but a shout of “Leave Him” stops them and you make your escape.\r\n"
		    			+ "";
	    					break;
	    		case 3:		text +=    			
		    			"You wait in the cave. The sounds stop after the first few hours. The smell of smoke hangs around for a couple of days.\r\n"
		    			+ "You open the package your mother gave you, it contains food, health potions and her blade. You eat the food cold, scared to start a fire in case it anyone notices it. Despite being behind a bush, halfway up a cliff; a trail of smoke would still be easy to see.\r\n"
		    			+ "Its three days until you go back to the farm house but there is nothing there except bones and ash. You grip your mothers’ blade tightly in your hand. You’ll have to make your own way now.\r\n"
		    			+ "";
	    					break;	    		
	    		default:	navigationStage(player);
            				Stage stage = (Stage) cont.getScene().getWindow();
            				stage.close();	
	    		}
	        	introduction.setText(text);
	    		
	        }});
	        

	        VBox root = new VBox(introduction, cont);
	        root.setAlignment(Pos.CENTER);
	        root.setBackground(new Background(new BackgroundFill(Color.BLACK, 
					CornerRadii.EMPTY, Insets.EMPTY)));

	        
	        eventStage.setScene(new Scene(root, 600, 600));
	        eventStage.show();
	        
	        Region region = ( Region ) introduction.lookup( ".content" );
	        region.setBackground(
	                new Background(
	                        Collections.singletonList(new BackgroundFill(
	                                Color.WHITE, 
	                                new CornerRadii(500), 
	                                new Insets(10))),
	                        Collections.singletonList(new BackgroundImage(
	                                new Image("farmhouse.jpg", 800, 800, false, true),
	                                BackgroundRepeat.NO_REPEAT,
	                                BackgroundRepeat.NO_REPEAT,
	                                BackgroundPosition.CENTER,
	                                BackgroundSize.DEFAULT))));
    }
		
		public void navigationStage(Character player) {
			Stage navigationStage = new Stage();
			navigationStage.setTitle("Navigation");
			
			int[] location = player.getLocation();
			
			String choice = "";
			if (location[0] == 10) {
				choice += "The mountains stop you from going any further east.\n";
			} 
			else if (location[0] == 8 || location[0] == 9)
			{
				choice += "The mountains to the east loom large on the horizon\n";
			}
			else if (location[0] == 1 || location[0] == 2)
			{
				choice += "The bush is getting harder to penetrate as you approach the western forest\n";
			}
			else if (location[0] == 0)
			{
				choice += "The bush is too thick to let you go any further west\n";
			}
			if (location[1] == 10) {
				choice += "The radiation of the wasteland is too strong for you to go any further south.\n";
			} 
			else if (location[1] == 8 || location[1] == 9)
			{
				choice += "The land is the south shows the sign of radiation poisoning as you approach the wasteland\n";
			}
			else if (location[1] == 1 || location[1] == 2)
			{
				choice += "The mountains to the north loom large on the horizon\n";
			}
			else if (location[1] == 0)
			{
				choice += "The mountains stop you from going any further north.\n";
			}
			if (location[0] == 5 && location[1] == 5)
			{
				choice += "You are safe within your cave. You take the opportunity to restock on supplies\n";
				player.addPotion(10);
			}
			else if ((location[0] == 4 || location[0] == 5 || location[0] == 6) && (location[1] == 4 || location[1] == 5 || location[1] == 6))
			{
				choice += "You are close to your cave.\n";
			}
			choice += "You choose the direction in which you wish to travel";			
			
	        Text position = new Text(player.getPosition() + "\n" + choice);
	        position.setFill(Color.WHITE);
	        position.setWrappingWidth(500);
	        position.setTextAlignment(TextAlignment.CENTER);
	        position.setStyle("-fx-text-fill:white;-fx-font-weight:bold;-fx-font-size:15");
	        Text directions = new Text("Direction");
	        directions.setWrappingWidth(50);
	        directions.setFill(Color.WHITE);
			
			Button north = new Button("North");
	       	north.setOnAction(e -> 
	        {           	
	        	position.setText(player.moveNorth()); 
	        	if (!checkWin(player))
	        	{
	        		eventStage(player);
	            	Stage stage = (Stage) north.getScene().getWindow();
	                stage.close();
	        	}
	        });
	        
	        Button south = new Button("South");
	      	south.setOnAction(e -> 
	        {
	        	position.setText(player.moveSouth());
	        	if (!checkWin(player))
	        	{
	        		eventStage(player);
	            	Stage stage = (Stage) south.getScene().getWindow();
	                stage.close();
	        	}
	        });
	        
	        Button east = new Button("East");
	      	east.setOnAction(e -> 
	        {	
	        	position.setText(player.moveEast()); 
	        	if (!checkWin(player))
	        	{
	        		eventStage(player);
	            	Stage stage = (Stage) east.getScene().getWindow();
	                stage.close();
	        	}
	        });
	        
	        Button west = new Button("West");
	      	west.setOnAction(e -> 
	        {
	        	position.setText(player.moveWest());  
	        	if (!checkWin(player))
	        	{
	        		eventStage(player);
	            	Stage stage = (Stage) west.getScene().getWindow();
	                stage.close();
	        	}
	        });
	        
	        Insets border = new Insets(5, 5, 5, 5);
	        
	        BorderPane borderPane = new BorderPane(directions, north, east, south, west); 
	        BorderPane.setAlignment(borderPane, Pos.CENTER);
	        BorderPane.setMargin(north, border);
	        BorderPane.setAlignment(north, Pos.CENTER);
	        BorderPane.setMargin(south, border);
	        BorderPane.setAlignment(south, Pos.CENTER);
	        BorderPane.setMargin(east, border);
	        BorderPane.setMargin(west, border);
	        
	        
	        
	        HBox top = new HBox(50);
	        top.setMinHeight(50);
	        HBox middle = new HBox(50);
	        middle.getChildren().add(position);
	        middle.setAlignment(Pos.CENTER);
	        HBox bottom = new HBox(50);
	        bottom.getChildren().add(borderPane);
	        bottom.setAlignment(Pos.BASELINE_LEFT);
			
	        BorderPane root = new BorderPane();
	        root.setTop(top);
	        root.setCenter(middle);
	        root.setBottom(bottom);
	        root.setBackground(
	                new Background(
	                        Collections.singletonList(new BackgroundFill(
	                                Color.WHITE, 
	                                new CornerRadii(500), 
	                                new Insets(10))),
	                        Collections.singletonList(new BackgroundImage(
	                                new Image("apocalypse.jpg", 800, 800, false, true),
	                                BackgroundRepeat.NO_REPEAT,
	                                BackgroundRepeat.NO_REPEAT,
	                                BackgroundPosition.CENTER,
	                                BackgroundSize.DEFAULT))));
	
	        navigationStage.setScene(new Scene(root, 600, 600));
	        navigationStage.show();
    }
		
		public void eventStage(Character player) {
			Stage eventStage = new Stage();
			eventStage.setTitle("Event");
	        	        
	        
			Random rand = new Random();
			int danger = player.danger();
	        Monster monster = new Monster(danger, player.getLocation());
			
	        String desc = "You see a " + monster.getMonsterType() + " in front of you\n";
	        if (player.getHasPup()) {
	        	if (danger <= 2)
	        	{
	        		desc += "Pup seems eager to fight them\n";
	        	}
	        	else if (danger == 3 || danger == 4) 
	        	{
	        		desc += "Pup seems nervous\n";
	        	}
	        	else if (danger > 4) 
	        	{
	        		desc += "Pup seems scared\n";
	        	}
	        	
	        }
		    Text encounter = new Text(desc);
	        encounter.setFill(Color.WHITE);
	        
	        HBox top = new HBox(50);
	        top.setMinHeight(50);
	        top.getChildren().add(encounter);
	        
	        Button fight = new Button("Fight");
	        fight.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	        	public void handle(ActionEvent event)            	              
                {      	
	    	    top.getChildren().clear();
			    String description = "";
			    int monsterDanger = danger * rand.nextInt(10);
			    int characterDamage = player.weapon * (rand.nextInt(9)+1);
	        	if (monsterDanger > characterDamage)
	            {
	            	player.hurt();
	            	description += "\nYou have been hurt!";
	            	description += "\nYour health is currently " + player.getHealth();
	            	if (player.getHealth()<= 0) {
	            		description += "\nYOU DIED";
	            		player.returnToStart();
	            		navigationStage(player);
	                	Stage stage = (Stage) fight.getScene().getWindow();
	                    stage.close();
	            	}
	            }
	            else
	            {
	            	description += "\nYou inflict a deep cut";
	            	monster.hurt();
	            	description += "\nMonster health is currently " + monster.getMonsterHealth();
	            	if (monster.getMonsterHealth()<= 0) {
	            		winStage(player);
	                	Stage stage = (Stage) fight.getScene().getWindow();
	                    stage.close();
	            	}
	            }  
	        	Text fightText = new Text(description);
	        	fightText.setFill(Color.WHITE);
	        	top.getChildren().add(fightText);
	        }});
	        
	        Button run = new Button("Run");
	      	run.setOnAction(e -> 
	        {
	        	player.runAway();
            	navigationStage(player);
            	Stage stage = (Stage) run.getScene().getWindow();
                stage.close();
	        });
	        
	        Button heal = new Button("Heal");
	      	heal.setOnAction(e -> 
	        {	
	        	top.getChildren().clear();
			    String description = "";
	        	description = player.Heal();	
	        	Text healText = new Text(description);
	        	healText.setFill(Color.WHITE);
	        	top.getChildren().add(healText);
	        });

	        HBox middle = new HBox(50);
	        middle.getChildren().addAll(fight, run, heal);
	        middle.setAlignment(Pos.CENTER);
			
	        VBox root = new VBox(top, middle);
	        root.setBackground(
	                new Background(
	                        Collections.singletonList(new BackgroundFill(
	                                Color.WHITE, 
	                                new CornerRadii(500), 
	                                new Insets(10))),
	                        Collections.singletonList(new BackgroundImage(
	                                new Image("monster image.jpg", 800, 800, false, true),
	                                BackgroundRepeat.NO_REPEAT,
	                                BackgroundRepeat.NO_REPEAT,
	                                BackgroundPosition.CENTER,
	                                BackgroundSize.DEFAULT))));
	        root.setStyle("-fx-text-fill:white;-fx-font-weight:bold;-fx-font-size:15");
	        
	        eventStage.setScene(new Scene(root, 600, 600));
	        eventStage.show();
		}
		
		public void winStage(Character player) {
			Stage winStage = new Stage();
			winStage.setTitle("You Win");
	        	        
	        
			Random rand = new Random();
		    	        
	        String str = "You win the fight.\n\n After patching up your cuts you check to see if any supplies have been dropped\n";
	        int choice = rand.nextInt(5);
	        
	        switch (choice)
	        {
	        case 0:	str += 	"You find a potion and add it to your supplies\n";
	        				player.addPotion(1);
	        				break;
	        case 1:	str +=	"You find a sharpening stone. You use it on your blade\n";
	        				player.improveWeapon(1);
	        				break;
	        case 2: str +=	"You find an old newspaper. You read it to remind yourself that you still can.\r\n"
	        		+ "“Our mission is to save the children of humanity and I believe that we will. Of course, it will take a long time for the world to recover but the world itself has been through many events such as this and always returns to balance. However, this takes thousands of years so we are working on ways to speed up the process.” Says Dr Clarke of the North-West Facility, flashing his trademark grin. “Life must be preserved and the achievements of humans must be passed on. When the dinosaurs initially went extinct, they had already created the birds that continued their legacy and now we have been able to recreate some of the dinosaurs from them,” he frowns, “I don’t mean to suggest that I approve of the reintroduction of the velociraptors but it serves to show the importance of continuing the chain.”\r\n"
	        		+ "You fold the paper and put it into your pocket. At least it will help start a fire.\r\n"
	        		+ "";
	        				break;
	        case 3:	str += 	"You find a potion and add it to your supplies\n";
							player.addPotion(1);
							break;
	        default:	str += "You find nothing of use\n";
	        }
	        
	        Text encounter = new Text(str);
	        encounter.setFill(Color.WHITE);
	        encounter.setWrappingWidth(500);
	        encounter.setTextAlignment(TextAlignment.CENTER);
	        
	        HBox top = new HBox(50);
	        top.setMinHeight(50);
	        top.getChildren().add(encounter);
	               
	        Button cont = new Button("Continue");
	      	cont.setOnAction(e -> 
	      	{
            	navigationStage(player);
            	Stage stage = (Stage) cont.getScene().getWindow();
                stage.close();
	        });

	        VBox root = new VBox(top, cont);
	        root.setAlignment(Pos.CENTER);
	        root.setBackground(
	                new Background(
	                        Collections.singletonList(new BackgroundFill(
	                                Color.WHITE, 
	                                new CornerRadii(500), 
	                                new Insets(10))),
	                        Collections.singletonList(new BackgroundImage(
	                                new Image("victory.jpg", 800, 800, false, true),
	                                BackgroundRepeat.NO_REPEAT,
	                                BackgroundRepeat.NO_REPEAT,
	                                BackgroundPosition.CENTER,
	                                BackgroundSize.DEFAULT))));
	        root.setStyle("-fx-text-fill:white;-fx-font-weight:bold;-fx-font-size:15");
	        
	        winStage.setScene(new Scene(root, 600, 600));
	        winStage.show();
    }
		
		

	public boolean checkWin(Character player)
	{
		int[] location = player.getLocation();
		if ((location[0] == 0 || location [0] == 10) && (location[1] == 0 || location [1] == 10))
			{
			if (location[0] == 0 && location[1] == 0)
			{
				forest();
			}
			else if (location[0] == 10 && location[1] == 0)
			{
				facility();
			}
			else if (location[0] == 0 && location[1] == 10)
			{
				camp();
			}
			else if (location[0] == 10 && location[1] == 10)
			{
				beach();
			}
			return true;
			}
		else if ((player.getHasPup() == false) &&((location[0] == 4 && location[1] == 4) || (location[0] == 6 && location[1] == 6)))
		{
			getPup(player);
			return true;
		}
		
		else
		{
			return false;
		}
	}
		
	public Text event(int xposIn, int yposIn)
	{
		Text position = new Text("You are currently at position " + xposIn + "," + yposIn);
		return position;
	}
	
	public void getPup(Character player) {
		Stage eventStage = new Stage();
		eventStage.setTitle("Pup");
        	        
	    TextArea introduction = new TextArea("You see a group of wild dogs but for once they don’t chase you. They are interested in something else and you creep closer to see what.\r\n"
	    		+ "Leaning out from behind a tree, you see that they are surrounding a young dog that is guarding a body. He is growling desperately but soon he will have them on every side and he only has one pair of teeth.\r\n"
	    		+ "");
        introduction.setWrapText(true);
        introduction.setEditable(false);
        introduction.setMinHeight(500);
        introduction.setStyle("-fx-text-fill:white;-fx-font-weight:bold;-fx-font-size:15");
        
        int[] count = {0};
        
        Button cont = new Button("Continue");
      	cont.setOnAction(new EventHandler<ActionEvent>() {
        	 
        	public void handle(ActionEvent event)            	              
           {      
	        count[0] = count[0] +1;
        	String text = "";
        	switch (count[0])
    		{
    		case 0: 	text +=    			
    			"A sense of righteousness comes over you and before you realise what you are doing you run at the dogs, shouting and waving your blade.\r\n"
    			+ "They scatter and you are left alone with the young guard. “It’s Ok,” you reassure him “I am not here to hurt you or her.” You point to the body, “Was she your friend?”\r\n"
    			+ "His bristles start to go down and his growl subsides.\r\n"
    			+ "";
    					break;
    		case 1:		text +=    			
	    			"“You can’t guard her for ever, you already look pretty skinny and they will be back.” You see a pile of rocks nearby and bring one over placing it by her head. “If we cover her with these then she can rest in peace and you will be free to hunt.” You don’t know if he understood but he lets you continue so you do.\r\n"
	    			+ "“I know what its like,” you tell him as you carry more rocks, “I had to bury my parents. There wasn’t much left but it was still hard. They weren’t heavy like these rocks but they were heavy in a different way.”\r\n"
	    			+ "";
    					break;
    		case 2: 	text +=    			
	    			"You stopped for a moment, “I could have used a friend that day, I’m glad I could be here for you.” You smiled at him and put the last rock in place. “All done, she will sleep well now.” You picked up your bag and waved, “look after yourself pup, it’s a rough world out there.”\r\n"
	    			+ "You set off down the road but you find him walking next to you. “You are free now pup, you don’t have to hang out with me.” \r\n"
	    			+ "He follows you anyway.\r\n"
	    			+ "“Maybe, I could still use that friend.” You smile.\r\n"
	    			+ "";
    					break;
    		default:	navigationStage(player);
    					Stage stage = (Stage) cont.getScene().getWindow();
        				stage.close();	
    		}
        	player.setHasPup();
        	introduction.setText(text);
    		
        }});
        

        VBox root = new VBox(introduction, cont);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, 
				CornerRadii.EMPTY, Insets.EMPTY)));

        
        eventStage.setScene(new Scene(root, 600, 600));
        eventStage.show();
        
        Region region = ( Region ) introduction.lookup( ".content" );
        region.setBackground(
                new Background(
                        Collections.singletonList(new BackgroundFill(
                                Color.WHITE, 
                                new CornerRadii(500), 
                                new Insets(10))),
                        Collections.singletonList(new BackgroundImage(
                                new Image("pup.jpg", 800, 800, false, true),
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.CENTER,
                                BackgroundSize.DEFAULT))));
}
	
	public void forest() {
		Stage eventStage = new Stage();
		eventStage.setTitle("Forrest Ending");
        	        
	    TextArea introduction = new TextArea("You find a cave deep in the forest and set up base there. The game is plentiful here, the position defendable so you think about staying here for good.\r\n"
	    		+ "Pup seems to be restless though. Each day he spends longer and longer away from the cave. You try not to worry though as he isn’t a young pup anymore. Time spent with you fighting monsters and hunting for food has left him a mass of muscle and even the velociraptors flee at the sight of him.\r\n"
	    		+ "");
        introduction.setWrapText(true);
        introduction.setEditable(false);
        introduction.setMinHeight(500);
        introduction.setStyle("-fx-text-fill:white;-fx-font-weight:bold;-fx-font-size:15");
        
        int[] count = {0};
        
        Button cont = new Button("Continue");
      	cont.setOnAction(new EventHandler<ActionEvent>() {
        	 
        	public void handle(ActionEvent event)            	              
           {      
	        count[0] = count[0] +1;
        	String text = "";
        	switch (count[0])
    		{
    		case 0: 	text +=    			
    			"One day he comes up to you in the cave and starts to bark, confused you grab your staff and walk up to him but he walks off and barks again. You walk over to him again and he sets off, stopping to look over his shoulder every so often to check that you are still following. \r\n"
    			+ "He leads you into a clearing where you see a pack of wild dogs. You clutch your staff tighter, expecting a fight but he continues and walks into the middle of them. The biggest of them comes to meet him, growling but Pup doesn’t back down. \r\n"
    			+ "Suddenly a fight breaks out between the two of them as the rest of the pack watches nervously. You step closer and realise that he is fighting to take the pack!\r\n"
    			+ "";
    					break;
    		case 1:		text +=    			
	    			"The old leader puts up a good fight and even manages to draw blood but it is clear that Pup is the stronger. A few of the others move as if to join in but you slam your staff into the ground and they think better of the idea. Then it is all over; the old dog lies dead on the floor, Pup stands over him, looking at each of his subjects in turn to see if they wish to challenge him.\r\n"
	    			+ "In turn they walk over to him, wagging tails and meek expressions showing that they accept him as their new leader. They are less sure what to make of you but they keep their distance and don’t try to attack.\r\n"
	    			+ "";
    					break;
    		case 2: 	text +=    			
	    			"Pup leads them back to the cave and settles them there next to you. Within the next few weeks, they come to accept you. Within a few months, they cuddle up to you in their sleep and follow your directions when you hunt together.  There is no doubt that Pup is the leader but you are like a respected elder, a Shaman and you find your place. Even, eventually helping to train the puppies in their hunting.\r\n"
	    			+ "Pup leads the pack for many years but grows old and is succeeded by one of his sons, who in turn is succeeded by one of his. The pack remains strong and you remain with them throughout. A hundred thousand years after dogs joined the tribes of man and became tame, you join the tribe of dogs and become wild. It was not the future you dreamed of as a child but you are happy in your family.\r\n"
	    			+ "";
    					break;
    		case 3:		text += "CONGRATULATIONS.\nYou have completed the forest ending.\n";
    					break;	    		
    		default:	Platform.exit();
						System.exit(0);	
    		}
        	introduction.setText(text);
    		
        }});
        

        VBox root = new VBox(introduction, cont);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, 
				CornerRadii.EMPTY, Insets.EMPTY)));

        
        eventStage.setScene(new Scene(root, 600, 600));
        eventStage.show();
        
        Region region = ( Region ) introduction.lookup( ".content" );
        region.setBackground(
                new Background(
                        Collections.singletonList(new BackgroundFill(
                                Color.WHITE, 
                                new CornerRadii(500), 
                                new Insets(10))),
                        Collections.singletonList(new BackgroundImage(
                                new Image("Forest.jpg", 800, 800, false, true),
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.CENTER,
                                BackgroundSize.DEFAULT))));
}
	
	public void beach() {
		Stage eventStage = new Stage();
		eventStage.setTitle("Beach Ending");
        	        
	    TextArea introduction = new TextArea("The smell of the sea arrives first, awakening memories that are not your own. Pup seems to be excited by it as well and by the time you see the sea you are both being rather silly. Luckily the beach is deserted.\r\n"
	    		+ "You spend some time playing on the sand, making castles and burying pup but as night falls you seek out shelter. A few miles down the road is an abandoned village, and you smash your way into the ruins of a house. The next day you explore some more and find some old papers in one of the houses including a map and a diary. \r\n"
	    		+ "");
        introduction.setWrapText(true);
        introduction.setEditable(false);
        introduction.setMinHeight(500);
        introduction.setStyle("-fx-text-fill:white;-fx-font-weight:bold;-fx-font-size:15");
        
        int[] count = {0};
        
        Button cont = new Button("Continue");
      	cont.setOnAction(new EventHandler<ActionEvent>() {
        	 
        	public void handle(ActionEvent event)            	              
           {      
	        count[0] = count[0] +1;
        	String text = "";
        	switch (count[0])
    		{
    		case 0: 	text +=    			
    			"The diary belonged to an old captain and you spend a couple of hours reading it, learning about the islands off the coast. You study the map as you do so, identifying each one. \r\n"
    			+ "The last page of the diary gives the location of where he has hidden his boat so in the morning you go and check it out. To your surprise it is still there and in one piece.\r\n"
    			+ "“Fancy going on an adventure?” you ask Pup with a smile.\r\n"
    			+ "";
    					break;
    		case 1:		text +=    			
	    			"TO BE CONTINUED IN APOCALYPSE 2 – PUPS OF THE HIGH SEAS";
    					break;
    		case 2: 	text +=  "CONGRATULATIONS.\nYou have completed the beach ending.\n";
    					break;	    		
    		default:	Platform.exit();
						System.exit(0);	
    		}
        	introduction.setText(text);
    		
        }});
        

        VBox root = new VBox(introduction, cont);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, 
				CornerRadii.EMPTY, Insets.EMPTY)));

        
        eventStage.setScene(new Scene(root, 600, 600));
        eventStage.show();
        
        Region region = ( Region ) introduction.lookup( ".content" );
        region.setBackground(
                new Background(
                        Collections.singletonList(new BackgroundFill(
                                Color.WHITE, 
                                new CornerRadii(500), 
                                new Insets(10))),
                        Collections.singletonList(new BackgroundImage(
                                new Image("fishing.jpg", 800, 800, false, true),
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.CENTER,
                                BackgroundSize.DEFAULT))));
}
	
	public void facility() {
		Stage eventStage = new Stage();
		eventStage.setTitle("Facility Ending");
        	        
	    TextArea introduction = new TextArea("“You can lower your weapon; I mean you no harm.”\r\n"
	    		+ "You recognise Dr Clarke from his picture and lower you weapon a little.\r\n"
	    		+ "“I know that your intentions are honourable, the scans the robots have completed of you tell me that. But I do not know why it is that you have come.”\r\n"
	    		+ "“I read about you… it said you want to save the children….”\r\n"
	    		+ "His smile remained in place but a look of sadness fell over him. “The children of humanity, not the children of humans.” He appraised you for a moment and seemed to cheer up. “Please, come with me.”\r\n"
	    		+ "");
        introduction.setWrapText(true);
        introduction.setEditable(false);
        introduction.setMinHeight(500);
        introduction.setStyle("-fx-text-fill:white;-fx-font-weight:bold;-fx-font-size:15");
        
        int[] count = {0};
        
        Button cont = new Button("Continue");
      	cont.setOnAction(new EventHandler<ActionEvent>() {
        	 
        	public void handle(ActionEvent event)            	              
           {      
	        count[0] = count[0] +1;
        	String text = "";
        	switch (count[0])
    		{
    		case 0: 	text +=    			
    			"He led you into the facility and down to a large canteen. “Help yourself” he offered before sitting across the table from you. \r\n"
    			+ "“We started here with a mission; to save humanity. It was before I was born but when I came here, I accepted that mission. Yet even then it was obvious that it was not possible.”\r\n"
    			+ "“Why not?”\r\n"
    			+ "“We are too far gone. The radiation poisons us so that children are almost unheard of, the monsters eat us so that only a few remain and then we spend our time killing each other. “ He shrugged, “But then what is it to be human? Do you know?”\r\n"
    			+ "“Er…. To be like us?”\r\n"
    			+ "";
    					break;
    		case 1:		text +=    			
	    			"“Which one of us? Certainly, you are not the same as me, then again neither is Micheal over there or indeed anyone else. If you mean a creature with two arms and two legs then that would include a monkey so it cannot be that. If you mean someone who has intelligence and morals then that would not include the barbarians.“\r\n"
	    			+ "He smiled and continued. “I came to believe that humanity meant those who acted humanely and the children of humanity were their creations. If we humans could not survive then we could at least ensure the survival of our creations, of our children. We could preserve our knowledge and pass it down to them. We could equip them for survival in this new world and then retire into the night in the belief that we have given them every chance to surpass us.”\r\n"
	    			+ "You looked at him blankly. “Creations? You can’t mean the dinosaurs?”. He shook his head. “But then that only leaves…”\r\n"
	    			+ "“Yes” he encouraged.\r\n"
	    			+ "“The robots?”\r\n"
	    			+ "";
    					break;
    		case 2: 	text +=    			
	    			"“Exactly. They can withstand the radiation. They can stand up to the barbarians and wild animals. They can live in harmony with nature. What they cannot do is live so before we become extinct we must teach them how.”\r\n"
	    			+ "Dr Clarke tidied your now empty plate and led you around the facility, showing you Dr Mantel’s work on treating radiation as a food source for the robots and Dr Sunil’s work on self-replication with variation. \r\n"
	    			+ "Over the days that followed you found yourself running errands around the facility, helping out more and more as you began to understand.\r\n"
	    			+ "";
    					break;
    		case 3:		text += "Now, thirty years later, the facility is yours and you watch proudly from the window as you watch the latest batch leave. Most of them will never return, though occasionally one may seek help to repair a bullet hole or bite mark. Each of them has an encyclopaedia of human knowledge stored in their memory banks, each of them consumes the radiation that pollutes this world leaving it better for the other creatures, each of them distributes health potions to the good people they meet and each of them has the weaponry required to stop that other sort of people. \r\n"
    				+ "You turn away from the window and call to your young apprentice, she has only been here a week but is starting to be a help to you all. “Can you ask Dr Sunil to join me, I have some ideas on how we can improve the next generation and I would value her wisdom.”\r\n"
    				+ "";
    					break;
    		case 4: 	text +=  "CONGRATULATIONS.\nYou have completed the facility ending.\n";
						break;
    		default:	Platform.exit();
						System.exit(0);
    		}
        	introduction.setText(text);
    		
        }});
        

        VBox root = new VBox(introduction, cont);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, 
				CornerRadii.EMPTY, Insets.EMPTY)));

        
        eventStage.setScene(new Scene(root, 600, 600));
        eventStage.show();
        
        Region region = ( Region ) introduction.lookup( ".content" );
        region.setBackground(
                new Background(
                        Collections.singletonList(new BackgroundFill(
                                Color.WHITE, 
                                new CornerRadii(500), 
                                new Insets(10))),
                        Collections.singletonList(new BackgroundImage(
                                new Image("Facility.jpg", 800, 800, false, true),
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.CENTER,
                                BackgroundSize.DEFAULT))));
}
	
	public void camp() {
		Stage eventStage = new Stage();
		eventStage.setTitle("Barbarian Ending");
        	        
	    TextArea introduction = new TextArea("They caught you as you slept. Pup fought to save you but the largest of them, the leader, slashed him with his sword and Pup fell. You were dragged back t the camp in chains.\r\n"
	    		+ "In the middle of the camp there was a ring and a platform with a chair. The leader settled himself onto the chair and pointed at the ring. “Tha blood is our fun. Today you are our fun.”\r\n"
	    		+ "You spit in his face and swear.\r\n"
	    		+ "“Tha fool wants ta fight me.” He shouts to the others, smiling. “Tonight tha fun is mine!”\r\n"
	    		+ "");
        introduction.setWrapText(true);
        introduction.setEditable(false);
        introduction.setMinHeight(500);
        introduction.setStyle("-fx-text-fill:white;-fx-font-weight:bold;-fx-font-size:15");
        
        int[] count = {0};
        
        Button cont = new Button("Continue");
      	cont.setOnAction(new EventHandler<ActionEvent>() {
        	 
        	public void handle(ActionEvent event)            	              
           {      
	        count[0] = count[0] +1;
        	String text = "";
        	switch (count[0])
    		{
    		case 0: 	text +=    			
    			"Your chains are removed and you are thrown in the ring. The leader follows, drawing his sword. You look around, trying to find a weapon but there only bones. \r\n"
    			+ "He comes to you swinging his blade and you duck, letting it sweep harmlessly over your head. With astonishing speed he changes his stroke and cuts down at you, making you have to dive out of the way. You grab a rib as you get up from the floor and he smiles even more. “Tha fool wants to fight ME!”\r\n"
    			+ "He swings twice more but you dodge them both. On the third swing you surprise him by stepping into his blow and stabbing the rib into his neck. “That was for Pup” you tell him as the life leaves his eyes.\r\n"
    			+ "";
    					break;
    		case 1:		text +=    			
	    			"Shouts from around the ring bring you back to your senses and you grab the sword from the floor as two more enter. They charge up to you but you are awake and armed now so they don’t stand a chance. You block the first and send a powerful kick into his stomach then spin and slice the head clean off the second. You kick the head over to one you had let live. “Do I need to kill you all?”\r\n"
	    			+ "No more try to attack you as you march over to the chair on the platform and sit on it. “Bring me something to eat” you command.\r\n"
	    			+ "A few of them approach you cautiously as you eat, “What do you want of us?” they ask.\r\n"
	    			+ "";
    					break;
    		case 2: 	text +=    			
	    			"You examine them closely. What do you want? What can they do? A memory resurfaces.\r\n"
	    			+ "“We thought we had tamed the word but it fought back. We weren’t strong enough. This time we will be. You’re a rabble; fighting farmers and scaring cattle. I am going to make you into an army; stronger than steel, fiercer than fire. We are going to burn away all the weakness and we are going to take this world back!”\r\n"
	    			+ "";
    					break;
    		case 3:		text += "CONGRATULATIONS.\nYou have completed the barbarian ending.\n";
    					break;	    		
    		default:	Platform.exit();
            			System.exit(0);
    		}
        	introduction.setText(text);
    		
        }});
        

        VBox root = new VBox(introduction, cont);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, 
				CornerRadii.EMPTY, Insets.EMPTY)));

        
        eventStage.setScene(new Scene(root, 600, 600));
        eventStage.show();
        
        Region region = ( Region ) introduction.lookup( ".content" );
        region.setBackground(
                new Background(
                        Collections.singletonList(new BackgroundFill(
                                Color.WHITE, 
                                new CornerRadii(500), 
                                new Insets(10))),
                        Collections.singletonList(new BackgroundImage(
                                new Image("camp.jpg", 800, 800, false, true),
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.CENTER,
                                BackgroundSize.DEFAULT))));
	}
	
}


	
