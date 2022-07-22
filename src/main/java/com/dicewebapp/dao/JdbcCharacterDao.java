package com.dicewebapp.dao;

import com.dicewebapp.model.Character;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCharacterDao implements CharacterDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCharacterDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}


    public List<Character> findCharactersByUserId(long userId){
        List<Character> characters = new ArrayList<>();
        String sql = "SELECT user_id, character_id, name, class, lvl, strength_score, dexterity_score, constitution_score, intelligence_score, wisdom_score, charisma_score FROM character" +
                " WHERE user_id = ?";
        SqlRowSet charactersStats = jdbcTemplate.queryForRowSet(sql, userId);
        String sqlSkills = "SELECT skill_name FROM skill AS s" +
                " JOIN character_skill AS cs ON s.skill_id = cs.skill_id" +
                " JOIN character AS c ON cs.character_id = c.character_id" +
                " WHERE cs.character_id = ?";
        while(charactersStats.next()){
            SqlRowSet skillSet = jdbcTemplate.queryForRowSet(sqlSkills, charactersStats.getInt("character_id"));
            Character character = mapRowToCharacter(charactersStats);
            addSkillsToCharacter(character, skillSet);
            characters.add(character);
        }

        return characters;
    }

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

    private Character mapRowToCharacter(SqlRowSet sqlRowSet){
        Character character = new Character();
        character.setUserId(sqlRowSet.getInt("user_id"));
        character.setCharacterId(sqlRowSet.getInt("character_id"));
        character.setCharacterClass(sqlRowSet.getString("class"));
        character.setCharacterLvl(sqlRowSet.getInt("lvl"));
        character.setCharacterName(sqlRowSet.getString("name"));
        character.setStrengthScore(sqlRowSet.getInt("strength_score"));
        character.setDexScore(sqlRowSet.getInt("dexterity_score"));
        character.setConScore(sqlRowSet.getInt("constitution_score"));
        character.setIntScore(sqlRowSet.getInt("intelligence_score"));
        character.setWisScore(sqlRowSet.getInt("wisdom_score"));
        character.setCharScore(sqlRowSet.getInt("charisma_score"));

        return character;
    }

    private Character addSkillsToCharacter(Character character, SqlRowSet skillSet){
        List<String> skillList = new ArrayList<>();
        while(skillSet.next()){
            skillList.add(skillSet.getString("skill_name"));
        }
        String[] skillArray = new String[skillList.size()];
        for(int i = 0; i < skillList.size(); i++)
        {
            skillArray[i] = skillList.get(i);
        }
        character.setCharacterSkills(skillArray);

        return character;
    }
}
