package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record Car(int year, String color, List<Wheel> wheels, Engine engine) {
    public Car(int year, String color, List<Wheel> wheels, Engine engine) {
        this.year = year;
        this.color = color;

        // Deep copy коліс
        List<Wheel> copy = new ArrayList<>();
        for (Wheel w : wheels) {
            copy.add(new Wheel(w.getRadius()));
        }
        this.wheels = copy;

        // Deep copy двигуна
        this.engine = engine == null ? null : new Engine(engine.getHorsePower(), engine.getManufacturer());
    }

    // Повертаємо mutable копію списку, щоб зовнішні add/remove не впливали на Car
    @Override
    public List<Wheel> wheels() {
        List<Wheel> cloned = new ArrayList<>();
        for (Wheel w : wheels) {
            cloned.add(new Wheel(w.getRadius()));
        }
        return cloned;
    }

    @Override
    public Engine engine() {
        return engine == null ? null : new Engine(engine.getHorsePower(), engine.getManufacturer());
    }

    public Car changeColor(String newColor) {
        return new Car(year, newColor, wheels, engine);
    }

    public Car changeEngine(Engine newEngine) {
        return new Car(year, color, wheels, newEngine);
    }

    public Car addWheel(Wheel newWheel) {
        List<Wheel> newWheels = new ArrayList<>();
        for (Wheel w : wheels) {
            newWheels.add(new Wheel(w.getRadius()));
        }
        newWheels.add(new Wheel(newWheel.getRadius()));
        return new Car(year, color, newWheels, engine);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        return year == car.year &&
                Objects.equals(color, car.color) &&
                Objects.equals(wheels, car.wheels) &&
                Objects.equals(engine, car.engine);
    }

    @Override
    public String toString() {
        return "Car{" +
                "year=" + year +
                ", color='" + color + '\'' +
                ", wheels=" + wheels +
                ", engine=" + engine +
                '}';
    }
}










