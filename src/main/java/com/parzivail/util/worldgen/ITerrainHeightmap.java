package com.parzivail.util.worldgen;

/**
 * Created by colby on 12/28/2016.
 */
public interface ITerrainHeightmap
{
	/**
	 * Gets the height of the top block (natural worldgen, not player creation) at (x, z)
	 *
	 * @param x The x position
	 * @param z The y position
	 * @return The height of the block at (x, z)
	 */
	double getHeightAt(int x, int z);

	/**
	 * Gets the biome noise interpolation value (0<=x<=1) for the specified position
	 *
	 * @param x The x position
	 * @param z The y position
	 * @return biome noise interpolation value of the block at (x, z)
	 */
	double getBiomeLerpAmount(int x, int z);

	/**
	 * Gets the weight (0<=x<=1) of each biome in the terrain for the specified position.
	 *
	 * Use this in the future to determine mob spawns, etc.
	 * Say, if the mountain biome [length - 1], which *should* be the tallest, has a lerp
	 * value > 0.8, meaning the current position is > 80% that biome, spawn some mountain-faring
	 * billy goats. If the biome [0] > 0.8, it's > 80% "flatlands" per se, so spawn some sheep or something.
	 *
	 * @param x The x position
	 * @param z The y position
	 * @return biome weight value of the block at (x, z)
	 */
	double[] getBiomeWeightsAt(int x, int z);
}
