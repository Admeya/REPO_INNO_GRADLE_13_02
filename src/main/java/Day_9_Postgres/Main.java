package Day_9_Postgres;

/**
 * Created by Ирина on 16.02.2017.
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/Students";
        String login = "postgres";
        String password = "root";

        String tableGroup = "grouppa";
        String tableJournal = "journal";
        String tableLection = "lesson";
        String tableStudent = "student";

        OperationsWithBD obd = new OperationsWithBD(url, login, password);

        //  obd.selectDbAll(tableGroup);
        //  obd.selectDbId(tableGroup, 1);
        //   obd.selectDbName(tableGroup, "STC-04");
        //   obd.updateDb(tableGroup, "name", "STC-04", "STC-05");

//        obd.selectDbAll(tableJournal);
//        obd.selectDbId(tableJournal, "1");
//        obd.selectDbName(tableJournal, "STC-04");
//        obd.updateDb(tableJournal, "name", "STC-04", "STC-05");
//
//        obd.selectDbAll(tableLection);
//        obd.selectDbId(tableLection, "1");
//        obd.selectDbName(tableLection, "OOP");
//        obd.updateDb(tableLection, "name", "OOP", "Object Oriented Programming ");
//
//        obd.selectDbAll(tableStudent);
//        obd.selectDbId(tableStudent, "1");
//        obd.selectDbName(tableStudent, "STC-04");
//        obd.updateDb(tableStudent, "name", "Buchin", "Buchin Y.");
    }
}
