package kenijey.harshencastle.config;

import kenijey.harshencastle.base.BaseConfig;
import kenijey.harshencastle.dimensions.DimensionPontus;
import net.minecraft.util.text.TextComponentTranslation;

public class IdConfig extends BaseConfig {

	public static int PontusDimension;
	public static int EntitySoulPart;
	public static int EntitySoullessKnight;
	public static int EntityThrown;
	public static int EntityHarshenSoul;

	
	@Override
	public String getName() {
		return "Ids";
	}

	@Override
	public void read() {
		PontusDimension = get("pontus_dimension", DimensionPontus.DIM_NAME, 5);
		EntitySoullessKnight = get("entity_soulless_knight", 83);
		EntitySoulPart = get("entity_soul_part", 84);
		EntityThrown = get("entity_thrown", 85);
		EntityHarshenSoul = get("entity_harshen_soul", 86);
	}
	
	@Override
	protected <T> T get(String name, String configName, T normal) {
		return super.get(name, getName(), new TextComponentTranslation("config.id",  new TextComponentTranslation(configName).getUnformattedText()).getUnformattedText(), normal);
	}
	
	@Override
	protected <T> T get(String name, T normal) {
		return get(name, name.replaceFirst("_", ".") + ".name", normal);
	}

}
