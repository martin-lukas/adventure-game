package util;

/**
 * Rozhraní subjektu změny prostoru.
 * @author Martin
 */
public interface SubjektZmenyProstoru {

    /**
     * Metoda slouží k zaregistrování pozorovatele, musí to být instance třídy,
     * která implementuje rozhraní ObserverZmenyProstoru.
     *
     * @param pozorovatel - registrovaný objekt
     */
    public void zaregistrujPozorovatele(ObserverZmenyProstoru pozorovatel);

    /**
     * Metoda slouží k zrušení registrace pozorovatele, musí to být instance
     * třídy, která implementuje rozhraní ObserverZmenyProstoru.
     *
     * @param pozorovatel - objekt, který již nechce být informován o změnách
     */
    public void odregistrujPozorovatele(ObserverZmenyProstoru pozorovatel);

    /**
     * Metoda, která se volá vždy, když dojde ke změně této instance. Upozorní
     * všechny pozorovatele, že došlo ke změně tak, že zavolá jejich metodu
     * aktualizuj.
     */
    public void upozorniPozorovatele();
}
