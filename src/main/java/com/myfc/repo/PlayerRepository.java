package com.myfc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.myfc.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer>{

}
