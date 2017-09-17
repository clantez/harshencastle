package kenijey.harshencastle.config;

import kenijey.harshencastle.base.BaseEnabledConfig;
import kenijey.harshencastle.base.HarshenStructure;
import net.minecraft.util.text.TextComponentTranslation;

public class StructuresEnabled extends BaseEnabledConfig<HarshenStructure>
{

	@Override
	public String getNameType() {
		return "Structures";
	}

	@Override
	protected String getComponantPathInConfig(HarshenStructure componant) {
		return componant.showName;
	}

	@Override
	protected String getComponantCommentName(HarshenStructure componant) {
		return new TextComponentTranslation("config.structure.name", componant.showName).getUnformattedText();
	}

}
