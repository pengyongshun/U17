package com.example.u17.base_uitls;

import java.util.List;
import java.util.Map;

/**
 * @Author:king1033
 * @Desc:
 * @Time:2016/9/16
 */
public class EmptyUtils {


	public static boolean emptyOfString(String params)
	{
		if(params!=null && params.length()>0)
		{
			return false;
		}
		return true;
	}


	public static boolean emptyOfList(List<?> params)
	{
		if(params!=null && params.size()>0)
		{
			return false;
		}
		return true;
	}


	public static boolean emptyOfArray(Object params[])
	{
		if(params!=null && params.length>0)
		{
			return false;
		}
		return true;
	}


	public static boolean emptyOfObject(Object params)
	{
		if(params!=null)
		{
			return false;
		}
		return true;
	}


	public static <K,V> boolean emptyOfMap(Map<K,V> map)
	{
		if(map!=null && map.size()>0)
		{
			return false;
		}
		return true;
	}
}
