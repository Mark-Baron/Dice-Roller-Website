package com.dicewebapp.dao;

import com.dicewebapp.model.Character;

import java.util.List;

public interface CharacterDao {

    public boolean saveCharacter(Character character);

    public List<Character> findCharactersByUserId(long userId);
}
