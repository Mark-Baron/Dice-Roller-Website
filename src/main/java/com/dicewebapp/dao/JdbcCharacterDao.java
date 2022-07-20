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
        jdbcTemplate.update(sql, character.getUserId(), character.getCharacterName(), character.getCharacterClass(), character.getCharacterLvl(), character.getStrengthScore(), character.getDexScore(), character.getConScore(), character.getIntScore(), character.getWisScore(), character.getCharScore());
        String sqlCharacterId = "SELECT character_id FROM character WHERE name = ?";
        Integer characterId = jdbcTemplate.queryForObject(sqlCharacterId, Integer.class, character.getCharacterName());
        String sqlSkills = "INSERT INTO character_skill (character_id, skill_id)" +
                " VALUES (?, (SELECT skill_id FROM skill WHERE skill_name = ?));";
        for(String skill : character.getCharacterSkills()){
            jdbcTemplate.update(sqlSkills, characterId, skill);
        }
        return true;
    }
}
