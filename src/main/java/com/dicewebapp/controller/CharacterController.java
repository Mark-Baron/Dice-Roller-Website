package com.dicewebapp.controller;

import com.dicewebapp.dao.UserDao;
import com.dicewebapp.model.Character;
import com.dicewebapp.dao.CharacterDao;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class CharacterController {

    private CharacterDao characterDao;
    private UserDao userDao;

    public CharacterController(CharacterDao characterDao, UserDao userDao){
        this.userDao = userDao;
        this.characterDao = characterDao;
    }

    @PostMapping(path="/character/create")
    public Character save(@RequestBody Character character, Principal principal){
        String username = principal.getName();
        Long userId = userDao.findByUsername(username).getId();
        character.setUserId(userId);
        characterDao.saveCharacter(character);
        return character;
    }

    @GetMapping(path="/characters")
    public List<Character> getCharacters(Principal principal){
        String username = principal.getName();
        long userId = userDao.findByUsername(username).getId();
        return characterDao.findCharactersByUserId(userId);
    }

    @GetMapping(path="/characters/{characterId}")
    public Character selectCharacter(@PathVariable long characterId){
        return characterDao.findCharacterByCharacterId(characterId);
    }
}
