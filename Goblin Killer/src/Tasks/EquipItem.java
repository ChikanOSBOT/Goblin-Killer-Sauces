package Tasks;

import org.osbot.rs07.script.Script;

import viking.api.Timing;

public class EquipItem {
	public EquipItem(Script sI) {

		if (sI.getInventory().contains("Bronze sword")) {
			sI.getInventory().interact("Wield","Bronze sword");
			Timing.waitCondition(() -> !sI.getInventory().contains("Bronze sword"),250,500);
			
		}else if (sI.getInventory().contains("Wooden shield")) {
			sI.getInventory().interact("Wield","Wooden shield");
			Timing.waitCondition(() -> !sI.getInventory().contains("Wooden shield"),250,500);
		}
		
	}
}
