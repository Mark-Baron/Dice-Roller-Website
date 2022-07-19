package com.dicewebapp.model;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class Character {

    public long characterId;
    @NotEmpty
    public long userId;
    @NotEmpty
    public String characterName;
    @NotEmpty
    public String characterClass;
    @NotEmpty
    public int characterLvl;
    @NotEmpty
    public int strengthScore;
    @NotEmpty
    public int dexScore;
    @NotEmpty
    public int conScore;
    @NotEmpty
    public int intScore;
    @NotEmpty
    public int wisScore;
    @NotEmpty
    public int charScore;
    public List<String> characterSkills;

    public long getCharacterId() {
        return characterId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setCharacterId(long characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public int getCharacterLvl() {
        return characterLvl;
    }

    public void setCharacterLvl(int characterLvl) {
        this.characterLvl = characterLvl;
    }

    public int getStrengthScore() {
        return strengthScore;
    }

    public void setStrengthScore(int strengthScore) {
        this.strengthScore = strengthScore;
    }

    public int getDexScore() {
        return dexScore;
    }

    public void setDexScore(int dexScore) {
        this.dexScore = dexScore;
    }

    public int getConScore() {
        return conScore;
    }

    public void setConScore(int conScore) {
        this.conScore = conScore;
    }

    public int getIntScore() {
        return intScore;
    }

    public void setIntScore(int intScore) {
        this.intScore = intScore;
    }

    public int getWisScore() {
        return wisScore;
    }

    public void setWisScore(int wisScore) {
        this.wisScore = wisScore;
    }

    public int getCharScore() {
        return charScore;
    }

    public void setCharScore(int charScore) {
        this.charScore = charScore;
    }

    public List<String> getCharacterSkills() {
        return characterSkills;
    }

    public void setCharacterSkills(List<String> characterSkills) {
        this.characterSkills = characterSkills;
    }
}
