package com.nate.library;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nate.library.models.Pokemon;

public class PokemonLib {
	
	private List<Pokemon> pokedex;

	public PokemonLib() {
		read(Settings.POKEMON_JSON_PATH);
	}
	
	public Pokemon getPokemonByID(int i) {
		return pokedex.get(i);
	}
	
	public List<Pokemon> getPokedex() {
		return pokedex;
	}
	
	private void read(String path) {
		if (path == null) {
			throw new ImageFileException("path not set");
		}
			
		String relativePath;
		try {
			relativePath = Block.class.getResource(path).getPath();
		} catch (NullPointerException e) {
			throw new ImageFileException("Relative path from the resource folder \"" + path 
					+ "\" for \"" + this.toString() + "\" did not find an image.");
		}

		JSONArray pokeJson = new JSONObject(relativePath).getJSONArray("pokedex");
		
		for (int i = 0; i < pokeJson.length(); i++) {
		    String id = pokeJson.getJSONObject(i).getString("id");
		    String name = pokeJson.getJSONObject(i).getString("name");
		    JSONArray types = pokeJson.getJSONObject(i).getJSONArray("type");
		    String type1 = (String) types.get(0),
		    	   type2 = null;
		    if (types.length() > 1) 
		    	type2 = (String) types.get(0);
		    String icon = pokeJson.getJSONObject(i).getString("icon");
		    
		    Pokemon pokemon = new Pokemon(id, name, type1, type2, icon);
		    pokedex.add(pokemon);
		}
	}
}
