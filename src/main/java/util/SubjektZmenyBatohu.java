package util;

/**
 * Rozhraní subjektu pro batoh.
 * 
 * @author Martin Lukáš
 * @version 1.0
 */
public interface SubjektZmenyBatohu {

    /**
     * Metoda slouží k zaregistrování pozorovatele, musí to být instance třídy,
     * která implementuje rozhraní ObserverZmenyBatohu.
     *
     * @param pozorovatel registrovaný objekt
     */
    public void zaregistrujPozorovatele(ObserverZmenyBatohu pozorovatel);

    /**
     * Metoda slouží k zrušení registrace pozorovatele, musí to být instance
     * třídy, která implementuje rozhraní ObserverZmenyBatohu.
     *
     * @param pozorovatel objekt, který již nechce být informován o změnách
     */
    public void odregistrujPozorovatele(ObserverZmenyBatohu pozorovatel);

    /**
     * Metoda, která se volá vždy, když dojde ke změně této instance. Upozorní
     * všechny pozorovatele, že došlo ke změně tak, že zavolá jejich metodu
     * aktualizuj.
     */
    public void upozorniPozorovatele();
}