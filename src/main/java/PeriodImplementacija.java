import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PeriodImplementacija extends ObradaTermina{


    private String prostor;
    private String dann;

    private int pocetakSati;

    private int krajSati;

    private LocalDateTime pocetakDan;

    private LocalDateTime krajDan;

    private List<Termin> raspored = getRaspored();

    private DayOfWeek danEng;




    @Override
    public boolean dodajNoviTermin(String... strings) {
        List<String> stringovi = new ArrayList<>();
        for (String i : strings) {
            stringovi.add(i);
        }

        prostor = stringovi.get(0);

        dann = stringovi.get(1);



        switch (dann){
            case "ponedeljak":
                danEng = DayOfWeek.MONDAY;
                break;
            case "utorak":
                danEng = DayOfWeek.TUESDAY;
                break;
            case "sreda":
                danEng = DayOfWeek.WEDNESDAY;
                break;
            case "cetvrtak":
                danEng = DayOfWeek.THURSDAY;
                break;
            case "petak":
                danEng = DayOfWeek.FRIDAY;
                break;
            case "subota":
                danEng = DayOfWeek.SATURDAY;
                break;
            case "nedelja":
                danEng = DayOfWeek.SUNDAY;
                break;
        }

        pocetakSati = Integer.parseInt(stringovi.get(2));

        krajSati = Integer.parseInt(stringovi.get(3));


        String datum = stringovi.get(4);

        int dan,mesec,godina;

        dan = Integer.parseInt(String.valueOf(datum.charAt(0)))*10 + Integer.parseInt(String.valueOf(datum.charAt(1)));

        mesec = Integer.parseInt(String.valueOf(datum.charAt(3)))*10 + Integer.parseInt(String.valueOf(datum.charAt(4)));

        godina = Integer.parseInt(String.valueOf(datum.charAt(6)))*1000 + Integer.parseInt(String.valueOf(datum.charAt(7)))*100 + Integer.parseInt(String.valueOf(datum.charAt(8)))*10 + Integer.parseInt(String.valueOf(datum.charAt(9)));

        pocetakDan = LocalDateTime.of(godina,mesec,dan,0,0);
        //System.out.println(dan);
       // System.out.println(mesec);
        //System.out.println(godina);


        datum = stringovi.get(5);

        dan = Integer.parseInt(String.valueOf(datum.charAt(0)))*10 + Integer.parseInt(String.valueOf(datum.charAt(1)));

        mesec = Integer.parseInt(String.valueOf(datum.charAt(3)))*10 + Integer.parseInt(String.valueOf(datum.charAt(4)));

        godina = Integer.parseInt(String.valueOf(datum.charAt(6)))*1000 + Integer.parseInt(String.valueOf(datum.charAt(7)))*100 + Integer.parseInt(String.valueOf(datum.charAt(8)))*10 + Integer.parseInt(String.valueOf(datum.charAt(9)));

        krajDan = LocalDateTime.of(godina,mesec,dan,0,0);

        for (LocalDateTime datumm = pocetakDan; datumm.isBefore(krajDan); datumm = datumm.plusDays(1)){
                if(datumm.getDayOfWeek() == danEng){
                    LocalDateTime krajj = LocalDateTime.of(datumm.getYear(),datumm.getMonth(),datumm.getDayOfMonth(),krajSati,0);
                    LocalDateTime pocetakk = LocalDateTime.of(datumm.getYear(),datumm.getMonth(),datumm.getDayOfMonth(),pocetakSati,0);

                    //System.out.println(pocetakk);
                    //System.out.println(krajj);
                    Termin termin = new Termin(null,pocetakk,krajj);
                    raspored.add(termin);
                }

        }

        //System.out.println(prostor);
       // System.out.println(danEng);
       // System.out.println(pocetakSati);
       // System.out.println(krajSati);
       // System.out.println(pocetakDan);
      //  System.out.println(krajDan);

        //System.out.println(pocetakDan.getDayOfWeek());

        for (Termin t : raspored){
            System.out.println(t);
        }

        return true;
    }




    @Override
    public boolean brisanjeTermina(String... strings) {
        return false;
    }

    @Override
    public boolean premestanjeTermina(String... strings) {
        return false;
    }

    @Override
    public boolean dodavanjeProstorija(String... strings) {
        return false;
    }
}
