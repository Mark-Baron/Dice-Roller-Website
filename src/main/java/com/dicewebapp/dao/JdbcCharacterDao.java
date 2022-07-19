package com.dicewebapp.dao;

import com.dicewebapp.model.Character;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcCharacterDao implements CharacterDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCharacterDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public boolean saveCharacter(Character character){
        String sql = "INSERT INTO character(user_id, name, class, lvl, strength_score, dexterity_score, constitution_score, intelligence_score, wisdom_score, charisma_score)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        return jdbcTemplate.update(sql, character.getUserId(), character.getCharacterName(), character.getCharacterClass(), character.getCharacterLvl(), character.getStrengthScore(),
                character.getDexScore(), character.getConScore(), character.getIntScore(), character.getWisScore(), character.getCharScore()) == 1;
    }
}
