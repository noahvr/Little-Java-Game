package field;

public class Key {
	private int priority;
	private char key;
	
	public Key(int priority, char key) {
		this.setPriority(priority);
		this.setKey(key);
		
		
	}

	public char getKey() {
		return key;
	}

	public void setKey(char key) {
		this.key = key;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	

}
