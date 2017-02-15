package Day_8_ClassLoader;


import Day_8_ClassLoader.datamanagement.CustomClassLoader;
import Day_8_ClassLoader.datamanagement.Module;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String pathToBIN = "https://github.com/Admeya/REPO_INNO_GRADLE_13_02/blob/master/src/main/resources/Day_8_ClassLoader/Animal.jar";
        String modulePath = "C:\\Users\\Ирина\\IdeaProjects\\ru.innopolis.admeya\\build\\classes\\main\\Day_8_ClassLoader";

        CustomClassLoader loader = new CustomClassLoader(pathToBIN, ClassLoader.getSystemClassLoader());
        File dir = new File(modulePath);
        String[] modules = dir.list();

        /**
         * Загружаем и исполняем каждый модуль.
         */
        for (String module : modules) {
            try {
                String moduleName = module.split(".class")[0];
                Class clazz = loader.loadClass(moduleName);
                Module execute = (Module) clazz.newInstance();

                execute.load();
                execute.run();
                execute.unload();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

//        Animal animal = new Animal("Хордовые", "Млекопитающие", "Кошачьи", "Британские", "Кошки");
//        SerializationManager helper = new SerializationManager();
//        helper.serialization(xmlPath, animal);
    }


}




