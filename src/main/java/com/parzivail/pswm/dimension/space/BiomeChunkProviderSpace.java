package com.parzivail.pswm.dimension.space;

import com.parzivail.pswm.StarWarsMod;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BiomeChunkProviderSpace extends ChunkProviderGenerate
{
	private final Random rand;
	/**
	 * Reference to the World object.
	 */
	private final World worldObj;

	public BiomeChunkProviderSpace(World world, long seed, boolean features)
	{
		super(world, seed, features);
		this.worldObj = world;
		this.rand = new Random(seed);
	}

	/**
	 * Returns if the IChunkProvider supports saving.
	 */
	@Override
	public boolean canSave()
	{
		return true;
	}

	/**
	 * Checks to see if a chunk exists at x, y
	 */
	@Override
	public boolean chunkExists(int p_73149_1_, int p_73149_2_)
	{
		return true;
	}

	@Override
	public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
	{
		return null;
	}

	@Override
	public void func_147424_a(int p_147424_1_, int p_147424_2_, Block[] p_147424_3_)
	{
		Arrays.fill(p_147424_3_, Blocks.air);
	}

	@Override
	public int getLoadedChunkCount()
	{
		return 0;
	}

	/**
	 * Returns a list of creatures of the specified type that can genComposite at the
	 * given location.
	 */
	@Override
	public List getPossibleCreatures(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_)
	{
		BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(p_73155_2_, p_73155_4_);
		return biomegenbase.getSpawnableList(p_73155_1_);
	}

	/**
	 * loads or generates the chunk at the chunk location specified
	 */
	@Override
	public Chunk loadChunk(int p_73158_1_, int p_73158_2_)
	{
		return this.provideChunk(p_73158_1_, p_73158_2_);
	}

	/**
	 * Converts the instance data to a readable string.
	 */
	@Override
	public String makeString()
	{
		return "RandomLevelSource";
	}

	/**
	 * Populates chunk with ores etc etc
	 */
	@Override
	public void populate(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_)
	{
		int k = p_73153_2_ * 16;
		int l = p_73153_3_ * 16;
		StarWarsMod.biomeSpace.decorate(this.worldObj, this.rand, k, l);
	}

	/**
	 * Will return back a chunk, if it doesn't exist and its not a MP client it
	 * will generates all the blocks for the specified chunk from the map seed
	 * and chunk seed
	 */
	@Override
	public Chunk provideChunk(int p_73154_1_, int p_73154_2_)
	{
		this.rand.setSeed(p_73154_1_ * 341873128712L + p_73154_2_ * 132897987541L);
		Block[] ablock = new Block[65536];
		byte[] abyte = new byte[65536];
		this.func_147424_a(p_73154_1_, p_73154_2_, ablock);

		Chunk chunk = new Chunk(this.worldObj, ablock, abyte, p_73154_1_, p_73154_2_);
		byte[] abyte1 = chunk.getBiomeArray();

		for (int k = 0; k < abyte1.length; ++k)
			abyte1[k] = 0;

		chunk.generateSkylightMap();
		return chunk;
	}

	@Override
	public void recreateStructures(int p_82695_1_, int p_82695_2_)
	{
	}

	@Override
	public void replaceBlocksForBiome(int p_147422_1_, int p_147422_2_, Block[] p_147422_3_, byte[] p_147422_4_, BiomeGenBase[] p_147422_5_)
	{
	}

	/**
	 * Two modes of operation: if passed true, save all Chunks in one go. If
	 * passed false, save up to two chunks. Return true if all chunks have been
	 * saved.
	 */
	@Override
	public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_)
	{
		return true;
	}

	/**
	 * Save extra data not associated with any Chunk. Not saved during autosave,
	 * only during world unload. Currently unimplemented.
	 */
	@Override
	public void saveExtraData()
	{
	}

	/**
	 * Unloads chunks that are marked to be unloaded. This is not guaranteed to
	 * unload every such chunk.
	 */
	@Override
	public boolean unloadQueuedChunks()
	{
		return false;
	}

}
