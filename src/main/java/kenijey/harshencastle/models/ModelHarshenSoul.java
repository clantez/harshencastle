package kenijey.harshencastle.models;

import java.util.ArrayList;

import kenijey.harshencastle.base.BaseHarshenBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHarshenSoul extends BaseHarshenBiped
{	
	
	public ArrayList<ModelRenderer> headParts = new ArrayList<>();
	public ArrayList<ModelRenderer> leftArmParts = new ArrayList<>();
	public ModelHarshenSoul()
	{
		this(0);
	}   
	 
	public ModelHarshenSoul(float modelSize)
	{
		super(modelSize, 0.0F, 64, 64);
		this.bipedHead = newRender(5, 1, 1, 0, 0, 0, -4, -8, 3, 0, 0, false, this);
		renderH(8, 5, 8, -4, -5, -4, 0, 0);
		renderH(6, 1, 7, -4, -8, -4, 0, 0);
		renderH(2, 2, 5, 2, -7, -4, 0, 0);
		renderH(1, 1, 2, 2, -6, 1, 0, 0);
		renderH(2, 1, 4, 2, -8, -4, 0, 0);
		renderH(1, 1, 1, 3, -8, 0, 0, 0);
		renderH(6, 2, 8, -4, -7, -4, 0, 0);
		
		this.bipedLeftArm = newRender(4, 5, 4, 5, 2, 0, -1, -2, -2, 40, 16, true, this);
		renderA(4, 5, 4, 5, 2, 0, -1, 5, -2, 40, 16, false);
		renderA(1, 2, 2, 5, 2, 0, 0, 3, -1, 40, 16, false);
		renderA(1, 2, 1, 5, 2, 0, 1, 3, -1, 40, 16, false);
		
		this.bipedHeadwear.isHidden = true;
	}
		
	private void renderA(int dimensionX, int dimensionY, int dimensionZ, float positionX, float positionY, float positionZ,
			float offsetX, float offsetY, float offsetZ, int textureX, int textureY, boolean mirror) {
		this.leftArmParts.add(newRender(dimensionX, dimensionY, dimensionZ, positionX, positionY, positionZ, offsetX, offsetY, offsetZ, textureX, textureY, mirror, this));
	}
	
	private void renderH(int dimensionX, int dimensionY, int dimensionZ, float offsetX, float offsetY, float offsetZ, int textureX, int textureY)
	{
		this.renderH(dimensionX, dimensionY, dimensionZ, 0, 0, 0, offsetX, offsetY, offsetZ, textureX, textureY,false);
	}
	
	private void renderH(int dimensionX, int dimensionY, int dimensionZ, float positionX, float positionY, float positionZ,
			float offsetX, float offsetY, float offsetZ, int textureX, int textureY, boolean mirror) {
		this.headParts.add(newRender(dimensionX, dimensionY, dimensionZ, positionX, positionY, positionZ, offsetX, offsetY, offsetZ, textureX, textureY, mirror, this));
	}
	
	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		for(ModelRenderer render : headParts)
			render.render(scale);
		for(ModelRenderer render : leftArmParts)
			render.render(scale);
		this.bipedHead.render(scale);
        this.bipedBody.render(scale);
        this.bipedRightArm.render(scale);
        this.bipedLeftArm.render(scale);
        this.bipedRightLeg.render(scale);
        this.bipedLeftLeg.render(scale);
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
		for(ModelRenderer render : headParts)
			setAllRotSame(this.bipedHead, render);
		for(ModelRenderer render : leftArmParts)
			setAllRotSame(this.bipedLeftArm, render);
	}
	
	private void setAllRotSame(ModelRenderer parent, ModelRenderer render)
	{
		render.rotateAngleX = parent.rotateAngleX;
		render.rotateAngleY = parent.rotateAngleY;
		render.rotateAngleZ = parent.rotateAngleZ;
	}
}
