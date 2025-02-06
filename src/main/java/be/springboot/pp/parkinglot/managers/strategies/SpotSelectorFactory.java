package be.springboot.pp.parkinglot.managers.strategies;

import be.springboot.pp.parkinglot.dtos.EntryPoint;
import be.springboot.pp.parkinglot.enums.SpotSelectionType;
import be.springboot.pp.parkinglot.managers.strategies.impl.RandomSpot;
import be.springboot.pp.parkinglot.managers.strategies.impl.SpotNearEntrance;
import be.springboot.pp.parkinglot.managers.strategies.impl.SpotNearExit;

public class SpotSelectorFactory {

    private SpotSelectorFactory() {}

    public static SpotSelectionStrategy getStrategy(SpotSelectionType strategy, EntryPoint entryPoint) {
        return switch (strategy) {
            case NEAR_ENTRANCE -> new SpotNearEntrance(entryPoint);
            case NEAR_EXIT -> new SpotNearExit(entryPoint);
            case RANDOM -> new RandomSpot(entryPoint);
        };
    }
}
