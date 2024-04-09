package com.myfc.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myfc.model.Player;
import com.myfc.repo.PlayerRepository;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
	
	@Autowired
    private PlayerRepository playerRepository;
    
    private static final Logger LOG = LoggerFactory.getLogger(PlayerController.class);
    
    // Retrieve all the Players
	@GetMapping
    public ResponseEntity<List<Player>> getPlayers(){
    	LOG.info("GET /api/players api called for getting all the players");
        List<Player> players = playerRepository.findAll();
        if(players.isEmpty()){
        	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(players,HttpStatus.OK);
	}
	
	// Add new player
    @PostMapping
    public ResponseEntity<Player> createUser(@RequestBody Player player){
    	LOG.info("POST /api/players endpoint called with user: {}", player);
    	try {
    		Player newplayer = playerRepository.save(player);
    		return new ResponseEntity<>(newplayer, HttpStatus.CREATED);
    	} catch(Exception e) {
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    //Retrieve player details by pnumber
    @GetMapping("/{pnumber}")
    public ResponseEntity<Player> getPlayerById(@PathVariable("pnumber") Integer pnumber) {
        Optional<Player> playerdata = playerRepository.findById(pnumber);
        if (playerdata.isPresent()) {
            return new ResponseEntity<>(playerdata.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Update a Player by pnumber
    @PutMapping("/{pnumber}")
    public ResponseEntity<Player> updatePlayer(@PathVariable("pnumber") Integer pnumber, @RequestBody Player Player) {
        Optional<Player> playerData = playerRepository.findById(pnumber);
        if (playerData.isPresent()) {
            Player updatedPlayer = playerData.get();
            updatedPlayer.setAge(Player.getAge());
            updatedPlayer.setPlayerName(Player.getPlayerName());
            updatedPlayer.setPlayerNumber(Player.getPlayerNumber());
            updatedPlayer.setPosition(Player.getPosition());
            return new ResponseEntity<>(playerRepository.save(updatedPlayer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Remove a player by pnumber
    @DeleteMapping("/{pnumber}")
    public ResponseEntity<HttpStatus> deleteNote(@PathVariable("pnumber") Integer pnumber) {
        try {
            playerRepository.deleteById(pnumber);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
