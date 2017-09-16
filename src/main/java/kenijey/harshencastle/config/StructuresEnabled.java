package kenijey.harshencastle.config;

import java.util.ArrayList;
import java.util.HashMap;

import kenijey.harshencastle.base.BaseConfig;
import kenijey.harshencastle.base.BaseEnabledConfig;
import kenijey.harshencastle.base.HarshenStructure;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.config.Property;

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
