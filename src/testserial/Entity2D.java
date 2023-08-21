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
import java.io.DataOutputStream;

public class Entity2D implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int MAX_ITEMS = 10; 
	public static int nb_generated = 0;
	private int id;
	private String name;
	private float x;
	private float y;
	private ArrayList<Integer> items;
	
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

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
	} 
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
	}
	

	public byte[] to_bytes_full_data() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(out);
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
		return out.toByteArray();
	}
	

	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeInt(id);
		out.writeFloat(x);
		out.writeFloat(y);
		out.writeUTF(name);
		// We write the number of items in one byte.
		out.write(items.size());
		
		// Then we write the value of each item.
		for (int i=0; i< items.size(); i++) {
			out.writeInt(items.get(i));
		}
	}


	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		id = in.readInt();
		x = in.readFloat();
		y = in.readFloat();
		name = in.readUTF();
		int nb_items = (int) in.read();
		items = new ArrayList<Integer>();
		for (int i=0; i< nb_items; i++) {
			items.add(in.readInt());
		}
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

	

	
}
