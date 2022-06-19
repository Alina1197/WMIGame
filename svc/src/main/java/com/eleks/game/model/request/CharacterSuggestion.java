package com.eleks.game.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class CharacterSuggestion
{
    @NotNull
    @Size(min = 1, max = 50, message = "nickname length must be between {min} and {max}!")
    private String nickname;
    @NotNull
    @Size(min = 1, max = 50, message = "character length must be between {min} and {max}!")
    private String character;

    public CharacterSuggestion(String nickname, String character)
    {
        this.nickname = nickname;
        this.character = character;
    }

    public CharacterSuggestion()
    {
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getCharacter()
    {
        return character;
    }

    public void setCharacter(String character)
    {
        this.character = character;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        CharacterSuggestion that = (CharacterSuggestion) o;
        return Objects.equals(nickname, that.nickname) && Objects.equals(character, that.character);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(nickname, character);
    }

    @Override
    public String toString()
    {
        return "CharacterSuggestion{" +
            "nickname='" + nickname + '\'' +
            ", character='" + character + '\'' +
            '}';
    }
}
