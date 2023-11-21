import java.io.BufferedReader;
import java.io.FileReader;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;

public class PeriodImplementacija extends ObradaTermina{


    private String soba;
    private String dann;

    private int pocetakSati;

    private int krajSati;

    private LocalDateTime pocetakDan;

    private LocalDateTime krajDan;

    //private List<Termin> raspored = getRaspored();

    private DayOfWeek danEng;






    @Override
    public boolean dodajNoviTermin(String... strings) {
        List<String> stringovi = new ArrayList<>();
        for (String i : strings) {
            stringovi.add(i);
        }

        soba = stringovi.get(0);
        int i = -1;
        boolean flag2 = false;
        for(Prostor p : getProstori()){
            i++;
            if(p.getIme().equals(soba)){
                flag2 = true;
                break;
            }

        }
        Prostor prostor;
        if (flag2) {
            prostor = getProstori().get(i);
        }else {
            System.out.println("prostor ne postoji");
            return false;
        }

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

        Map<String,String> dodaci = new HashMap<>();

        for (int h = 6 ; h < stringovi.size(); h++){
            String[] splitovi = stringovi.get(h).split(":");
            dodaci.put(splitovi[0],splitovi[1]);
            //System.out.println(dodaci);
        }

        if(pocetakDan.getYear() == krajDan.getYear() && pocetakDan.getMonth() == krajDan.getMonth() && pocetakDan.getDayOfMonth()==krajDan.getDayOfMonth()){
            if(pocetakDan.getDayOfWeek() == danEng){
                LocalDateTime krajj = LocalDateTime.of(pocetakDan.getYear(),pocetakDan.getMonth(),pocetakDan.getDayOfMonth(),krajSati,0);
                LocalDateTime pocetakk = LocalDateTime.of(pocetakDan.getYear(),pocetakDan.getMonth(),pocetakDan.getDayOfMonth(),pocetakSati,0);

                //System.out.println(pocetakk);
                //System.out.println(krajj);
                Termin termin = new Termin(prostor,pocetakk,krajj,dodaci);
                termin.setTipZakazivanja(PrvaDrugaImp.DRIGA_IMP);
                //System.out.println(termin);
                int flag = 1;
                List<Termin> provera = getRaspored();
                for (Termin t: provera){
                    if (zauzet(termin,t)){
                        flag = 0;
                        System.out.println("Ovaj termin nije dodat jer se preklapa sa drugim terminom" + " " + termin);
                    }
                }
                if (flag == 1 || getRaspored().isEmpty()) {
                    List<Termin> ddd = getRaspored();
                    ddd.add(termin);
                }

            }
        }
            List<Termin> zaDodavanje = new ArrayList<>();
        for (LocalDateTime datumm = pocetakDan; datumm.isBefore(krajDan); datumm = datumm.plusDays(1)){

                if(datumm.getDayOfWeek() == danEng){
                    LocalDateTime krajj = LocalDateTime.of(datumm.getYear(),datumm.getMonth(),datumm.getDayOfMonth(),krajSati,0);
                    LocalDateTime pocetakk = LocalDateTime.of(datumm.getYear(),datumm.getMonth(),datumm.getDayOfMonth(),pocetakSati,0);

                    //System.out.println(pocetakk);
                    //System.out.println(krajj);
                    Termin termin = new Termin(prostor,pocetakk,krajj,dodaci);
                    termin.setTipZakazivanja(PrvaDrugaImp.DRIGA_IMP);
                    //System.out.println(termin);
                    //int flag = 1;
                    List<Termin> provera = getRaspored();
                        for (Termin t: provera){
                            if (zauzet(termin,t)){
                                //flag = 0;
                                System.out.println("Ovaj termin nije dodat jer se preklapa sa drugim terminom");
                                return false;
                            }
                        }
                        zaDodavanje.add(termin);
                        //if (flag == 1 || getRaspored().isEmpty()) {
                            //List<Termin> ddd = getRaspored();
                            //ddd.add(termin);
                       // }

                }

        }

        //System.out.println(prostor);
       // System.out.println(danEng);
       // System.out.println(pocetakSati);
       // System.out.println(krajSati);
       // System.out.println(pocetakDan);
      //  System.out.println(krajDan);

        //System.out.println(pocetakDan.getDayOfWeek());


           // List<Termin> ispis = getRaspored();
        //System.out.println(ispis.size());
       // for (Termin t : ispis){
            //System.out.println(t);
       // }
        //dodaci.clear();
        getRaspored().addAll(zaDodavanje);
        return true;
    }

    private boolean proveriSobe(Termin novi, Termin postojeci){
        if(novi.getProstor().getIme().equals(postojeci.getProstor().getIme())){
            return true;
        }
        return false;
    }

    public boolean zauzet(Termin jedan,Termin dva){
        if (jedan.getPocetak().getYear()==dva.getPocetak().getYear() && jedan.getPocetak().getMonth()==dva.getPocetak().getMonth() && jedan.getPocetak().getDayOfMonth()==dva.getPocetak().getDayOfMonth()){
            if (jedan.getPocetak().getHour() >= dva.getKraj().getHour() || jedan.getKraj().getHour() <= dva.getPocetak().getHour() || !proveriSobe(jedan, dva)){
               // System.out.println("Slobodan");
                return false;
            }else {
               // System.out.println("Zauzetttttttttttt");
                return true;
            }
        }else {
            //System.out.println("zauzet");
            return false;
        }

    }


    @Override
    public boolean brisanjeTermina(String... strings) {
        List<String> stringovi = new ArrayList<>();
        for (String i : strings) {
            stringovi.add(i);
        }

        String datum = stringovi.get(0);

        int dan,mesec,godina;

        dan = Integer.parseInt(String.valueOf(datum.charAt(0)))*10 + Integer.parseInt(String.valueOf(datum.charAt(1)));

        mesec = Integer.parseInt(String.valueOf(datum.charAt(3)))*10 + Integer.parseInt(String.valueOf(datum.charAt(4)));

        godina = Integer.parseInt(String.valueOf(datum.charAt(6)))*1000 + Integer.parseInt(String.valueOf(datum.charAt(7)))*100 + Integer.parseInt(String.valueOf(datum.charAt(8)))*10 + Integer.parseInt(String.valueOf(datum.charAt(9)));

        pocetakDan = LocalDateTime.of(godina,mesec,dan,0,0);


        String datum2 = stringovi.get(1);

        int dan2,mesec2,godina2;

        dan2 = Integer.parseInt(String.valueOf(datum2.charAt(0)))*10 + Integer.parseInt(String.valueOf(datum2.charAt(1)));

        mesec2 = Integer.parseInt(String.valueOf(datum2.charAt(3)))*10 + Integer.parseInt(String.valueOf(datum2.charAt(4)));

        godina2 = Integer.parseInt(String.valueOf(datum2.charAt(6)))*1000 + Integer.parseInt(String.valueOf(datum2.charAt(7)))*100 + Integer.parseInt(String.valueOf(datum2.charAt(8)))*10 + Integer.parseInt(String.valueOf(datum2.charAt(9)));

        krajDan = LocalDateTime.of(godina2,mesec2,dan2,0,0);



        if (stringovi.size() == 2){
            List<Termin> obrisi = new ArrayList<>();
            if(pocetakDan.getYear() == krajDan.getYear() && pocetakDan.getMonth() == krajDan.getMonth() && pocetakDan.getDayOfMonth()==krajDan.getDayOfMonth()){
                for (Termin t : getRaspored()){
                    if (t.getPocetak().getYear() == pocetakDan.getYear() && t.getPocetak().getMonth() == pocetakDan.getMonth() && t.getPocetak().getDayOfMonth() == pocetakDan.getDayOfMonth() && t.getTipZakazivanja() == PrvaDrugaImp.DRIGA_IMP){
                        obrisi.add(t);
                    }
                }
                getRaspored().removeAll(obrisi);
            }

            for (LocalDateTime datumm = pocetakDan; datumm.isBefore(krajDan); datumm = datumm.plusDays(1)){

                for (Termin t : getRaspored()){
                    //System.out.println(t.getTipZakazivanja());
                    if (t.getPocetak().getYear() == datumm.getYear() && t.getPocetak().getMonth() == datumm.getMonth() && t.getPocetak().getDayOfMonth() == datumm.getDayOfMonth() && t.getTipZakazivanja() == PrvaDrugaImp.DRIGA_IMP){
                        //System.out.println("aleeeeeeeeeeeeeeeeeeeeeee");
                        obrisi.add(t);
                    }
                }

            }
            getRaspored().removeAll(obrisi);
        } else if (stringovi.size() == 4) {
            List<Termin> obrisi = new ArrayList<>();
            pocetakSati = Integer.parseInt(stringovi.get(2));

            krajSati = Integer.parseInt(stringovi.get(3));

            if(pocetakDan.getYear() == krajDan.getYear() && pocetakDan.getMonth() == krajDan.getMonth() && pocetakDan.getDayOfMonth()==krajDan.getDayOfMonth()){
                LocalDateTime krajj = LocalDateTime.of(pocetakDan.getYear(), pocetakDan.getMonth(), pocetakDan.getDayOfMonth(), krajSati, 0);
                LocalDateTime pocetakk = LocalDateTime.of(pocetakDan.getYear(), pocetakDan.getMonth(), pocetakDan.getDayOfMonth(), pocetakSati, 0);
                for (Termin t : getRaspored()){
                    if (t.getPocetak().getYear() == pocetakDan.getYear() && t.getPocetak().getMonth() == pocetakDan.getMonth() && t.getPocetak().getDayOfMonth() == pocetakDan.getDayOfMonth() && t.getPocetak().getHour() == pocetakk.getHour() && t.getKraj().getHour() == krajj.getHour() && t.getTipZakazivanja() == PrvaDrugaImp.DRIGA_IMP){
                        obrisi.add(t);
                    }
                }
                getRaspored().removeAll(obrisi);
            }

            for (LocalDateTime datumm = pocetakDan; datumm.isBefore(krajDan); datumm = datumm.plusDays(1)) {
                LocalDateTime krajj = LocalDateTime.of(datumm.getYear(), datumm.getMonth(), datumm.getDayOfMonth(), krajSati, 0);
                LocalDateTime pocetakk = LocalDateTime.of(datumm.getYear(), datumm.getMonth(), datumm.getDayOfMonth(), pocetakSati, 0);

                for(Termin t : getRaspored()){
                    if (t.getPocetak().getYear() == pocetakk.getYear() && t.getPocetak().getMonth() == pocetakk.getMonth() && t.getPocetak().getDayOfMonth() == pocetakk.getDayOfMonth() && t.getPocetak().getHour() == pocetakk.getHour() && t.getKraj().getHour() == krajj.getHour() && t.getTipZakazivanja() == PrvaDrugaImp.DRIGA_IMP){
                        obrisi.add(t);
                    }
                }
            }
            getRaspored().removeAll(obrisi);
        } else if (stringovi.size() == 3) {
            List<Termin> obrisi = new ArrayList<>();
            dann = stringovi.get(2);

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
            if(pocetakDan.getYear() == krajDan.getYear() && pocetakDan.getMonth() == krajDan.getMonth() && pocetakDan.getDayOfMonth()==krajDan.getDayOfMonth()){
                for (Termin t : getRaspored()){
                    if (t.getPocetak().getYear() == pocetakDan.getYear() && t.getPocetak().getMonth() == pocetakDan.getMonth() && t.getPocetak().getDayOfMonth() == pocetakDan.getDayOfMonth() && t.getPocetak().getDayOfWeek() == danEng && t.getTipZakazivanja() == PrvaDrugaImp.DRIGA_IMP){
                        obrisi.add(t);
                    }
                }
                getRaspored().removeAll(obrisi);
            }

            for (LocalDateTime datumm = pocetakDan; datumm.isBefore(krajDan); datumm = datumm.plusDays(1)) {
                LocalDateTime krajj = LocalDateTime.of(datumm.getYear(), datumm.getMonth(), datumm.getDayOfMonth(), 0, 0);
                LocalDateTime pocetakk = LocalDateTime.of(datumm.getYear(), datumm.getMonth(), datumm.getDayOfMonth(), 0, 0);

                if (datumm.getDayOfWeek() == danEng){
                    for(Termin t : getRaspored()){
                        if (t.getPocetak().getYear() == datumm.getYear() && t.getPocetak().getMonth() == datumm.getMonth() && t.getPocetak().getDayOfMonth() == datumm.getDayOfMonth() && t.getTipZakazivanja() == PrvaDrugaImp.DRIGA_IMP){
                            obrisi.add(t);
                        }
                    }
                }


            }
            getRaspored().removeAll(obrisi);
        } else if (stringovi.size() == 5) {
            List<Termin> obrisi = new ArrayList<>();
            pocetakSati = Integer.parseInt(stringovi.get(2));

            krajSati = Integer.parseInt(stringovi.get(3));

            dann = stringovi.get(4);

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

            if(pocetakDan.getYear() == krajDan.getYear() && pocetakDan.getMonth() == krajDan.getMonth() && pocetakDan.getDayOfMonth()==krajDan.getDayOfMonth()){
                LocalDateTime krajj = LocalDateTime.of(pocetakDan.getYear(), pocetakDan.getMonth(), pocetakDan.getDayOfMonth(), krajSati, 0);
                LocalDateTime pocetakk = LocalDateTime.of(pocetakDan.getYear(), pocetakDan.getMonth(), pocetakDan.getDayOfMonth(), pocetakSati, 0);
                for (Termin t : getRaspored()){
                    if (t.getPocetak().getYear() == pocetakDan.getYear() && t.getPocetak().getMonth() == pocetakDan.getMonth() && t.getPocetak().getDayOfMonth() == pocetakDan.getDayOfMonth() && t.getPocetak().getHour() == pocetakk.getHour() && t.getKraj().getHour() == krajj.getHour() && t.getPocetak().getDayOfWeek() == danEng && t.getTipZakazivanja() == PrvaDrugaImp.DRIGA_IMP){
                        obrisi.add(t);
                    }
                }
                getRaspored().removeAll(obrisi);
            }

            for (LocalDateTime datumm = pocetakDan; datumm.isBefore(krajDan); datumm = datumm.plusDays(1)) {
                LocalDateTime krajj = LocalDateTime.of(datumm.getYear(), datumm.getMonth(), datumm.getDayOfMonth(), krajSati, 0);
                LocalDateTime pocetakk = LocalDateTime.of(datumm.getYear(), datumm.getMonth(), datumm.getDayOfMonth(), pocetakSati, 0);

                if (datumm.getDayOfWeek() == danEng){
                    for (Termin t : getRaspored()){
                        if (t.getPocetak().getYear() == pocetakk.getYear() && t.getPocetak().getMonth() == pocetakk.getMonth() && t.getPocetak().getDayOfMonth() == pocetakk.getDayOfMonth() && t.getPocetak().getHour() == pocetakk.getHour() && t.getKraj().getHour() == krajj.getHour() && t.getTipZakazivanja() == PrvaDrugaImp.DRIGA_IMP){
                            obrisi.add(t);
                        }
                    }
                }
            }
                getRaspored().removeAll(obrisi);
            }


        return true;
    }

    @Override
    public boolean premestanjeTermina(String... strings) {
        List<String> stringovi = new ArrayList<>();
        for (String i : strings) {
            stringovi.add(i);
        }

        soba = stringovi.get(0);

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
        Map<String,String> dodaci = new HashMap<>();
        for (Termin t : getRaspored()){
            if (t.getPocetak().getYear() == pocetakDan.getYear() && t.getPocetak().getMonth() == pocetakDan.getMonth() && t.getPocetak().getDayOfMonth() == pocetakDan.getDayOfMonth() && t.getPocetak().getHour() == pocetakSati && t.getKraj().getHour() == krajSati){
                dodaci = t.getDodaci();
            }
        }
        //System.out.println(dodaci.size());
        brisanjeTermina(stringovi.get(4),stringovi.get(5),stringovi.get(2),stringovi.get(3),stringovi.get(1));


        String prostor2 = stringovi.get(6);

        String dann2 = stringovi.get(7);

        DayOfWeek danEng2;

        switch (dann2){
            case "ponedeljak":
                danEng2 = DayOfWeek.MONDAY;
                break;
            case "utorak":
                danEng2 = DayOfWeek.TUESDAY;
                break;
            case "sreda":
                danEng2 = DayOfWeek.WEDNESDAY;
                break;
            case "cetvrtak":
                danEng2 = DayOfWeek.THURSDAY;
                break;
            case "petak":
                danEng2 = DayOfWeek.FRIDAY;
                break;
            case "subota":
                danEng2 = DayOfWeek.SATURDAY;
                break;
            case "nedelja":
                danEng2 = DayOfWeek.SUNDAY;
                break;
        }

        int pocetakSati2 = Integer.parseInt(stringovi.get(8));

        int krajSati2 = Integer.parseInt(stringovi.get(9));


        String datum2 = stringovi.get(10);

        int dan2,mesec2,godina2;

        dan2 = Integer.parseInt(String.valueOf(datum2.charAt(0)))*10 + Integer.parseInt(String.valueOf(datum2.charAt(1)));

        mesec2 = Integer.parseInt(String.valueOf(datum2.charAt(3)))*10 + Integer.parseInt(String.valueOf(datum2.charAt(4)));

        godina2 = Integer.parseInt(String.valueOf(datum2.charAt(6)))*1000 + Integer.parseInt(String.valueOf(datum2.charAt(7)))*100 + Integer.parseInt(String.valueOf(datum2.charAt(8)))*10 + Integer.parseInt(String.valueOf(datum2.charAt(9)));

        LocalDateTime pocetakDan2 = LocalDateTime.of(godina2,mesec2,dan2,0,0);
        //System.out.println(dan2 + "   alooooooooooo");
        // System.out.println(mesec2);
        //System.out.println(godina2);


        String datum3 = stringovi.get(11);

        int dan3,mesec3,godina3;

        dan3 = Integer.parseInt(String.valueOf(datum3.charAt(0)))*10 + Integer.parseInt(String.valueOf(datum3.charAt(1)));

        mesec3 = Integer.parseInt(String.valueOf(datum3.charAt(3)))*10 + Integer.parseInt(String.valueOf(datum3.charAt(4)));

        godina3 = Integer.parseInt(String.valueOf(datum3.charAt(6)))*1000 + Integer.parseInt(String.valueOf(datum3.charAt(7)))*100 + Integer.parseInt(String.valueOf(datum3.charAt(8)))*10 + Integer.parseInt(String.valueOf(datum3.charAt(9)));

        LocalDateTime krajDan3 = LocalDateTime.of(godina,mesec,dan,0,0);





        dodajNoviTermin(stringovi.get(6),stringovi.get(7),stringovi.get(8),stringovi.get(9),stringovi.get(10),stringovi.get(11));

        //System.out.println(dodaci.size() + "      dddddddddddddddddddddddd");
        for (LocalDateTime datumm = pocetakDan2; datumm.isBefore(krajDan3); datumm = datumm.plusDays(1)) {
            LocalDateTime krajj = LocalDateTime.of(datumm.getYear(), datumm.getMonth(), datumm.getDayOfMonth(), krajSati2, 0);
            LocalDateTime pocetakk = LocalDateTime.of(datumm.getYear(), datumm.getMonth(), datumm.getDayOfMonth(), pocetakSati2, 0);

            for (Termin t :getRaspored()){
                if (t.getPocetak().getYear() == pocetakk.getYear() && t.getPocetak().getMonth() == pocetakk.getMonth() && t.getPocetak().getDayOfMonth() == pocetakk.getDayOfMonth() && t.getPocetak().getHour() == pocetakk.getHour() && t.getKraj().getHour() == krajj.getHour() && t.getTipZakazivanja() == PrvaDrugaImp.DRIGA_IMP){
                    t.setDodaci(dodaci);
                }
            }


        }





            return false;
    }


}
