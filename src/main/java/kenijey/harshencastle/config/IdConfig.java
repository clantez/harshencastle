package kenijey.harshencastle.config;

import kenijey.harshencastle.base.BaseConfig;

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
		PontusDimension = getInt("pontus_dimension", 5);
		EntitySoullessKnight = getInt("entity_soulless_knight", 83);
		EntitySoulPart = getInt("entity_soul_part", 84);
	}

	@Override
	public void save() {
		setInt("pontus_dimension", PontusDimension);
		setInt("entity_soulless_knight", EntitySoullessKnight);
		setInt("entity_soul_part", EntitySoulPart);
	}

}
