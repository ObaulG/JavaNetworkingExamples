package testserial;

import java.util.ArrayList;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.io.Externalizable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Entity2D implements Externalizable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int MAX_ITEMS = 10;
	public static final int ITEM_MIN_VAL = -100;
	public static final int ITEM_MAX_VAL = 120;
	public static int nb_generated = 0;
	private int id;
	private String name;
	private float x;
	private float y;
	private ArrayList<Integer> items;
	
	public Entity2D() {
		items = new ArrayList<Integer>();
	}
	
	public Entity2D(String name, float x, float y){
		this.name = name;
		this.x = x;
		this.y = y;
		this.id = nb_generated;
		nb_generated++;
		items = new ArrayList<Integer>();
	}
	
	public Entity2D(String name, float x, float y, int id, ArrayList<Integer> items) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.id = id;
		this.items = items;
	}
	
	/**
	 * Create an instance of Entity2D from a DataInputStream bytes that was written by the method to_bytes_full_data. 
	 * Attributes are represented in this order : 
	 * id (int), x and y (float), name (String), count of items (int), items (ints).
	 * @param dis : The DataInputStream holding the data.
	 * @return The instance of Entity2D corresponding to the bytes, or null if an exception is raised.
	 */
	public static Entity2D fromBytes(DataInputStream dis) {
		Entity2D instance = null;
		try {
			int id = dis.readInt();
			float x = dis.readFloat();
			float y = dis.readFloat();
			String name = dis.readUTF();
			int nb_items = (int) dis.read();
			ArrayList<Integer> items = new ArrayList<Integer>();
			for (int i=0; i< nb_items; i++) {
				items.add(dis.readInt());
			}
			instance = new Entity2D(name, x, y, id, items);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return instance;
	}
	
	public static Entity2D fromJSON(String json_string) {
		ObjectMapper mapper = new ObjectMapper();
		Entity2D entity = null;
		try {
			entity = mapper.readValue(json_string, Entity2D.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
	} 
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
	}
	
	/**
	 * Write the data of this instance into the DataOutputStream given in parameter in this order :
	 * id (int), x and y (float), name (String), count of items (int), items (ints).
	 * @param data
	 */
	public void to_bytes_full_data(DataOutputStream data) {
		try {
			data.writeInt(id);
			data.writeFloat(x);
			data.writeFloat(y);
			data.writeUTF(name);
			// We write the number of items in one byte.
			data.write(items.size());
			
			// Then we write the value of each item.
			for (int i=0; i< items.size(); i++) {
				data.writeInt(items.get(i));
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public String toJSON() {
		ObjectMapper mapper = new ObjectMapper();
		String json_string = "";
		try {
			json_string = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json_string;
	}
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeInt(id);
		out.writeFloat(x);
		out.writeFloat(y);
		out.writeUTF(name);
		out.writeObject(items);
		
		/*
		// We write the number of items in one byte.
		out.write(items.size());
		
		// Then we write the value of each item.
		for (int i=0; i< items.size(); i++) {
			out.writeInt(items.get(i));
		}
		*/
	}
	
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		id = in.readInt();
		x = in.readFloat();
		y = in.readFloat();
		name = in.readUTF();
		items = (ArrayList<Integer>) in.readObject();
		
		/*
		int nb_items = (int) in.read();
		items = new ArrayList<Integer>();
		for (int i=0; i< nb_items; i++) {
			items.add(in.readInt());
		}
		*/
	}
	
	public void putItem(int value) {
		if (items.size() < 10)
			items.add(value);
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static int getMaxItems() {
		return MAX_ITEMS;
	}

	public static int getNb_generated() {
		return nb_generated;
	}

	public int getId() {
		return id;
	}

	public ArrayList<Integer> getItems() {
		return items;
	}

	@Override
	public String toString() {
		return "Entity2D [id=" + id + ", name=" + name + ", x=" + x + ", y=" + y + ", items=" + items + "]";
	}

	

	
}
