///// Class that contains data of a single pin
/// is used for the accability.
// is used in the array of 6 pins.
package pavel.game;
import java.awt.Color;
import java.awt.Point;

public class PinData {
	private final int dataTypes = 6;
	private CirclePin pinObject;
	private Color pinColor;
	private String pinColorNames;
	private boolean PinOn;
	private boolean PinRule;
	private Point pinLocation;
	PinData (){
				
	}
	public void addCirclePlin (CirclePin p) {
		setPinObject(p);
	}
	public void setPinLocation(Point pinLocation) {
		this.pinLocation = pinLocation;
	}
	public Point getPinLocation() {
		return pinLocation;
	}
	public void setPinOn(boolean pinOn) {
		PinOn = pinOn;
	}
	public boolean isPinOn() {
		return PinOn;
	}
	public void setPinColor(Color pinColor) {
		this.pinColor = pinColor;
	}
	public Color getPinColor() {
		return pinColor;
	}
	public void setPinObject(CirclePin pinObject) {
		this.pinObject = pinObject;
	}
	public CirclePin getPinObject() {
		return pinObject;
	}
	public void setPinColorNames(String pinColorNames) {
		this.pinColorNames = pinColorNames;
	}
	public String getPinColorNames() {
		return pinColorNames;
	}

	public int getDataTypes() {
		return dataTypes;
	}
	public void setPinRule(boolean pinRule) {
		PinRule = pinRule;
	}
	public boolean isPinRule() {
		return PinRule;
	}
}
