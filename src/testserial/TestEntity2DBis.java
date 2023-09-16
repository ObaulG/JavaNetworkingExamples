package testserial;

import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestEntity2DBis {

	public static final int NB_ENTITIES = 1000;
	public static final float XMIN = (float)-1000.0;
	public static final float XMAX = (float) 1000.0;
	public static final float YMIN = (float)-1000.0;
	public static final float YMAX = (float) 1000.0;
	public static final float DELTAX = XMAX - XMIN;
	public static final float DELTAY = YMAX - YMIN;
	

	public static void main(String[] args) {
		
		Random rng = new Random();
		ArrayList<Entity2D> entity_array = new ArrayList<Entity2D>(NB_ENTITIES);
		String name = "entity";
		float x;
		float y;
		int items_to_generate;
		int item_value;
		// Generate the entities
		for(int i =0; i< NB_ENTITIES; i++) {
			x = XMIN + rng.nextFloat() * DELTAX;
			y = YMIN + rng.nextFloat() * DELTAY;
			
			items_to_generate = rng.nextInt(Entity2D.MAX_ITEMS + 1);
			Entity2D new_entity = new Entity2D(name + i, x, y);
			for(int j=0; j<items_to_generate;j++) {
				item_value = rng.nextInt(Entity2D.ITEM_MAX_VAL - Entity2D.ITEM_MIN_VAL);
				new_entity.putItem(item_value);
			}
			entity_array.add(new_entity);
		}
		
		// Serialization using JSON
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File("donneestestbis.json"), entity_array);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// How long is the file ?
		File saved = new File("donneestestbis.json");
		System.out.println("Taille du fichier : " + saved.length() + " octets");
	}

}
