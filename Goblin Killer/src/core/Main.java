package core;

import java.awt.Graphics2D;
import java.util.concurrent.TimeUnit;

import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import Tasks.Attack;
import Tasks.DeathWalk;
import Tasks.EquipItem;
import data.State;
import data.Vars;
import viking.api.Timing;


@ScriptManifest(author = "Chikan", name = "Chikan's Epic Goblin killer!!!!", info = "Kills gobins.. duh", version = 1.0, logo = "")

public final class Main extends Script  {
	private long timeBegan;
	private long timeRan;
	public void onStart() {
		log("Starting Goblin Killer hack.exe...");
		log("Please wait...");
		//logs the current xp on start
		sBeginningXp = skills.getExperience(Skill.STRENGTH);
		aBeginningXp = skills.getExperience(Skill.ATTACK);
		dBeginningXp = skills.getExperience(Skill.DEFENCE);
		//paint time variable
		timeBegan = System.currentTimeMillis();
		
	}
	
	private State getState() throws InterruptedException {

		NPC goblin = getNpcs().closestThatContains("Goblin");
		if (getInventory().contains("Bronze sword")) {
			return State.EQUIPITEM;
		}else if (!myPlayer().isInteracting(goblin)&&goblin!=null) {
			return State.ATTACK;
		}else if (myPlayer().isUnderAttack()) {
			return State.IDLE;
		}else if (Vars.DeathArea.contains(myPlayer())) {
			return State.DEATHWALK;
		}
		return State.IDLE;
		
	}


	public final int onLoop() throws InterruptedException {
		switch (getState()) 
	       {
		case ATTACK:
			new Attack(this);
			break;
		case DEATHWALK:
			new DeathWalk(this);
			break;
		case EQUIPITEM:
			new EquipItem(this);
		case IDLE:
			break;
		
	       }
		return random(250,400);
	}
	public void onPaint(Graphics2D g)
    {
      Graphics2D gr = g;
      //time variable
      timeRan = System.currentTimeMillis() - this.timeBegan;
      //XP variable
      sLvl = skills.getExperience(Skill.STRENGTH);
      aLvl = skills.getExperience(Skill.ATTACK);
      dLvl = skills.getExperience(Skill.STRENGTH);
      sXpGained = sLvl - sBeginningXp;
      aXpGained = aLvl - sBeginningXp;
      dXpGained = dLvl - sBeginningXp;
      g.drawString("Chikan's Auto Goblins", 25, 280);
      //XP gained onscreen paint
      g.drawString("" + sXpGained, 125, 325);
      g.drawString("Strength Exp: ", 25, 325);
      g.drawString("" + aXpGained, 125, 340);
      g.drawString("Attack Exp: ", 25, 340);
      g.drawString("" + dXpGained, 125, 355);
      g.drawString("Defense Exp: ", 25, 355);
      //time ran paint
      g.drawString(ft(timeRan), 125, 300);
      g.drawString("Time: ", 25, 300);
    }
	//paint stuff
    private String ft(long duration)
    {
            String res = "";
            long days = TimeUnit.MILLISECONDS.toDays(duration);
            long hours = TimeUnit.MILLISECONDS.toHours(duration) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
            long minutes = TimeUnit.MILLISECONDS.toMinutes(duration) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration));
            long seconds = TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration));
            if (days == 0)
            {
                    res = (hours + ":" + minutes + ":" + seconds);
            }
            else
            {
                    res = (days + ":" + hours + ":" + minutes + ":" + seconds);
            }
            return res;
    }

	//Paint xpGained variable
    private int sBeginningXp;
    private int aBeginningXp;
    private int dBeginningXp;
    private int sLvl;
    private int aLvl;
    private int dLvl;
    private int sXpGained;
    private int aXpGained;
    private int dXpGained;
    
    
}