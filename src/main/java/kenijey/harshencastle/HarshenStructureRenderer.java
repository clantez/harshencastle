package kenijey.harshencastle;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

import org.apache.commons.io.IOUtils;

import com.google.common.collect.Lists;

import kenijey.harshencastle.handlers.HandlerStructure;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.Template;

public class HarshenStructureRenderer
{
    private final List<Template.BlockInfo> blocks = Lists.<Template.BlockInfo>newArrayList();
    
    private BlockPos size = BlockPos.ORIGIN;
    
    private static HashMap<ResourceLocation, HarshenStructureRenderer> templateMap = new HashMap<>();
    
	private HarshenStructureRenderer(ResourceLocation location) {
		String s = location.getResourceDomain();
        String s1 = location.getResourcePath();
        InputStream stream = null;
        boolean flag;

        try
        {
            stream = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(HarshenCastle.MODID, "structures/" + s1 + ".nbt")).getInputStream();
            NBTTagCompound compound = CompressedStreamTools.readCompressed(stream);
            if (!compound.hasKey("DataVersion", 99))
            {
            	compound.setInteger("DataVersion", 500);
            }
            Template template = new Template();
            template.read(Minecraft.getMinecraft().getDataFixer().process(FixTypes.STRUCTURE, compound));
            this.blocks.clear();
            NBTTagList nbttaglist = compound.getTagList("size", 3);
            this.size = new BlockPos(nbttaglist.getIntAt(0), nbttaglist.getIntAt(1), nbttaglist.getIntAt(2));
            BasicPalette template$basicpalette = new BasicPalette();
            NBTTagList nbttaglist1 = compound.getTagList("palette", 10);

            for (int i = 0; i < nbttaglist1.tagCount(); ++i)
            {
                template$basicpalette.addMapping(NBTUtil.readBlockState(nbttaglist1.getCompoundTagAt(i)), i);
            }

            NBTTagList nbttaglist3 = compound.getTagList("blocks", 10);
            for (int j = 0; j < nbttaglist3.tagCount(); ++j)
            {
                NBTTagCompound nbttagcompound = nbttaglist3.getCompoundTagAt(j);
                NBTTagList nbttaglist2 = nbttagcompound.getTagList("pos", 3);
                BlockPos blockpos = new BlockPos(nbttaglist2.getIntAt(0), nbttaglist2.getIntAt(1), nbttaglist2.getIntAt(2));
                IBlockState iblockstate = template$basicpalette.stateFor(nbttagcompound.getInteger("state"));
                NBTTagCompound nbttagcompound1;

                if (nbttagcompound.hasKey("nbt"))
                {
                    nbttagcompound1 = nbttagcompound.getCompoundTag("nbt");
                }
                else
                {
                    nbttagcompound1 = null;
                }
                if(iblockstate.getBlock() != Blocks.STRUCTURE_BLOCK && iblockstate.getBlock() != Blocks.AIR)
                	this.blocks.add(new Template.BlockInfo(blockpos, iblockstate, nbttagcompound1));
            }
        }
        catch (Throwable var10)
        {
        }
        finally
        {
            IOUtils.closeQuietly(stream);
        }
	}
    
    public static HarshenStructureRenderer getTemplate(ResourceLocation location)
    {
    	if(templateMap.containsKey(location))
    		return templateMap.get(location);
    	HarshenStructureRenderer renderer = new HarshenStructureRenderer(location);
    	templateMap.put(location, renderer);
    	return renderer;
    }
	
    public void renderIntoWorld(World world, BlockPos basePos, float partialTicks)
    {
    	if(!world.isRemote)
    		return;
    	if(blocks.size() > 30000)
            return;
    	for(Template.BlockInfo block : blocks){
    		HarshenClientUtils.renderGhostBlock(block.blockState, basePos.add(block.pos), false, partialTicks);
    	}
    }
    
    static class BasicPalette implements Iterable<IBlockState>
    {
        public static final IBlockState DEFAULT_BLOCK_STATE = Blocks.AIR.getDefaultState();
        final ObjectIntIdentityMap<IBlockState> ids;
        private int lastId;

        private BasicPalette()
        {
            this.ids = new ObjectIntIdentityMap<IBlockState>(16);
        }

        public int idFor(IBlockState state)
        {
            int i = this.ids.get(state);

            if (i == -1)
            {
                i = this.lastId++;
                this.ids.put(state, i);
            }

            return i;
        }

        @Nullable
        public IBlockState stateFor(int id)
        {
            IBlockState iblockstate = this.ids.getByValue(id);
            return iblockstate == null ? DEFAULT_BLOCK_STATE : iblockstate;
        }

        public Iterator<IBlockState> iterator()
        {
            return this.ids.iterator();
        }

        public void addMapping(IBlockState p_189956_1_, int p_189956_2_)
        {
            this.ids.put(p_189956_1_, p_189956_2_);
        }
    }
}
