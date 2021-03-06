package com.footballersAPI.footballlegends.repository;

import com.footballersAPI.footballlegends.entity.Legend;
import com.footballersAPI.footballlegends.exceptions.ResourceIsATeaPot;
import com.footballersAPI.footballlegends.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class LegendRepository {
    // mocking the database
    private List<Legend> legendDatabase = new ArrayList<Legend>(List.of(
            new Legend(1, "Johan Cruyff", "Dutch", 1947),
            new Legend(2, "Diego Armando Maradona", "Argentinian", 1960),
            new Legend(3, "Ronaldinho Gáucho", "Brazilian", 1980)
    ));

    // method for index route
    public List<Legend> findAllLegends() {
        return legendDatabase;
    }

    // method for show route
    public Legend findLegendById(int id) {
        Legend selectedLegendById = legendDatabase.stream()
                .filter((legend) -> legend.getId() == id)
                .findFirst()
                .orElse(null);
        if (selectedLegendById == null) throw new ResourceNotFoundException("Not able to find the legend with id: " + id);
        return selectedLegendById;
    }

    // method for create route
    public Legend addLegend(Legend newLegend) {
        legendDatabase.add(newLegend);
        return newLegend;
    }

    // method for update route
    public Legend editLegendById (int id, Legend updatedLegend) {
        try {
            //finding legend to be updated
            Legend currentLegend = findLegendById(id);
            //finding index of legend to be updated
            int index = legendDatabase.indexOf(currentLegend);
            //setting in dB updatedLegend at selected index
            legendDatabase.set(index, updatedLegend);
        } catch (Exception e) {
            throw new ResourceIsATeaPot("Could not find the Legend to be updated with id: " + id);
        }
        return updatedLegend;
    }

    // method for remove route
    public Legend removeLegendById(int id) {
        Legend legendToBeRemoved = findLegendById(id);
        legendDatabase.remove(legendToBeRemoved);
        return legendToBeRemoved;
    }
}
