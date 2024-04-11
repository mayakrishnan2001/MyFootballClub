package com.myfc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myfc.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
