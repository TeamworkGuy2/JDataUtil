package dataUtility;

import java.awt.Color;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/** A utility to manage various properties, such as floats, ints, booleans, colors, etc.
 * These values are stored in the map passed to this class' constructor {@link #PropertyMap(Map)}.<br/>
 * @author TeamworkGuy2
 * @since 2014-4-11
 */
public class PropertyMap {
	private Map<String, String> properties;
	private Map<String, String> usedProperties;


	/** Create a property map using the specified map
	 * @param map the map to read and write property values to and from
	 */
	public PropertyMap(Map<String, String> map) {
		this.properties = map;
		this.usedProperties = new HashMap<String, String>();
	}


	/**
	 * @return the property map passed to this object's constructor
	 * @see #PropertyMap(Map)
	 */
	public Map<String, String> getProperties() {
		return properties;
	}


	/** Return a subset of this property map containing only the used properties in this map.
	 * A used property is a property that has been requested via a get or set method,
	 * for example {@link #accessString(String, String)} or {@link #accessInt(String, int)}, etc.
	 * @return a subset map of all of the used properties in this object's properties map. 
	 */
	public Map<String, String> getUsedProperties() {
		return usedProperties;
	}


	/** Get the specified string property value. If the property is empty, set it
	 * to the specified default value.
	 * @param name the name of the property
	 * @param defaultValue the default value to store in the property and return if the property is empty
	 * @return the value of the property with the specified name
	 */
	public String accessString(String name, String defaultValue) {
		String str = getString(name);
		if(str == null) {
			setString(name, defaultValue);
			return defaultValue;
		}
		return str;
	}


	/** Get the specified string property value.
	 * @param name the name of the property
	 * @return the value associated with the specified property
	 */
	public String getString(String name) {
		String str = properties.get(name);
		if(str != null) {
			usedProperties.put(name, str);
		}
		return str;
	}


	/** Set the specified string property. If the property already exists it is
	 * overwritten.
	 * @param name the name of the property to set
	 * @param value the value to assign to the property
	 */
	public void setString(String name, String value) {
		properties.put(name, value);
		usedProperties.put(name, value);
	}


	/** Get the specified file path property value. If the property is empty,
	 * set it to the specified default value using {@link File#getPath()}.
	 * @param name the name of the property
	 * @param defaultValue the default value to store in the property and return if the property is empty
	 * @return the value of the property with the specified name
	 */
	public File accessFile(String name, File defaultValue) {
		String str = getString(name);
		if(str == null) {
			setString(name, defaultValue.getPath());
			return defaultValue;
		}
		return new File(str);
	}


	/** Set the specified file path property using {@link File#getPath()}. If the property already
	 * exists it is overwritten.
	 * @param name the name of the property to set
	 * @param value the value to assign to the property
	 */
	public void setFile(String name, File value) {
		setString(name, value.getPath());
	}


	/** Get the specified URL path property value. If the property is empty,
	 * set it to the specified default value using {@link URL#toExternalForm()}.
	 * @param name the name of the property
	 * @param defaultValue the default value to store in the property and return if the property is empty
	 * @return the value of the property with the specified name
	 */
	public URL accessUrl(String name, URL defaultValue) {
		String str = getString(name);
		if(str == null) {
			setString(name, defaultValue.toExternalForm());
			return defaultValue;
		}
		URL result = null;
		try {
			result = new URL(str);
		} catch (MalformedURLException e) {
			throw new RuntimeException(str, e);
		}
		return result;
	}


	/** Set the specified URL path property using {@link URL#toExternalForm()}. If the property
	 * already exists it is overwritten.
	 * @param name the name of the property to set
	 * @param value the value to assign to the property
	 */
	public void setUrl(String name, URL value) {
		setString(name, value.toExternalForm());
	}


	/** Get the specified boolean property value. If the property is empty, set it to the specified default value.
	 * @param name the name of the property
	 * @param defaultValue the default value to store in the property and return if the property is empty
	 * @return the value of the property with the specified name.
	 */
	public boolean accessBoolean(String name, boolean defaultValue) {
		String str = getString(name);
		if(str == null) {
			setBoolean(name, defaultValue);
			return defaultValue;
		}
		else {
			return Boolean.parseBoolean(str);
		}
	}


	public void setBoolean(String name, boolean value) {
		setString(name, Boolean.toString(value));
	}


	/** Get the specified integer property. If the property is empty, set it to the default value.
	 * @param name the name of the property
	 * @param defaultValue the default value to store in the property and return if the property is empty
	 * @return the value of the property with the specified name.
	 */
	public int accessInt(String name, int defaultValue) {
		String str = getString(name);
		if(str == null) {
			setString(name, Integer.toString(defaultValue));
			return defaultValue;
		}
		else {
			return Integer.parseInt(str);
		}
	}


	/** Get the specified integer property. If the property is empty, set it to the default value.
	 * @param name the name of the property
	 * @param defaultValue the default value to store in the property and return if the property is empty
	 * @param min the minimum value of the property
	 * @param max the maximum value of the property
	 * @return the value of the property with the specified name.
	 * If the value is less than min or greater than max, then min or max is returned.
	 */
	public int accessInt(String name, int defaultValue, int min, int max) {
		defaultValue = defaultValue < min ? min : defaultValue > max ? max : defaultValue;
		int result = accessInt(name, defaultValue);
		result = result < min ? min : result > max ? max : result;
		return result;
	}


	public void setInt(String name, int value) {
		setString(name, Integer.toString(value));
	}


	/** Get the specified hexadecimal property. If the property is empty, set it to the default value.
	 * @param name the name of the property
	 * @param defaultValue the default value to store in the property and return if the property is empty
	 * @return the value of the property with the specified name.
	 * If the value is less than min or greater than max, then min or max is returned.
	 */
	public int accessHex(String name, int defaultValue) {
		String str = getString(name);
		if(str == null) {
			String valueStr = "0x"+Integer.toHexString(defaultValue);
			setString(name, valueStr);
			return defaultValue;
		}
		else {
			int result = Integer.parseInt(str.substring(2).toUpperCase(), 16);
			return result;
		}
	}


	/** Get the specified hexadecimal property. If the property is empty, set it to the default value.
	 * @param name the name of the property
	 * @param defaultValue the default value to store in the property and return if the property is empty
	 * @param min the minimum value of the property
	 * @param max the maximum value of the property
	 * @return the value of the property with the specified name.
	 * If the value is less than min or greater than max, then min or max is returned.
	 */
	public int accessHex(String name, int defaultValue, int min, int max) {
		defaultValue = defaultValue < min ? min : defaultValue > max ? max : defaultValue;
		int result = accessHex(name, defaultValue);
		result = result < min ? min : result > max ? max : result;
		return result;
	}


	public void setHex(String name, int value) {
		setString(name, "0x"+Integer.toHexString(value));
	}


	/** Get the specified color property. If the property is empty, set it to the default value.
	 * @param name the name of the property
	 * @param defaultValue the default value to store in the property and return if the property is empty
	 * @return the value of the property with the specified name.
	 * If the value is less than min or greater than max, then min or max is returned.
	 */
	public Color accessColor(String name, Color defaultValue) {
		// & 0xFFFFFF of the RGB integer because the first byte is the opacity which is assumed to be maximum (0xFF)
		return new Color(accessHex(name, defaultValue.getRGB() & 0xFFFFFF,
				Integer.MIN_VALUE, Integer.MAX_VALUE));
	}


	public void setColor(String name, Color value) {
		setHex(name, value.getRGB() & 0xFFFFFF);
	}


	/** Get the specified float property. If the property is empty, set it to the default value.
	 * @param name the name of the property
	 * @param defaultValue the default value to store in the property and return if the property is empty
	 * @return the value of the property with the specified name.
	 * If the value is less than min or greater than max, then min or max is returned.
	 */
	public float accessFloat(String name, float defaultValue) {
		String str = getString(name);
		if(str == null) {
			String valueStr = Float.toString(defaultValue);
			setString(name, valueStr);
			return defaultValue;
		}
		else {
			float result = Float.parseFloat(str);
			return result;
		}
	}


	/** Get the specified float property. If the property is empty, set it to the default value.
	 * @param name the name of the property
	 * @param defaultValue the default value to store in the property and return if the property is empty
	 * @param min the minimum value of the property
	 * @param max the maximum value of the property
	 * @return the value of the property with the specified name.
	 * If the value is less than min or greater than max, then min or max is returned.
	 */
	public float accessFloat(String name, float defaultValue, float min, float max) {
		defaultValue = defaultValue < min ? min : defaultValue > max ? max : defaultValue;
		float result = accessFloat(name, defaultValue);
		result = result < min ? min : result > max ? max : result;
		return result;
	}


	public void setFloat(String name, float value) {
		setString(name, Float.toString(value));
	}

}
