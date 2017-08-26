package kenijey.harshencastle.models;

import java.util.ArrayList;
import java.util.HashMap;

import kenijey.harshencastle.base.BaseHarshenBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

public class ModelArmour extends BaseHarshenBiped
{
	private ArrayList<ModelRenderer> helmet = new ArrayList<>();
	private ArrayList<ModelRenderer> chestplate = new ArrayList<>();
	 
	private HashMap<ModelRenderer, Vec3d> rotations = new HashMap<>();

	public ModelArmour(float scale)
	{
		super(scale, 0, 32, 64);
		 
		textureWidth = 32;
		textureHeight = 64;
	
		 
		 
		for(ModelRenderer renderer : helmet)
			bipedHead.addChild(renderer);
		 
		for(ModelRenderer renderer : chestplate)
			 bipedBody.addChild(renderer);
	}
	 
		float offsetX, float offsetY, float offsetZ, int textureX, int textureY) 
	{
		ModelRenderer r = newRender(dimensionX, dimensionY, dimensionZ, 0, 0, 0, offsetX, offsetY, offsetZ, textureX, textureY, false, this);
		chestplate.add(r);
		return r;
	}

	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		for(ModelRenderer renderer : helmet)
			renderAndRotate(renderer, bipedHead, scale);
		for(ModelRenderer renderer : chestplate)
			renderAndRotate(renderer, bipedBody, scale);

	}
	
	private void renderAndRotate(ModelRenderer renderer, ModelRenderer parent, float scale)
	{
		if(!parent.showModel)
			return;
		copyModelAngles(parent, renderer);
		if(rotations.keySet().contains(renderer))
			addAngle(renderer);
		renderer.render(scale);
	}
	
	private void addAngle(ModelRenderer renderer)
	{
		Vec3d vec = rotations.get(renderer);
		renderer.rotateAngleX += vec.x / 50f;
		renderer.rotateAngleY += vec.y / 50f;
		renderer.rotateAngleZ += vec.z / 50f;
	}
}
