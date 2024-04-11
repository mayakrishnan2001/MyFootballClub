package com.myfc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class Player {
	@Id
	private Integer playerNumber;
	private String playerName;
	private int age;
	private String position;

	@Lob
	private byte[] playerPhoto;

	public byte[] getPlayerPhoto() {
		return playerPhoto;
	}

	public void setPlayerPhoto(byte[] playerPhoto) {
		this.playerPhoto = playerPhoto;
	}

	public Integer getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(Integer playerNumber) {
		this.playerNumber = playerNumber;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
