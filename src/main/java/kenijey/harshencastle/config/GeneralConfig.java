package kenijey.harshencastle.config;

import kenijey.harshencastle.base.BaseConfig;

public class GeneralConfig extends BaseConfig {

	public static boolean bloodDrops;
	public static double chanceBloodSpawns;
	
	@Override
	public String getName() {
		return "General";
	}

	@Override
	public void read() {
		bloodDrops = getBoolean("blood_drops", true);
		chanceBloodSpawns = getDouble("blood_chance", 0.3D);
	}

	@Override
	public void save() {
		setBoolean("blood_drops", bloodDrops);
		setDouble("blood_chance", chanceBloodSpawns);
	}

}
