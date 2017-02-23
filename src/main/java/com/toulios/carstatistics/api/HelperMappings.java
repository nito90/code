package com.toulios.carstatistics.api;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Helper mappings for the valuation of SIPP code and vehicle's score calculation
 */
public interface HelperMappings {

	/**
	 * Map with the points configuration
	 */
	final static Map<String, Integer> POINTS = Collections.unmodifiableMap(Stream.of(
			new AbstractMap.SimpleEntry<>("Manual", 1),
			new AbstractMap.SimpleEntry<>("Automatic", 5),
			new AbstractMap.SimpleEntry<>("AC", 2),
			new AbstractMap.SimpleEntry<>("no AC", 0))
			.collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));

	/**
	 * Map with the fuel configuration
	 */
	final static Map<Character, String> FUEL_MAP = Collections.unmodifiableMap(Stream.of(
			new AbstractMap.SimpleEntry<>('N', "Petrol/no AC"),
			new AbstractMap.SimpleEntry<>('R', "Petrol/AC"))
			.collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));

	/**
	 * Map with the transimission configuration
	 */
	final static Map<Character, String> TRANSIMISSION_MAP = Collections.unmodifiableMap(Stream.of(
			new AbstractMap.SimpleEntry<>('M', "Manual"),
			new AbstractMap.SimpleEntry<>('A', "Automatic"))
			.collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));

	/**
	 * Map with the car-type configuration
	 */
	final static Map<Character, String> CAR_TYPE_MAP = Collections.unmodifiableMap(Stream.of(
			new AbstractMap.SimpleEntry<>('M', "Mini"),
			new AbstractMap.SimpleEntry<>('E', "Economy"),
			new AbstractMap.SimpleEntry<>('C', "Compact"),
			new AbstractMap.SimpleEntry<>('I', "Intermediate"),
			new AbstractMap.SimpleEntry<>('S', "Standard"),
			new AbstractMap.SimpleEntry<>('F', "Full size"),
			new AbstractMap.SimpleEntry<>('P', "Premium"),
			new AbstractMap.SimpleEntry<>('L', "Luxury"),
			new AbstractMap.SimpleEntry<>('X', "Special"))
			.collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));

	/**
	 * Map with the door-type configuration
	 */
	final static Map<Character, String> CAR_TYPE_DOOR_MAP = Collections.unmodifiableMap(Stream.of(
			new AbstractMap.SimpleEntry<>('B', "2 doors"),
			new AbstractMap.SimpleEntry<>('C', "4 doors"),
			new AbstractMap.SimpleEntry<>('D', "5 doors"),
			new AbstractMap.SimpleEntry<>('W', "Estate"),
			new AbstractMap.SimpleEntry<>('T', "Convertible"),
			new AbstractMap.SimpleEntry<>('F', "SUV"),
			new AbstractMap.SimpleEntry<>('P', "Pick up"),
			new AbstractMap.SimpleEntry<>('V', "Passenger Van"),
			new AbstractMap.SimpleEntry<>('X', "Special"))
			.collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));
}
