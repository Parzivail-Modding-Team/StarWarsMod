package com.parzivail.pswm.dimension.scarif;

import com.parzivail.pswm.dimension.DimensionInfo;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.IChunkGenerator;

/**
 * Created by colby on 12/22/2016.
 */

public class WorldProviderScarif extends WorldProvider
{
	@Override
	protected void init()
	{
		this.hasSkyLight = true;
		this.biomeProvider = new BiomeProviderSingle(DimensionInfo.biomeScarif);
	}

	@Override
	public IChunkGenerator createChunkGenerator()
	{
		return new ChunkProviderScarif(world, world.getSeed());
	}

	@Override
	public String getWelcomeMessage()
	{
		return "Jumping out of Hyperspace near " + this.world.getWorldInfo().getWorldName();
	}

	@Override
	public String getDepartMessage()
	{
		return "Blasting out of " + this.world.getWorldInfo().getWorldName();
	}

	@Override
	public String getSaveFolder()
	{
		return "SCARIF";
	}

	@Override
	public DimensionType getDimensionType()
	{
		return DimensionInfo.scarifDimension;
	}

}