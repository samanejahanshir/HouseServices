package service;

import model.Expert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;

public class ExpertServiceTest {
   static ExpertService expertService;

    @BeforeAll
    static void init(){
        expertService = new ExpertService();
    }

    @Test
    void getExpert_SaveToDb() {
        // ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        File file = new File("/res/unknown.png");
        byte[] imageFile = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(imageFile);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Expert expert = Expert.ExpertBuilder.anExpert()
                .withFirstName("expert")
                .withLastName("Efamily")
                .withPassword("a1234S454")
                .withEmail("expert@email.com")
                .withImage(imageFile)
                .build();

        expertService.saveExpert(expert);
    }

    @Test
    void getExpertDuplicate_SaveToDb_ThrowException() {
        // ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
       /* File file = new File("/res/unknown.png");
        byte[] imageFile = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(imageFile);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        Expert expert = Expert.ExpertBuilder.anExpert()
                .withFirstName("expert")
                .withLastName("Efamily")
                .withPassword("a1234S45874")
                .withEmail("expert@email.com")
                //.withImage(imageFile)
                .build();

        RuntimeException exp = Assertions.assertThrows(RuntimeException.class, () ->
                expertService.saveExpert(expert));
        System.out.println(exp.getMessage());
        Assertions.assertEquals("this expert by this email is exist .", exp.getMessage());
    }

    @Test
    void getExpert_ByEmailAndPass() {
        Expert expert = expertService.getExpertByEmailAndPass("expert@email.com", "a1234S454");
        Assertions.assertNotNull(expert);

    }

    @Test
    void getNewPass_UpdateExpertPass() {
        int id = expertService.updatePassword("expert@email.com", "56A56745dd66");
        Assertions.assertEquals(1, id);
    }

    @Test
    void getListOrderTest() {
        Expert expert = expertService.getExpertByEmail("expert@email.com");
        System.out.println(expertService.getListOrders(expert).size());
    }

    @Test
    void SetImageTest(){
        File file = new File("/res/unknown.png");
        expertService.setImage(file,"expert@email.com");
    }
}
