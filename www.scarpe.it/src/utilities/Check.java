package utilities;

public class Check {

	/**
	 * Checks if the given parameter is valid or not. A parameter is assumed to
	 * be valid if it differs from {@code null} and from the empty string.
	 * 
	 * @param parameter The parameter to be checked out.
	 * @return true if the parameter is valid, false otherwise.
	 */
	public static boolean isValid(String parameter) {
		return parameter != null && !parameter.trim().isEmpty();
	}
}
