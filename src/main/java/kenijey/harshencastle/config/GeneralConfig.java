package kenijey.harshencastle.config;

import kenijey.harshencastle.base.BaseConfig;

public class GeneralConfig extends BaseConfig {

	public static boolean bloodDrops;
	public static boolean bloodOffHand;
	public static double bloodChance;
	public static boolean renderPlates;
	public static double structureRuinChance;
	
	@Override
	public String getName() {
		return "General";
	}

	@Override
	public void read() {
		bloodDrops = get("blood_drops", true);
		bloodChance = get("blood_chance", 0.3D);
		bloodOffHand = get("blood_offhand", true);
		renderPlates = get("render_flatplates", true);
		structureRuinChance = get("structure_ruin_chance", 0.2D);
	}

	@Override
	public void save() {
		set("blood_drops", bloodDrops);
		set("blood_chance", bloodChance);
		set("blood_offhand", bloodOffHand);
		set("render_flatplates", renderPlates);
		set("structure_ruin_chance", structureRuinChance);
	}

}
