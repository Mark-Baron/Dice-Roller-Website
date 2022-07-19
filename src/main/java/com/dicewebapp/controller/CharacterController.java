package com.dicewebapp.controller;

import com.dicewebapp.model.Character;
import com.dicewebapp.dao.CharacterDao;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterController {

    private CharacterDao characterDao;

    public CharacterController(CharacterDao characterDao){
        this.characterDao = characterDao;
    }

    @PostMapping(path="/character/create")
    public Character save(@RequestBody Character character){
        characterDao.saveCharacter(character);
        return character;
    }

}
