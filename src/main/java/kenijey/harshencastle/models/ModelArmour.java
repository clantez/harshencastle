package kenijey.harshencastle.models;

import java.util.ArrayList;
import java.util.HashMap;

import kenijey.harshencastle.base.BaseHarshenBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3i;

public class ModelArmour extends BaseHarshenBiped
{
	 private ArrayList<ModelRenderer> helmet = new ArrayList<>();
	 
	 private HashMap<ModelRenderer, Vec3i> rotations = new HashMap<>();

	 public ModelArmour(float scale)
	 {
		 super(scale, 0, 32, 64);
		 
		 textureWidth = 32;
		 textureHeight = 64;
	
		 addRenderer(helmet, 10, 1, 8, -5, -9, -3, 0, 0);
		 addRenderer(helmet, 10, 3, 1, -5, -7, 4, 36, 0);
		 addRenderer(helmet, 10, 3, 1, -5, -3, 4, 36, 4);
		 addRenderer(helmet, 1, 10, 1, 1, -8, 4, 0, 9);
		 addRenderer(helmet, 1, 10, 1, -2, -8, 4, 4, 9);
		 addRenderer(helmet, 1, 1, 7, 4, -1, -5, 8, 9);
		 addRenderer(helmet, 1, 3, 1, 4, -1, 2, 40, 9);
		 addRenderer(helmet, 1, 3, 1, -5, -1, 2, 44, 9);
		 addRenderer(helmet, 1, 1, 2, 4, -3, -2, 58, 0);
		 addRenderer(helmet, 1, 1, 2, -5, -3, -2, 58, 3);
		 addRenderer(helmet, 1, 5, 1, -5, -6, 0, 0, 20);
		 addRenderer(helmet, 1, 5, 1, 4, -6, 0, 4, 20);
		 addRenderer(helmet, 1, 1, 2, -6, -3, 0, 0, 26);
		 addRenderer(helmet, 1, 1, 2, 5, -3, 0, 0, 29);
		 addRenderer(helmet, 1, 1, 4, -5, -5, -2, 6, 27);
		 addRenderer(helmet, 1, 1, 4, 4, -5, -2, 16, 27);
		 addRenderer(helmet, 1, 6, 1, 4, -8, 2, 26, 25);
		 addRenderer(helmet, 1, 6, 1, -5, -8, 2, 30, 25);
		 addRenderer(helmet, 1, 3, 1, -5, -4, -3, 48, 9);
		 addRenderer(helmet, 1, 3, 1, 4, -4, -3, 52, 9);
		 addRenderer(helmet, 1, 1, 1, -6, -4, 1, 8, 24);
		 addRenderer(helmet, 1, 1, 1, 5, -4, 1, 12, 24);
		 rotations.put(addRenderer(helmet, 1, 5, 1, 4, -10, -2, 40, 13), new Vec3i(8.521d, 0, 0));
		 rotations.put(addRenderer(helmet, 1, 5, 1, -5, -10, -2, 44, 13), new Vec3i(8.521d, 0, 0));
		 addRenderer(helmet, 8, 1, 6, -4, -10, -2, 8, 17);
		 
		 for(ModelRenderer renderer : helmet)
			 bipedHead.addChild(renderer);
	 }
	 
	private ModelRenderer addRenderer(ArrayList listToAdd, int dimensionX, int dimensionY, int dimensionZ,
			float offsetX, float offsetY, float offsetZ, int textureX, int textureY) 
	{
		ModelRenderer r = newRender(dimensionX, dimensionY, dimensionZ, 0, 0, 0, offsetX, offsetY, offsetZ, textureX, textureY, false, this);
		listToAdd.add(r);
		return r;
	}

	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		for(ModelRenderer renderer : helmet)
		{
			copyModelAngles(bipedHead, renderer);
			renderer.render(scale);
		}
		for(ModelRenderer r : rotations.keySet())
		{
			Vec3i vec = rotations.get(r);
			r.rotateAngleX += vec.getX();
			r.rotateAngleY += vec.getY();
			r.rotateAngleZ += vec.getZ();
		}
	}
}
