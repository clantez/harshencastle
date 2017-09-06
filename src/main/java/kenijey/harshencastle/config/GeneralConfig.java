package kenijey.harshencastle.config;

import kenijey.harshencastle.base.BaseConfig;

public class GeneralConfig extends BaseConfig {

	public static boolean bloodDrops;
	public static boolean bloodOffHand;
	public static double bloodChance;
	
	@Override
	public String getName() {
		return "General";
	}

	@Override
	public void read() {
		bloodDrops = getBoolean("blood_drops", true);
		bloodChance = getDouble("blood_chance", 0.3D);
		bloodOffHand = getBoolean("blood_offhand", true);
	}

	@Override
	public void save() {
		setBoolean("blood_drops", bloodDrops);
		setDouble("blood_chance", bloodChance);
		setBoolean("blood_offhand", bloodOffHand);
	}

}
