package com.myfc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfc.model.Player;
import com.myfc.repo.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	public List<Player> getPlayers() {
		List<Player> players = playerRepository.findAll();
		return players;
	}

	public Player addPlayer(Player player) {
		Player newPlayer = playerRepository.save(player);
		return newPlayer;
	}

	public Optional<Player> getPlayerByNumber(Integer playernumber) {
		Optional<Player> playerdata = playerRepository.findById(playernumber);
		return playerdata;
	}

	public void removePlayerByNumber(Integer playernumber) throws Exception {
		playerRepository.deleteById(playernumber);
	}

	public Player updatePlayer(Integer playernumber, Player player) {
		Optional<Player> playerData = playerRepository.findById(playernumber);
		if (playerData.isPresent()) {
			Player updatedPlayer = playerData.get();
			updatedPlayer.setAge(player.getAge());
			updatedPlayer.setPlayerName(player.getPlayerName());
			updatedPlayer.setPlayerNumber(player.getPlayerNumber());
			updatedPlayer.setPosition(player.getPosition());
			playerRepository.save(updatedPlayer);
			return updatedPlayer;
		}
		return null;
	}

}
