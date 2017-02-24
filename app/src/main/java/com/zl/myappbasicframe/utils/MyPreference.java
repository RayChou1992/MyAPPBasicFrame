package com.zl.myappbasicframe.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import java.util.Set;

public class MyPreference
{
	private static SharedPreferences preferences;
	 
	private static Editor editor;
 
	 
	public static void GetIntance(Context context)
	{
		 preferences = PreferenceManager.getDefaultSharedPreferences(context);
		 editor = preferences.edit();
	}

	/**
	 * 
	 * @param strKey
	 * @param defaultValue
	 * @return String类型的 key对应的value
	 */
	public static String getString(String strKey, final String defaultValue)
	{
		return preferences.getString(strKey,defaultValue);
	}
	/**
	 * 
	 * @param key
	 * @param defValue
	 * @return int类型 key 对应的value 没有则返回默认值 defValue
	 */
	public static int getint(String key, final int defValue)
	{
		return preferences.getInt(key, defValue);
	}
	/**
	 * 
	 * @param key
	 * @param defValue
	 * @return 返回float类型的value
	 */
	public static float getfloat(String key, final float defValue)
	{
		return preferences.getFloat(key, defValue);
	}
	/**
	 * 
	 * @param key
	 * @param defValue
	 * @return 返回long类型的value
	 */
	public static long getlong(String key, final long defValue)
	{
		return preferences.getLong(key, defValue);
		 
	}
	/**
	 * 
	 * @param key
	 * @param defValue
	 * @return 返回boolean 类型的value
	 */
	public static boolean getboolean(String key, final boolean defValue)
	{
		return preferences.getBoolean(key, defValue);
		
	}
 
	/**
	 * 
	 * @param key
	 * @param defValue
	 * @return  返回 Set<String>类型的value (最低支持版本android　SDK 11)
	 */
	public static Set<String> getStrings (String key, final Set<String> defValues)
	{
		return preferences.getStringSet(key, defValues);
	}
	 /**
	  * 设置String类型的value
	  * @param key
	  * @param value
	  */
	public static void commitString(final String key, final String value)
	{
		editor.putString(key, value);
		editor.commit();
	}
	/**
	 * 设置int类型的value
	 * @param key
	 * @param value
	 */
	public static void commitint(final String key, final int value)
	{
		editor.putInt(key, value);
		editor.commit();
	}
	/**
	 * 设置float类型的value
	 * @param key
	 * @param value
	 */
	public static void commitfloat(final String key, final float value)
	{
		editor.putFloat(key, value);
		editor.commit();
	}
	/**
	 * 设置boolean 类型的value
	 * @param key
	 * @param value
	 */
	public static void commitboolean (final String key, final boolean value)
	{
		editor.putBoolean(key, value);
		editor.commit();
	}
	/**
	 * 设置long类型的value
	 * @param key
	 * @param value
	 */
	public static void commitlong(final String key, final long value)
	{
		editor.putLong(key, value);
		editor.commit();
	}
	/**
	 * 设置Set《String》类型的value
	 * @param key
	 * @param values
	 */
	public static void putcommitSet(final String key, final Set<String> values)
	{
		editor.putStringSet(key, values);
		editor.commit();
	}
	/**
	 * 删除key 以及value值
	 * @param key
	 */
	public void removeKey(final String key)
	{
		editor.remove(key);
		editor.commit();
		 
	}
	/**
	 * 删除所有的key
	 */
	public void removeAllKey()
	{
		editor.clear();
		editor.commit();
	}
	
}
