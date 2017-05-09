package com.intertec.username.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

import com.intertec.username.exception.InternalException;

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
	@SuppressWarnings("unchecked")
	public HashSet<String> retrieveCollection() throws InternalException{
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
			throw new InternalException("Persistence error, there was a problem reading the file.", e);
		} catch (ClassNotFoundException e) {
			throw new InternalException("Persistence error, there was a problem with the type of class saved on file.", e);
		}        
        return collection;
	}
	
	/**
	 * Method responsible for adding an item to the collection.
	 * @param item the item to be added.
	 */
	public void addItem(String item) throws InternalException {		
		HashSet<String> collection = retrieveCollection();
		collection.add(item);
		writeCollection(collection);		 
	}
	
	/**
	 * Private class responsible for writing the collection to the file. 
	 * @param collection the collection to be written to the file.
	 */
	private void writeCollection(HashSet<String> collection) throws InternalException{
		try {
			FileOutputStream write=new FileOutputStream(filePath);
			ObjectOutputStream writeFile=new ObjectOutputStream(write);

			writeFile.writeObject(collection);
			writeFile.flush();
			writeFile.close();
		} catch (IOException e) {
			throw new InternalException("Persistence error, there was a problem saving the file.", e);
		}
	}
}
