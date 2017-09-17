package kenijey.harshencastle.config;

import kenijey.harshencastle.base.BaseConfig;
import kenijey.harshencastle.dimensions.DimensionPontus;

public class IdConfig extends BaseConfig {

	public static int PontusDimension;
	public static int EntitySoulPart;
	public static int EntitySoullessKnight;

	
	@Override
	public String getName() {
		return "Ids";
	}

	@Override
	public void read() {
		PontusDimension = get("pontus_dimension", 5);
		EntitySoullessKnight = get("entity_soulless_knight", 83);
		EntitySoulPart = get("entity_soul_part", 84);
	}

	@Override
	public void save() {
		set("pontus_dimension", PontusDimension);
		set("entity_soulless_knight", EntitySoullessKnight);
		set("entity_soul_part", EntitySoulPart);
	}

}
