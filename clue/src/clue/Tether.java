package clue;

public class Tether {
	
	private int person = 0;
	private int weapon = 0;
	private int room = 0;
	
	public Tether(int person, int weapon, int room) {
		if(person != 0) {
			setPerson(person);
		}
		if(weapon != 0) {
			setWeapon(weapon);
		}
		if(room != 0) {
			setRoom(room);
		}
	}

	public int getPerson() {
		return person;
	}

	public void setPerson(int person) {
		this.person = person;
	}

	public int getWeapon() {
		return weapon;
	}

	public void setWeapon(int weapon) {
		this.weapon = weapon;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}
	
}