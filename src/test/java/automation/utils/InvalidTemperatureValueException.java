package automation.utils;

public class InvalidTemperatureValueException extends Exception {

	String message;
	public InvalidTemperatureValueException(){
		message="Diffrence in temperature value of UI and API response is exceeding the given offset";
	}
	
	public String toString(){
		return getClass().getName()+": "+ message;
	}
}
