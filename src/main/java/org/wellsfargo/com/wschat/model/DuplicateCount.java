package org.wellsfargo.com.wschat.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

public class DuplicateCount {
	public static void main(String[] args) {
		String hello = "hellp";
		JSONObject jObject = new JSONObject(hello);
		System.out.println(jObject);
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(1);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(2);
		list.add(3);
		list.add(5);
		Set<Integer> uniqueSet = new HashSet<Integer>(list);
		for (Integer temp : uniqueSet) {
			System.out.println(temp + ": " + Collections.frequency(list, temp));
		}

		Map<Integer, Integer> mp = new HashMap<Integer, Integer>();
		for (Integer temp : list) {
			Integer count = mp.get(temp);
			mp.put(temp, (count == null) ? 1 : count + 1);
		}
		printMap(mp);
	}

	static void printMap(Map<Integer, Integer> mp) {
		for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : "
					+ entry.getValue());
		}

	}
}
