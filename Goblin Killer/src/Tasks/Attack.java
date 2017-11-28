package Tasks;

import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.Script;

import data.Vars;
import viking.api.Timing;

public class Attack {
	public Attack(Script sI) {
		NPC goblin = sI.getNpcs().closest("Goblin");
		if (!Vars.TrainingArea.contains(sI.myPlayer())) {
			sI.getWalking().walk(Vars.TrainingArea);
			sI.log("Walking to the training spot!");
			Timing.waitCondition(() -> !sI.myPlayer().isMoving(),2500,4000);
		}else if (Vars.TrainingArea.contains(sI.myPlayer())&&goblin!=null
				&&!sI.myPlayer().isInteracting(goblin)&&!goblin.isUnderAttack()&&sI.getMap().canReach(goblin)) {
			sI.getNpcs().closest("Goblin").interact("Attack");
			sI.log("We're on the attack!");
			Timing.waitCondition(() -> !goblin.isHitBarVisible(),2500,4000);
		}
	}
}
