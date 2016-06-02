package com.saulo.borges.roihunter.util;

public class Param {
	public final String name;
	public String value;

	public Param(String name) {
		this.name = name;
	}

	/**
	 * Returns a new param with the specified name.
	 *
	 * @param name
	 *            to create Param from
	 * @return new param
	 */
	public static Param name(String name) {
		return new Param(name);
	}

	/**
	 * Sets the value of the Param.
	 *
	 * @param value
	 *            of param
	 * @return this param
	 */
	public Param value(Object value) {
		this.value = value.toString();
		return this;
	}

}
