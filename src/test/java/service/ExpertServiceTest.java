package service;

import model.Expert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;

public class ExpertServiceTest {
    @Test
    void getExpert_SaveToDb() {
        // ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        File file = new File("/unknown.png");
        byte[] imageFile = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(imageFile);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Expert expert = Expert.ExpertBuilder.anExpert()
                .withFirstName("customer")
                .withLastName("Cfamily")
                .withPassword("a1234S454")
                .withEmail("customer@email.com")
                .withImage(imageFile)
                .build();

        ExpertService expertService = new ExpertService();
        expertService.saveExpert(expert);
    }

    @Test
    void getExpertDuplicate_SaveToDb_ThrowException() {
        // ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        File file = new File("/unknown.png");
        byte[] imageFile = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(imageFile);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Expert expert = Expert.ExpertBuilder.anExpert()
                .withFirstName("customer")
                .withLastName("Cfamily")
                .withPassword("a1234S454")
                .withEmail("customer@email.com")
                .withImage(imageFile)
                .build();

        ExpertService expertService = new ExpertService();
        RuntimeException exp = Assertions.assertThrows(RuntimeException.class, () ->
                expertService.saveExpert(expert));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("this expert by this email is exist .", exp.getMessage());
    }

    @Test
    void getExpert_ByEmailAndPass() {
        ExpertService expertService = new ExpertService();
        Expert expert = expertService.getExpertByEmail("customer@email.com", "a1234S454");
        Assertions.assertNotNull(expert);

    }

    @Test
    void getNewPass_UpdateExpertPass() {
        ExpertService expertService = new ExpertService();
        int id = expertService.updatePassword("customer@email.com", "56A56745dd66");
        Assertions.assertEquals(1, id);
    }
}
