import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {

    @Test
    void testNomChien() {

        Chien chien = new Chien("Rex");

        assertEquals("Rex", chien.nom);
    }
}