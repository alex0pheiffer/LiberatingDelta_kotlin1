package com.example.liberatingdelta_kotlin1.basic_classes

abstract class Chapter(
    val nom: String,
    val coor_PL: Int,
    val region: String,
    val city: String,
    //do i have a place for the individual chapter images? or are we just doing region imgs...
    var drawable_background: Int,
    val phases: Array<phase_objects>
)
{
    /*
    public void setBannedCities(cityPt[] cities) {
        bannedCities = cities;
    }

    public cityPt getBannedCity(int index) {
        return bannedCities[index];
    }

    public boolean isBannedCity(String name) {
        boolean bool= false;
        if (bannedCities!=null) {
            for (int i = 0; i < bannedCities.length; i++) {
                if (name.equals(bannedCities[i].toString())) bool = true;
            }
        }
        return bool;
    }
    */
}