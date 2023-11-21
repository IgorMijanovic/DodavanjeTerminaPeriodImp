public class Main {

    public static void main(String[] args) {

        PeriodImplementacija p = new PeriodImplementacija();
        p.initRaspored();

        //p.dodajNoviTermin("s1","nedelja","13","15","01.10.2023","25.10.2023","Profesor:Igor Mijanovic","Tip:predavanje");

        //p.dodajNoviTermin("s2","sreda","13","15","01.10.2023","25.10.2023","Tip:predavanje");

        //p.exportCSV("src/main/resources/Desktop.csv","s1");

        //p.exportJson("C:\\Users\\aleks\\Desktop\\DodavanjeTerminaPeriodImp\\src\\main\\resources\\proba.json","s1");

        //p.exportPDF("C:\\Users\\aleks\\Desktop\\DodavanjeTerminaPeriodImp\\src\\main\\resources\\abc.pdf","s1");

        //p.importovanjeJson("C:\\\\Users\\\\aleks\\\\Desktop\\\\DodavanjeTerminaPeriodImp\\\\src\\\\main\\\\resources\\\\proba.json");

        //p.importovanje("src/main/resources/Desktop.csv");

        //p.dodajNoviTermin("s1","nedelja","14","18","01.10.2023","25.10.2023","Profesor:Igor Mijanovic","Projektor:DA","Tip:predavanje");

        //p.dodajNoviTermin("s1","sreda","14","18","08.11.2023","08.11.2023");

        //System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        //p.dodajNoviTermin("raf2","nedelja","12","14","01.10.2023","01.10.2023","Tip:Vezbe","Predmet:DS");

        System.out.println(p.getRaspored().size());
        for (Termin t: p.getRaspored()){
            System.out.println(t);
            System.out.println(t.getTipZakazivanja());
        }
        System.out.println("Posle brisanjaaaaaaaaaaaaa");
        //p.brisanjeTermina("01.10.2023","17.10.2023");
        //p.brisanjeTermina("01.10.2023","25.10.2023","13","15");
        //p.brisanjeTermina("01.10.2023","25.10.2023","nedelja");
        //p.brisanjeTermina("01.10.2023","25.10.2023","13","15","nedelja");
        //p.brisanjeTermina("08.11.2023","08.11.2023","14","18");
        //p.brisanjeTermina("08.11.2023","08.11.2023","sreda");
        //p.brisanjeTermina("08.11.2023","08.11.2023","14","18","sreda");

        System.out.println("Posle premestanjaaaaaaaaaaa");

        //p.premestanjeTermina("s1","nedelja","13","15","01.10.2023","25.10.2023","raf2","sreda","17","18","27.08.2023","15.09.2023");

        //p.premestanjeTermina();



        System.out.println(p.getRaspored().size());
        for (Termin t: p.getRaspored()){
            System.out.println(t);
        }
    }
}
