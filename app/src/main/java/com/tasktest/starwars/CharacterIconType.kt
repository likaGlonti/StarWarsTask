package com.tasktest.starwars

import com.tasktest.starwars.data.model.SpecieResponse
import com.tasktest.starwars.designsystem.icon.StarWarsIcons

enum class CharacterIconType(val resource: Int) {
    Human(StarWarsIcons.Human), Alien(StarWarsIcons.Alien), Droid(StarWarsIcons.Droid);

    companion object {

        fun getType(specieResponse: SpecieResponse): CharacterIconType {
            return if (specieResponse.name in CharacterIconType.values().map { it.name }) {
                valueOf(specieResponse.name)
            } else Human
        }
    }
}
