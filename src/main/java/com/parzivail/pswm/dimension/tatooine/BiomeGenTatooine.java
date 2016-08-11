package com.parzivail.pswm.dimension.tatooine;

import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.dimension.BiomeGenPSWM;
import com.parzivail.pswm.world.StructureBank;
import com.parzivail.pswm.world.gen.WorldGenMV;
import com.parzivail.util.math.MathUtils;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

public class BiomeGenTatooine extends BiomeGenPSWM
{
	public int locY = 0;

	public BiomeGenTatooine(int biomeId)
	{
		super(biomeId);

		this.setBiomeName("Tatooine");

		this.enableRain = false;
		this.enableSnow = false;

		this.rootHeight = 0.1f;
		this.heightVariation = 0.0f;

		this.spawnableCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();

		this.topBlock = StarWarsMod.blockTatooineSand;
		this.fillerBlock = Blocks.stone;

		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.deadBushPerChunk = -999;
		this.theBiomeDecorator.reedsPerChunk = -999;
		this.theBiomeDecorator.cactiPerChunk = -999;

		this.spawnableCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.locY = (int)MathUtils.map(this.rootHeight, -2, 2, 0, 128);
	}

	@Override
	public void decorate(World par1World, Random par2Random, int chunkX, int chunkZ)
	{
		if (par2Random.nextInt(400) == 0)
		{
			int k = chunkX + par2Random.nextInt(16) + 8;
			int l = chunkZ + par2Random.nextInt(16) + 8;
			//gen super homestead
		}
		if (par2Random.nextInt(30) == 0)
		{
			int k = chunkX + par2Random.nextInt(16) + 8;
			int l = chunkZ + par2Random.nextInt(16) + 8;
			WorldGenMV worldGenMV = new WorldGenMV();
			worldGenMV.generate(par1World, par2Random, k, par1World.getHeightValue(k, l) + 2, l);
		}
		if (par2Random.nextInt(300) == 0)
		{
			int k = chunkX + par2Random.nextInt(16) + 8;
			int l = chunkZ + par2Random.nextInt(16) + 8;
			//gen normal homestead
		}

		StructureBank.getMosEisley().genComposite(par1World, chunkX, locY, chunkZ, 2, 2);
		StructureBank.getObiWanHut().genComposite(par1World, chunkX, locY, chunkZ, -15, -15);
	}
}