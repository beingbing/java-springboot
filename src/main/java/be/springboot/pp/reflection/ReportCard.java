package be.springboot.pp.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Deserialize {

    // TODO: incomplete
    public Object deserialize(String serializedData, Class c) throws IllegalAccessException, NoSuchFieldException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object object = c.getDeclaredConstructor().newInstance();
        if (serializedData.startsWith("\"{") && serializedData.endsWith("}\"")) {
            serializedData = serializedData.substring(3, serializedData.length() - 2);
        }
        if (serializedData.startsWith("{") && serializedData.endsWith("}")) {
            serializedData = serializedData.substring(1, serializedData.length() - 1);
        }
        String[] fields = serializedData.split(",");
        System.out.println("fields: " + Arrays.toString(fields));

        for (String field : fields) {
            String[] data = field.split(":");
            String fieldName = data[0].trim();
            String val = data[1].trim();
            if (fieldName.startsWith("\"") && fieldName.endsWith("\"")) {
                fieldName = fieldName.substring(1, fieldName.length() - 1);
            }
            if (val.startsWith("\"") && val.endsWith("\"")) {
                val = val.substring(1, val.length() - 1);
            }
            Field f = c.getDeclaredField(fieldName);
            f.setAccessible(true);
            Class<?> fieldType = f.getType();
            if (fieldType == int.class) {
                f.set(object, Integer.parseInt(val));
            } else if (fieldType == double.class) {
                f.set(object, Double.parseDouble(val));
            } else if (fieldType == boolean.class) {
                f.set(object, Boolean.parseBoolean(val));
            } else {
                f.set(object, deserialize(val, fieldType));  // Assume it's a String or other object type
            }
        }

        return object;
    }

}

class Serializer {

    public String serialize(Object object) throws IllegalAccessException {
        String serializedData = "";
        Class c = object.getClass();
        serializedData += "{\"";
        List<Field> allField = getAllFields(c);

        for (Field field : allField) {
            field.setAccessible(true);
            String fieldName = field.getName();
            serializedData +=  fieldName;
            serializedData +=  "\":\"";
            Object val = field.get(object);
            int modifiers = field.getModifiers();
            System.out.println("modifiers: " + modifiers);
            System.out.println("modifiers decoded: " + Modifier.toString(modifiers));
            if (val.getClass().equals(Integer.class) || val.getClass().equals(Double.class)) {
            // if (val.getClass().isPrimitive()) {
                serializedData +=  val;
            } else {
                serializedData += serialize(val);
            }
            serializedData +=  "\",\"";
        }

        serializedData = serializedData.substring(0, serializedData.length() - 2);
        serializedData += "}";
        return serializedData;
    }

    private List<Field> getAllFields(Class c) {
        List<Field> allField = new ArrayList<>(Arrays.asList(c.getDeclaredFields()));
        while (c.getSuperclass() != Object.class) {
            allField.addAll(getAllFields(c.getSuperclass()));
        }
        return allField;
    }
}

class Tester {

    public static void main(String[] args) {
        ReportCard reportCard = new ReportCard(101,
                new ScienceMarks(82, 87, 95, 87.5),
                new ArtsMarks(85, 95, 85.5), 87.5);

        try {
            String serializedData = new Serializer().serialize(reportCard);
            System.out.println(serializedData);
            ReportCard reportCard1 = (ReportCard) new Deserialize().deserialize(serializedData, ReportCard.class);
            System.out.println(reportCard1);
        } catch (IllegalAccessException | NoSuchFieldException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            System.out.println("ex: " + e.getMessage());
            e.printStackTrace();
        }


    }
}

public class ReportCard {

    private final int id;

    private final ScienceMarks scienceMarks;

    private final ArtsMarks artsMarks;

    private final double totalPercentage;

    public ReportCard() {
        this(0, null, null, 0.0);
    }

    public ReportCard(int id, ScienceMarks scienceMarks, ArtsMarks artsMarks, double totalPercentage) {
        this.id = id;
        this.scienceMarks = scienceMarks;
        this.artsMarks = artsMarks;
        this.totalPercentage = totalPercentage;
    }

    public int getId() {
        return id;
    }

    public ScienceMarks getScienceMarks() {
        return scienceMarks;
    }

    public ArtsMarks getArtsMarks() {
        return artsMarks;
    }

    public double getTotalPercentage() {
        return totalPercentage;
    }

}

class ScienceMarks {

    private final int physicsMarks;

    private final int chemistryMarks;

    private final int mathsMarks;

    private final double sciencePercentage;

    public ScienceMarks() {
        this(0, 0, 0, 0.0);
    }

    public ScienceMarks(int physicsMarks, int chemistryMarks, int mathsMarks, double sciencePercentage) {
        this.physicsMarks = physicsMarks;
        this.chemistryMarks = chemistryMarks;
        this.mathsMarks = mathsMarks;
        this.sciencePercentage = sciencePercentage;
    }

}

class ArtsMarks {

    private final int englishMarks;

    private final int socialScienceMarks;

    private final double artsPercentage;

    public ArtsMarks() {
        this(0, 0, 0.0);
    }

    public ArtsMarks(int englishMarks, int socialScienceMarks, double artsPercentage) {
        this.englishMarks = englishMarks;
        this.socialScienceMarks = socialScienceMarks;
        this.artsPercentage = artsPercentage;
    }

}