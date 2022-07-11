package com.dicewebapp;

import com.dicewebapp.dao.CharacterDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private CharacterDao characterDao;

    public Controller(CharacterDao characterDao){
        this.characterDao = characterDao;
    }

    @PostMapping(path="/character")
    public Character save(@RequestBody Character character){
        characterDao.saveCharacter(character);
        return character;
    }

}
