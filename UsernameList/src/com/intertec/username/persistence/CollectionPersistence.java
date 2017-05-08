package com.intertec.username.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

/**
 * Utility abstract class responsible for the persistence of simple String Collections.
 * @author vitor
 *
 */
public abstract class CollectionPersistence {
	
	String filePath = null;
	
	/**
	 * Constructor receiving the filePath
	 * @param filePath
	 */
	public CollectionPersistence(String filePath){
		this.filePath = filePath;
	}
	
	
	/**
	 * Method responsible for retrieving the collection
	 * @return HashSet<String> with the collection .
	 */
	public HashSet<String> retrieveCollection(){
		HashSet<String> collection = null;
        try{
        	FileInputStream read=new FileInputStream(filePath);
        	ObjectInputStream readFile=new ObjectInputStream(read);
        	collection = (HashSet<String>) readFile.readObject();
        	readFile.close();
        }catch (FileNotFoundException e) {
        	//In case the file doesn't exists I create a new one.
        	collection = new HashSet<String>();
        	writeCollection(collection);
        	return collection;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
        return collection;
	}
	
	/**
	 * Method responsible for adding an item to the collection.
	 * @param item the item to be added.
	 */
	public void addItem(String item) {		
		HashSet<String> collection = retrieveCollection();
		collection.add(item);
		writeCollection(collection);		 
	}
	
	/**
	 * Private class responsible for writing the collection to the file. 
	 * @param collection the collection to be written to the file.
	 */
	private void writeCollection(HashSet<String> collection){
		try {
			FileOutputStream write=new FileOutputStream(filePath);
			ObjectOutputStream writeFile=new ObjectOutputStream(write);

			writeFile.writeObject(collection);
			writeFile.flush();
			writeFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
