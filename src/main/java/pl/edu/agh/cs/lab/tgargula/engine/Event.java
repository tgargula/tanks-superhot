package pl.edu.agh.cs.lab.tgargula.engine;

import pl.edu.agh.cs.lab.tgargula.basics.HashSetHashMap;
import pl.edu.agh.cs.lab.tgargula.basics.SetMap;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.ITank;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.PlayerTank;

import java.util.List;

public enum Event {
    MOVE, SHOOT, NULL;

    public static Event draw() {
        return Math.random() < 0.5 ? MOVE : SHOOT;
    }

    public static SetMap<Event, ITank> assignEvents(List<ITank> tanks, PlayerTank playerTank, Event playerEvent) {
        SetMap<Event, ITank> events = new HashSetHashMap<>();
        tanks.forEach(tank -> events.put(draw(), tank));
        events.put(playerEvent, playerTank);
        return events;
    }
}
