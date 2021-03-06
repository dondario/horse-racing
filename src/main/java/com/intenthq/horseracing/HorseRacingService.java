package com.intenthq.horseracing;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.intenthq.horseracing.exception.NoValidHorsesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HorseRacingService {
    private InputProcessor inputProcessor;
    private OutputWriter outputWriter;

    @Autowired
    public HorseRacingService(final InputProcessor inputProcessor,
                              final OutputWriter outputWriter) {

        this.inputProcessor = inputProcessor;
        this.outputWriter = outputWriter;
    }

    public String processRace(final String raceInput) throws NoValidHorsesException {
        final Map<Integer, Horse> horses = inputProcessor.process(raceInput);
        List<Horse> sortedHorseList = sortHorses(horses.values());
        return outputWriter.print(sortedHorseList);
    }

    private List<Horse> sortHorses(final Collection<Horse> horses) {
        final List<Horse> horseList = Lists.newArrayList(horses);
        Collections.sort(horseList, byDistanceDescending());

        return horseList;
    }

    private Ordering<Horse> byDistanceDescending() {
        return new Ordering<Horse>() {
            @Override
            public int compare(final Horse left, final Horse right) {
                return right.getYardsCovered() - left.getYardsCovered();
            }
        };
    }
}
