package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record Car(int year, String color, List<Wheel> wheels, Engine engine) {
    public Car(int year, String color, List<Wheel> wheels, Engine engine) {
        if (wheels == null) {
            throw new NullPointerException("Wheels cannot be null");
        }
        this.year = year;
        this.color = color;

        // deep copy wheels
        List<Wheel> copy = new ArrayList<>();
        for (Wheel w : wheels) {
            copy.add(w.clone());
        }
        this.wheels = new ArrayList<>(copy); // внутрішня копія

        // deep copy engine (може бути null)
        this.engine = (engine == null) ? null : engine.clone();
    }

    @Override
    public List<Wheel> wheels() {
        List<Wheel> copy = new ArrayList<>();
        for (Wheel w : wheels) {
            copy.add(w.clone());
        }
        return copy; // повертаємо копію, яка mutable
    }

    @Override
    public Engine engine() {
        return (engine == null) ? null : engine.clone();
    }

    public Car changeEngine(Engine newEngine) {
        return new Car(year, color, wheels, newEngine);
    }

    public Car changeColor(String newColor) {
        return new Car(year, newColor, wheels, engine);
    }

    public Car addWheel(Wheel newWheel) {
        List<Wheel> copy = new ArrayList<>();
        for (Wheel w : wheels) {
            copy.add(w.clone());
        }
        copy.add(newWheel.clone());
        return new Car(year, color, copy, engine);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        return year == car.year
                && Objects.equals(color, car.color)
                && Objects.equals(wheels, car.wheels)
                && Objects.equals(engine, car.engine);
    }

}












