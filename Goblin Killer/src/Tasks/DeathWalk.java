package Tasks;

import org.osbot.rs07.script.Script;
import data.Vars;

public class DeathWalk {
	public DeathWalk(Script sI) {
		if (Vars.DeathArea.contains(sI.myPlayer())) {
			sI.log("Lol you died. Cx");
			sI.getWalking().walk(Vars.TrainingArea);
			
		}
	}
}
