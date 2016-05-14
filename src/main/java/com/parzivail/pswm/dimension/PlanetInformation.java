package com.parzivail.pswm.dimension;

import com.parzivail.pswm.Resources;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

/**
 * Created by Colby on 5/14/2016.
 */
public class PlanetInformation
{
	public class StarPosition
	{
		public float x, y;

		public StarPosition(float x, float y)
		{
			this.x = x;
			this.y = y;
		}
	}

	public static PlanetInformation getPlanet(String name)
	{
		for (PlanetInformation p : Resources.planetInformation)
			if (p.getInternalName().equalsIgnoreCase(name))
				return p;
		return null;
	}

	// Canon info
	private StarPosition position = new StarPosition(0, 0);
	private String name = "UNKNOWN";
	private String description = "UNKNOWN";
	private String affiliation = "UNKNOWN";
	private int suns = 0;
	private int moons = 0;
	private ArrayList<String> terrain = new ArrayList<>();
	private ArrayList<String> nativeSpecies = new ArrayList<>();
	private ArrayList<String> resources = new ArrayList<>();

	// Mod info
	private int dimensionId = 0;
	private String internalName = "UNKNOWN";
	private ResourceLocation cubeTexture;
	private boolean obfuscated = false;

	public void setPosition(float x, float y)
	{
		this.position = new StarPosition(x, y);
	}

	public StarPosition getPosition()
	{
		return this.position;
	}

	public boolean isObfuscated()
	{
		return obfuscated;
	}

	public void setObfuscated(boolean obfuscated)
	{
		this.obfuscated = obfuscated;
	}

	public int getSuns()
	{
		return suns;
	}

	public void setSuns(int suns)
	{
		this.suns = suns;
	}

	public int getMoons()
	{
		return moons;
	}

	public void setMoons(int moons)
	{
		this.moons = moons;
	}

	public ArrayList<String> getResources()
	{
		return resources;
	}

	public void addResource(String resource)
	{
		this.resources.add(resource);
	}

	public void setCubeTexture(ResourceLocation cubeTexture)
	{
		this.cubeTexture = cubeTexture;
	}

	public ResourceLocation getCubeTexture()
	{
		return cubeTexture;
	}

	public void setAffiliation(String affiliation)
	{
		this.affiliation = affiliation;
	}

	public String getAffiliation()
	{
		return affiliation;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDimensionId(int dimensionId)
	{
		this.dimensionId = dimensionId;
	}

	public int getDimensionId()
	{
		return dimensionId;
	}

	public void setName(String name)
	{
		this.name = name;
		this.setInternalName(name);
	}

	public void setInternalName(String internamName)
	{
		this.internalName = internamName;
	}

	public String getInternalName()
	{
		return internalName;
	}

	public String getName()
	{
		return name;
	}

	public void addNativeSpecies(String species)
	{
		this.nativeSpecies.add(species);
	}

	public ArrayList<String> getNativeSpecies()
	{
		return nativeSpecies;
	}

	public void addTerrain(String terrain)
	{
		this.terrain.add(terrain);
	}

	public ArrayList<String> getTerrain()
	{
		return terrain;
	}
}
