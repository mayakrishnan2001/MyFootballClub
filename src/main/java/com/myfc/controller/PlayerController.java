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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.myfc.model.Player;
import com.myfc.service.PlayerService;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
	
	@Autowired
	PlayerService playerService;
	
    
    private static final Logger LOG = LoggerFactory.getLogger(PlayerController.class);
    
    // Retrieve all the Players
	@GetMapping
    public ResponseEntity<List<Player>> getPlayers(){
    	LOG.info("GET /api/players api called for getting all the players");
        List<Player> players = playerService.getPlayers();
        if(players.isEmpty()){
        	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(players,HttpStatus.OK);
	}
	
	// Add new player
    @PostMapping
    public ResponseEntity<Player> createUser(@ModelAttribute Player player, @RequestParam("image") MultipartFile file){
    	LOG.info("POST /api/players endpoint called with user: {}", player);
    	
    	try {
    		if(file.isEmpty()) {
    			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    		}
            player.setPlayerPhoto(file.getBytes());
    		Player newplayer = playerService.addPlayer(player);
    		return new ResponseEntity<>(newplayer, HttpStatus.CREATED);
    	} catch(Exception e) {
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    //Retrieve player details by playernumber
    @GetMapping("/{playernumber}")
    public ResponseEntity<Player> getPlayerById(@PathVariable("playernumber") Integer playernumber) {
        Optional<Player> playerdata = playerService.getPlayerByNumber(playernumber);
        if (playerdata.isPresent()) {
            return new ResponseEntity<>(playerdata.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Update a Player by playernumber
    @PutMapping("/{playernumber}")
    public ResponseEntity<Player> updatePlayer(@PathVariable("playernumber") Integer playernumber, @RequestBody Player player) {
        Player updatePlayer = playerService.updatePlayer(playernumber,player);
        if(updatePlayer!=null) {
        	return new ResponseEntity<>(updatePlayer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Remove a player by playernumber
    @DeleteMapping("/{playernumber}")
    public ResponseEntity<HttpStatus> removePlayer(@PathVariable("playernumber") Integer playernumber) {
        try {
            playerService.removePlayerByNumber(playernumber);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
