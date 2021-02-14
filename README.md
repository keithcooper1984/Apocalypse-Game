# Apocalypse-Game
Text based game set in Apocalypse - made as present for my son

Game requires jpg images titled 	"apocalypse", "monster image", "victory", "farmhouse", "pup", "Forest", "fishing", "camp" & "Facility"
Running without these images in place will result in error code
I have not included these as my license was for private use only but any jpg images with these titles will work

Class Character
  /** Character Class for the Apocalypse game
 *  @author Keith Cooper
 *  @version 15/02/2021
 *  requires Monster.Java and ApocDisp.Java to work
 */
 
Class Monster
  /** Monster Class for the Apocalypse game
 *  @author Keith Cooper
 *  @version 15/02/2021
 *  requires Character.Java and ApocDisp.Java to work
 *  Only monster.hurt() and monster.getMonsterType() are called outside of construction
 */

Class ApocDisp
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
