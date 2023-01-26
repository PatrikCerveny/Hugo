package com.example.hugo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

    @Parcelize
    data class Result(val title: String ,val subTitle: String, val explanation: String, val image: Int) : Parcelable {
        companion object {
            val results = listOf(
                Result(
                    "Hoax",
                    "Tvoja informácia je pravdepodobne Hoax",
                    "Čo je to Hoax? Hoax v preklade z anglického slova znamená podvod, vtip (výslovnosť termínu hoax je hōks). " +
                            "Označuje poplašnú alebo nezmyselnú správu, ktorá je masovo šírená najmä prostredníctvom internetu. Hoaxy sú najčastejšie preposielané formou emailu" +
                            " alebo zdieľaním na sociálnych sieťach. Bývajú jednoduché a obsahujú výzvu na šírenie. Odborníci sa zhodujú, že ak dostaneme takýto e-mail (prípadne iný typ správy), " +
                            "je dobré poučiť ľudí, ktorí ho poslali o zbytočnosti (a škodlivosti) ich dobrého úmyslu. Do budúcnosti sa tak ušetrí množstvo času a energie. " +
                            "Niekedy však môže ísť o reálnu správu, tú je však vhodné preposielať len pri úplnej istote, že je pravdivá, napr. keď osobne poznáte odosielateľa a viete, že: " +
                            "rozumie tomu, čo píše, správa pochádza priamo od neho (nie je preposlaná), zvykne písať pravdu. Známy server o tejto problematike, hoax.cz, uvádza: „V praxi můžeme použít následující pravidlo: " +
                            "Jestliže zpráva obsahuje výzvu k hromadnému rozeslání na další adresy, je to s největší pravděpodobností HOAX.“ Je potrebné podotknúť, že táto výzva NEMUSÍ byť vyjadrená priamo. Napríklad \"Dajte pozor na svoje deti!\". Doslova sa nám vnucuje správu preposlať, aby sme chránili deti našich blízkych vďaka útokom na naše city. "+
                            "Je veľa dôvodov, ktoré mohli viesť pôvodného autora k napísaniu správy, zväčša však ide o snahu zabaviť sa. V mnohých správach sú úplné absurdnosti, to však nebráni tomu, aby sa správa vďaka ľudskej hlúposti nešírila ďalej. Pri viacnásobnom preposielaní je možné z hlavičiek e-mailových správ (zobraziť podrobnosti) získať pomerne veľké množstvo e-mailových adries (tzv. Email harvesting).",
                    R.drawable.truth

                ),
                Result(
                    "Dezinformácia",
                    "Tvoja informácia je pravdepodobne dezinformáciouasd adas",
                    "Dezinformácia je viac alebo menej zámerne vytvorená skreslená alebo chybná informácia – chce mýliť, zavádzať adresáta. Dezinformácia vedie k nesprávnym záverom alebo rozhodnutiam. " +
                            "Je to chybná správa alebo posolstvo inkorporované do určitého informačného systému, spravidla s cieľom zmeniť ho a dosiahnuť inhibíciu/excitáciu zvolených mienkotvorných tém v populačnom prostredí. " +
                            "Môže to byť aj úmyselne nesprávna či skreslená informácia tajne implantovaná do informačnej sústavy oponenta so zámerom ovplyvniť potrebným smerom jeho aktivity (názory). " +
                            "Je nástrojom tzv. psychologickej vojny – čiernej propagandy. pojem, ktorý zahŕňa nie len vecný obsah “informácie”, ale i proces jej rozširovania a recepcie adresátom. Dezinformácia je rovnako fakticky nesprávna, " +
                            "no jej tvorca a často aj šíriteľ to vie, a napriek tomu ju vedome rozširuje a snaží sa tým ublížiť. Dezinformácie môžu byť aj informácie, ktoré sú vo svojom jadre pravdivé, ale sú šírené bez súvislostí – sú kumulované " +
                            "na jeden portál alebo profil a vytvárajú skreslený pohľad na problematiku. Vyberajú sa napríklad ojedinelé prípady, ktoré sú však kumulované. Dezinformácia vedie k nesprávnym záverom alebo rozhodnutiam. Je aj nástrojom tzv. psychologickej vojny – propagandy.",
                    R.drawable.conspiracy

                ),
                Result(
                    "Informácia si vyžaduje pozornosť",
                    "Tvoja informácia vykazuje známky dezinformácií",
                    "Dezinformácia – označuje nepravdivú alebo zmanipulovanú informáciu, ktorá je šírená zámerne s cieľom zavádzať a uškodiť. " +
                            "Dezinformácie môžu mať podobu nepravdivého alebo zmanipulovaného textu, obrázku, videa alebo zvuku, pričom môžu byť použité na podporu konšpirácií, šírenie pochybností a diskreditáciu pravdivých informácií či jednotlivcov a organizácií. " +
                            "Aj pravdivú informáciu môžeme považovať za dezinformáciu, ak je podaná manipulatívnym spôsobom. Medzi dezinformácie nepatria neúmyselné chyby v spravodajstve, " +
                            "satira, paródia ani správy a komentáre naklonené jednej strane, ktoré sú takto zreteľne označené. Môže ísť aj o misinformáciu. Misinformácia – mylná alebo nepravdivá informácia, ktorá sa na rozdiel od dezinformácií, šíri nevedome a bez úmyslu poškodiť. " +
                            "Dezinformácia je rovnako fakticky nesprávna, no jej tvorca a často aj šíriteľ to vie, a napriek tomu ju vedome rozširuje a snaží sa tým ublížiť. Dezinformácie môžu byť aj informácie, ktoré sú vo svojom jadre pravdivé, ale sú šírené bez súvislostí " +
                            "– sú kumulované na jeden portál alebo profil a vytvárajú skreslený pohľad na problematiku. Vyberajú sa napríklad ojedinelé prípady, ktoré sú však kumulované. Dezinformácia vedie k nesprávnym záverom alebo rozhodnutiam. Je aj nástrojom tzv. psychologickej vojny – propagandy.",
                    R.drawable.fake_news

                ),
                Result(
                    "Informácia je OK",
                    "Tvoja informácia nevykazuje žiadne známky dezinformácií",
                    "Aj keď tvoja informácia nevykazuje známky dezinformácií je potrebné zostať na pozore. V digitálnej dobe internetu majú ľudia oveľa rýchlejší prístup k informáciám ako kedykoľvek v minulosti, čo je úžasné, ale môže to mať aj negatívne efekty. " +
                            "Na internete sa šíria najrôznejšie správy, a aj keď často ide najmä o rýchlosť, dôležitá je aj aktuálnosť a pravdivosť informácie. Tu nastáva problém s presýtením internetu obsahom, ktorý nemusí byť vždy pravdivý. Často krát sa stáva, že informácie na " +
                            "internete sú nepravdivé alebo sú to len tzv. polopravdy, obsahujú upravované fotografie a videá. Na internet prispievajú všetci – ľudia so vzdelaním, bez neho, vzdelávajúci sa alebo tí, ktorí svoje vzdelanie predstierajú. " +
                            "S informáciami, ktoré máš z internetu treba narábať opatrne, uvažovať nad nimi a treba dobre zvážiť, čomu uveríte a čomu nie. Je potrebné preverovať, porovnávať zdroje a snažiť sa neprispievať k šíreniu nespoľahlivých informácií. Dezinformácia je rovnako fakticky nesprávna, no jej tvorca a často aj šíriteľ to vie, a napriek tomu ju vedome rozširuje a snaží sa tým ublížiť. " +
                            "Dezinformácie môžu byť aj informácie, ktoré sú vo svojom jadre pravdivé, ale sú šírené bez súvislostí – sú kumulované na jeden portál alebo profil a vytvárajú skreslený pohľad na problematiku. Vyberajú sa napríklad ojedinelé prípady, ktoré sú však kumulované. Dezinformácia vedie k nesprávnym záverom alebo rozhodnutiam. Je aj nástrojom tzv. psychologickej vojny – propagandy.",
                    R.drawable.facts
                ),
            )
        }
    }
